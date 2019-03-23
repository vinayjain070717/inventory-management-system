package com.thinking.machine.inventory.bl;
import javax.swing.table.*;
import java.util.*;
public class ItemModel extends AbstractTableModel
{
private List<ItemInterface> items;
private List<ItemInterface> viewItems;
private String filterByCategory;
private String[] title;
public ItemModel()
{
filterByCategory=null;
title=new String[]{"S.No.","Item"};
populateDataStructure();
this.viewItems=this.items;
}
private void populateDataStructure()
{
List<com.thinking.machine.inventory.dl.ItemInterface> dlItems;
com.thinking.machine.inventory.dl.ItemDAOInterface itemDAO=new com.thinking.machine.inventory.dl.ItemDAO();
try
{
dlItems=itemDAO.getAll();
items=new LinkedList<ItemInterface>();
ItemInterface item;
for(com.thinking.machine.inventory.dl.ItemInterface dlItem:dlItems)
{
item=new Item();
item.setCode(dlItem.getCode());
item.setName(dlItem.getName());
item.setCategory(dlItem.getCategory());
this.items.add(item);
}
Collections.sort(items,new Comparator<ItemInterface>(){
public int compare(ItemInterface left,ItemInterface right)
{
return left.getName().toUpperCase().compareTo(right.getName().toUpperCase());
}
});
}catch(com.thinking.machine.inventory.dl.DAOException daoException)
{
this.items=new LinkedList<>();
}
}
public int getRowCount()
{
return this.viewItems.size();
}
public int getColumnCount()
{
return title.length;
}
public String getColumnName(int columnIndex)
{
if(columnIndex==0) return "S.No.";
return "Item";
}
public Class getColumnClass(int columnIndex)
{
if(columnIndex==0) return java.lang.Integer.class;
return java.lang.String.class;
}
public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}
public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0) return rowIndex+1;
return this.viewItems.get(rowIndex).getName();
}
public void add(ItemInterface itemInterface) throws BLException
{
int i=0;
ItemInterface ii;
while(i<this.items.size())
{
ii=this.items.get(i);
if(ii.getName().equalsIgnoreCase(itemInterface.getName())) throw new BLException("Item : "+itemInterface.getName()+" exists.");
if(ii.getName().toUpperCase().compareTo(itemInterface.getName().toUpperCase())>0) break;
i++;
}
try
{
com.thinking.machine.inventory.dl.ItemInterface dlItem;
dlItem=new com.thinking.machine.inventory.dl.Item();
dlItem.setName(itemInterface.getName());
dlItem.setCategory(itemInterface.getCategory());
new com.thinking.machine.inventory.dl.ItemDAO().add(dlItem);
itemInterface.setCode(dlItem.getCode());
this.items.add(i,itemInterface);
if(this.viewItems!=this.items && itemInterface.getCategory().equals(this.filterByCategory))
{
i=0;
for(ItemInterface iii:this.viewItems)
{
if(iii.getName().toUpperCase().compareTo(itemInterface.getName().toUpperCase())>0) break;
i++;
}
this.viewItems.add(i,itemInterface);
}
this.fireTableDataChanged();
}catch(com.thinking.machine.inventory.dl.DAOException daoException)
{
throw new BLException(daoException.getMessage());
}
}
public void update(ItemInterface itemInterface) throws BLException
{
int i;
for(i=0;i<this.items.size();i++)
{
if(this.items.get(i).getCode()==itemInterface.getCode()) break;
}
if(i==this.items.size()) throw new BLException("Invalid item code : "+itemInterface.getCode());
int foundAt=i;
ItemInterface ii;
for(i=0;i<this.items.size();i++)
{
ii=this.items.get(i);
if(ii.getName().equalsIgnoreCase(itemInterface.getName()) && ii.getCode()!=itemInterface.getCode()) throw new BLException("Item : "+itemInterface.getName()+" exists.");
}
try
{
com.thinking.machine.inventory.dl.ItemInterface dlItem;
dlItem=new com.thinking.machine.inventory.dl.Item();
dlItem.setCode(itemInterface.getCode());
dlItem.setName(itemInterface.getName());
dlItem.setCategory(itemInterface.getCategory());
new com.thinking.machine.inventory.dl.ItemDAO().update(dlItem);
this.items.remove(foundAt);
for(i=0;i<this.items.size();i++)
{
if(this.items.get(i).getName().toUpperCase().compareTo(itemInterface.getName().toUpperCase())>0) break;
}
this.items.add(i,itemInterface);
if(this.viewItems!=this.items)
{
i=0;
for(ItemInterface iii:this.viewItems)
{
if(iii.equals(itemInterface)) break;
i++;
}
if(i<this.viewItems.size())
{
this.viewItems.remove(i);
}
if(this.filterByCategory.equals(itemInterface.getCategory()))
{
i=0;
for(ItemInterface iii:this.viewItems)
{
if(iii.getName().toUpperCase().compareTo(itemInterface.getName().toUpperCase())>0) break;
i++;
}
this.viewItems.add(i,itemInterface);
}
}
fireTableDataChanged();
}catch(com.thinking.machine.inventory.dl.DAOException daoException)
{
throw new BLException(daoException.getMessage());
}
}
public void delete(int code) throws BLException
{
int i;
for(i=0;i<this.items.size();i++)
{
if(this.items.get(i).getCode()==code) break;
}
if(i==this.items.size()) throw new BLException("Invalid item code : "+code);
try
{
new com.thinking.machine.inventory.dl.ItemDAO().delete(code);
this.items.remove(i);
if(this.viewItems!=this.items)
{
i=0;
for(ItemInterface ii:this.viewItems)
{
if(ii.getCode()==code) break;
i++;
}
if(i<this.viewItems.size()) this.viewItems.remove(i);
}
this.fireTableDataChanged();
}catch(com.thinking.machine.inventory.dl.DAOException  daoException)
{
throw new BLException(daoException.getMessage());
}
}
public ItemInterface search(String name,boolean matchLeft,boolean caseInSensitive) throws BLException
{
for(ItemInterface item:this.viewItems)
{
if(matchLeft)
{
if(caseInSensitive)
{
if(item.getName().toUpperCase().startsWith(name.toUpperCase())) return item;
}
else
{
if(item.getName().startsWith(name)) return item;
}
}
else
{
if(caseInSensitive)
{
if(item.getName().equalsIgnoreCase(name)) return item;
}
else
{
if(item.getName().equals(name)) return item;
}
}
}
throw new BLException("Invalid item : "+name);
}
public ItemInterface getItemAt(int index) throws BLException
{
if(index<0 || index>=this.viewItems.size()) throw new BLException("Invalid index : "+index);
return this.viewItems.get(index);
}
public int getIndexOf(ItemInterface item) throws BLException
{
int x=0;
for(ItemInterface i:this.viewItems)
{
if(i.equals(item)) return x;
x++;
}
throw new BLException("Invalid item : "+item.getName());
}
public void applyFilter(String category)
{
this.viewItems=new LinkedList<>();
for(ItemInterface ii:this.items)
{
if(ii.getCategory().equals(category)) this.viewItems.add(ii);
}
this.filterByCategory=category;
fireTableDataChanged();
}
public void clearFilter()
{
this.viewItems=this.items;
this.filterByCategory=null;
fireTableDataChanged();
}
public Vector<String> getCategories()
{
Set<String> categories=new HashSet<String>();
for(ItemInterface i:items)
{
categories.add(i.getCategory());
}
Vector v=new Vector<String>(categories);
Collections.sort(v);
return v;
}
}