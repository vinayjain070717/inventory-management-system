package com.thinking.machine.inventory.dl;
import java.io.*;
import java.util.*;
public class ItemDAO implements ItemDAOInterface
{
private static final String fileName="item.data";
public void add(ItemInterface itemInterface) throws DAOException
{
try
{
File file=new File(fileName);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
int code;
int numberOfRecords;
if(randomAccessFile.length()==0)
{
code=1;
numberOfRecords=1;
randomAccessFile.writeBytes(String.format("%-10s%-10s",String.valueOf(code),String.valueOf(numberOfRecords)));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(code));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(itemInterface.getName());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(itemInterface.getCategory());
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
itemInterface.setCode(code);
return;
}
String header=randomAccessFile.readLine();
int vCode;
String vName;
String vCategory;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vCategory=randomAccessFile.readLine();
if(vName.equalsIgnoreCase(itemInterface.getName()))
{
randomAccessFile.close();
throw new DAOException("Item : "+itemInterface.getName()+" exists");
}
}
code=Integer.parseInt(header.substring(0,10).trim())+1;
numberOfRecords=Integer.parseInt(header.substring(10).trim())+1;
randomAccessFile.writeBytes(String.valueOf(code));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(itemInterface.getName());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(itemInterface.getCategory());
randomAccessFile.writeBytes("\n");
randomAccessFile.seek(0);
randomAccessFile.writeBytes(String.format("%-10s%-10s",String.valueOf(code),String.valueOf(numberOfRecords)));
randomAccessFile.close();
itemInterface.setCode(code);
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}
public void update(ItemInterface itemInterface) throws DAOException
{
try
{
File file=new File(fileName);
if(!file.exists()) throw new DAOException("Invalid item code "+itemInterface.getCode());
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Item Code "+itemInterface.getCode());
}
String header;
header=randomAccessFile.readLine();
if(randomAccessFile.getFilePointer()==randomAccessFile.length())
{
randomAccessFile.close();
throw new DAOException("Invalid Item code "+itemInterface.getCode());
}
int vCode;
String vName;
String vCategory;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vCategory=randomAccessFile.readLine();
if(vCode==itemInterface.getCode())
{
found=true;
break;
}
}
if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invalid Item code "+itemInterface.getCode());
}
randomAccessFile.seek(0);
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vCategory=randomAccessFile.readLine();
if(vName.equalsIgnoreCase(itemInterface.getName()))
{
randomAccessFile.close();
throw new DAOException("Item Name "+itemInterface.getName()+" exists");
}
}
randomAccessFile.seek(0);
File tmpFile=new File("fhaltu.fff");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vCategory=randomAccessFile.readLine();
if(vCode!=itemInterface.getCode())
{
tmpRandomAccessFile.writeBytes(vCode+"\n");
tmpRandomAccessFile.writeBytes(vName+"\n");
tmpRandomAccessFile.writeBytes(vCategory+"\n");
}
else
{
tmpRandomAccessFile.writeBytes(itemInterface.getCode()+"\n");
tmpRandomAccessFile.writeBytes(itemInterface.getName()+"\n");
tmpRandomAccessFile.writeBytes(itemInterface.getCategory()+"\n");
}
}
tmpRandomAccessFile.seek(0);
randomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}
public void delete(int code) throws DAOException
{
try
{
File file=new File(fileName);
if(!file.exists()) throw new DAOException("Invalid item Code"+code);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Item Code "+code);
}
String header;
header=randomAccessFile.readLine();
if(randomAccessFile.getFilePointer()==randomAccessFile.length())
{
randomAccessFile.close();
throw new DAOException("Invalid Item code"+code);
}
int vCode;
String vName;
String vCategory;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vCategory=randomAccessFile.readLine();
if(vCode==code)
{
found=true;
break;
}
}
if(!found)
{
randomAccessFile.close();
throw new DAOException("Invalid Item Code "+code);
}
int lastGeneratedCode;
int numberOfRecords;
lastGeneratedCode=Integer.parseInt(header.substring(0,10).trim());
numberOfRecords=Integer.parseInt(header.substring(10).trim())-1;
File tmpFile=new File("fhaltu.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
header=String.format("%-10s%-10s",String.valueOf(lastGeneratedCode),String.valueOf(numberOfRecords));
tmpRandomAccessFile.writeBytes(header+"\n");
randomAccessFile.seek(0);
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vCategory=randomAccessFile.readLine();
if(vCode!=code)
{
tmpRandomAccessFile.writeBytes(vCode+"\n");
tmpRandomAccessFile.writeBytes(vName+"\n");
tmpRandomAccessFile.writeBytes(vCategory+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
randomAccessFile.close();
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}
public ItemInterface getByCode(int code) throws DAOException
{
try
{
File file=new File(fileName);
if(!file.exists()) throw new DAOException("Invalid item Code"+code);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Item Code "+code);
}
String header;
header=randomAccessFile.readLine();
if(randomAccessFile.getFilePointer()==randomAccessFile.length())
{
randomAccessFile.close();
throw new DAOException("Invalid Item code"+code);
}
int vCode;
String vName;
String vCategory;
ItemInterface itemInterface;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vCategory=randomAccessFile.readLine();
if(vCode==code)
{
itemInterface=new Item();
itemInterface.setCode(vCode);
itemInterface.setName(vName);
itemInterface.setCategory(vCategory);
randomAccessFile.close();
return itemInterface;
}
}
randomAccessFile.close();
throw new DAOException("Invalid item Code :"+code);
}catch(IOException ioException)
{
System.out.println(ioException);
throw new DAOException(ioException.getMessage());
}
}
public ItemInterface getByName(String name) throws DAOException
{
try
{
File file=new File(fileName);
if(!file.exists()) throw new DAOException("Invalid item name"+name);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Item name "+name);
}
String header;
header=randomAccessFile.readLine();
if(randomAccessFile.getFilePointer()==randomAccessFile.length())
{
randomAccessFile.close();
throw new DAOException("Invalid Item name "+name);
}
int vCode;
String vName;
String vCategory;
ItemInterface itemInterface;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vCategory=randomAccessFile.readLine();
if(vName.equalsIgnoreCase(name.trim()))
{
itemInterface=new Item();
itemInterface.setCode(vCode);
itemInterface.setName(vName);
itemInterface.setCategory(vCategory);
randomAccessFile.close();
return itemInterface;
}
}
randomAccessFile.close();
throw new DAOException("Invalid Item name "+name);
}catch(IOException ioException)
{
System.out.println(ioException);
throw new DAOException(ioException.getMessage());
}
}
public List<ItemInterface> getAll() throws DAOException
{
try
{
File file=new File(fileName);
if(!file.exists()) throw new DAOException("No items");
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No items");
}
randomAccessFile.readLine();
if(randomAccessFile.getFilePointer()==randomAccessFile.length())
{
randomAccessFile.close();
throw new DAOException("No items");
}
List<ItemInterface> items=new LinkedList<ItemInterface>();
ItemInterface itemInterface;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
itemInterface=new Item();
itemInterface.setCode(Integer.parseInt(randomAccessFile.readLine()));
itemInterface.setName(randomAccessFile.readLine());
itemInterface.setCategory(randomAccessFile.readLine());
items.add(itemInterface);
}
randomAccessFile.close();
return items;
}catch(IOException ioException)
{
System.out.println(ioException);
throw new DAOException(ioException.getMessage());
}
}
public List<ItemInterface> getByCategory(String category) throws DAOException
{
try
{
File file=new File(fileName);
if(!file.exists()) throw new DAOException("No items");
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No items");
}
randomAccessFile.readLine();
if(randomAccessFile.getFilePointer()==randomAccessFile.length())
{
randomAccessFile.close();
throw new DAOException("No items");
}
List<ItemInterface> items=new LinkedList<ItemInterface>();
ItemInterface itemInterface;
int vCode;
String vName;
String vCategory;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vCategory=randomAccessFile.readLine();
if(vCategory.equals(category))
{
itemInterface=new Item();
itemInterface.setCode(vCode);
itemInterface.setName(vName);
itemInterface.setCategory(vCategory);
items.add(itemInterface);
}
}
randomAccessFile.close();
if(items.size()==0)
{
throw new DAOException("No items");
}
return items;
}catch(IOException ioException)
{
System.out.println(ioException);
throw new DAOException(ioException.getMessage());
}
}
public long getCount() throws DAOException
{
try
{
File file=new File(fileName);
if(!file.exists()) return 0;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
String header=randomAccessFile.readLine();
randomAccessFile.close();
return Long.parseLong(header.substring(10).trim());
}catch(IOException ioException)
{
System.out.println(ioException);
throw new DAOException(ioException.getMessage());
}
}
public long getCategoriesCount() throws DAOException
{
try
{
File file=new File(fileName);
if(!file.exists()) return 0;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0) 
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
if(randomAccessFile.getFilePointer()==randomAccessFile.length())
{
randomAccessFile.close();
return 0;
}
Set<String> categories=new HashSet<String>();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
categories.add(randomAccessFile.readLine());
}
randomAccessFile.close();
return categories.size();
}catch(IOException ioException)
{
System.out.println(ioException);
throw new DAOException(ioException.getMessage());
}
}
public List<String> getCategories() throws DAOException
{
try
{
File file=new File(fileName);
if(!file.exists()) throw new DAOException("No categories");
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No categories");
}
randomAccessFile.readLine();
if(randomAccessFile.getFilePointer()==randomAccessFile.length())
{
randomAccessFile.close();
throw new DAOException("No categories");
}
Set<String> categories=new HashSet<String>();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
categories.add(randomAccessFile.readLine());
}
randomAccessFile.close();
return new LinkedList<String>(categories);
}catch(IOException ioException)
{
System.out.println(ioException);
throw new DAOException(ioException.getMessage());
}
}
}