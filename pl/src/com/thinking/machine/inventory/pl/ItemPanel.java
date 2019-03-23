package com.thinking.machine.inventory.pl;
import java.awt.event.*;
import java.util.*;
import com.thinking.machine.inventory.bl.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
public class ItemPanel extends JPanel implements ListSelectionListener
{
private enum MODE{VIEW_MODE,ADD_MODE,EDIT_MODE,DELETE_MODE,EXPORT_TO_PDF_MODE};
private MODE mode;
private Vector<String> categories;
private ItemModel itemModel;
private JLabel moduleTitleLabel;
private JLabel searchCaptionLabel;
private JTextField searchTextField;
private JButton clearSearchTextFieldButton;
private JLabel searchErrorLabel;
private JLabel filterCategoryCaptionLabel;
private JComboBox filterCategoryComboBox;
private JTable itemViewTable;
private JScrollPane itemViewTableScrollPane;
private ItemCRUDPanel itemCRUDPanel;
public ItemPanel()
{
initComponents();
setAppearance();
addEventListeners();
setViewMode();
}
private void initComponents()
{
this.itemModel=new ItemModel();
this.categories=this.itemModel.getCategories();
this.categories.add(0,"<All>");
this.moduleTitleLabel=new JLabel("Items");
this.searchCaptionLabel=new JLabel("Search");
this.searchTextField=new JTextField();
this.clearSearchTextFieldButton=new JButton("x");
this.searchErrorLabel=new JLabel("");
this.filterCategoryCaptionLabel=new JLabel("Category");
this.filterCategoryComboBox=new JComboBox(this.categories);
this.itemViewTable=new JTable(this.itemModel);
this.itemViewTableScrollPane=new JScrollPane(this.itemViewTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
this.itemCRUDPanel=new ItemCRUDPanel();
}
private void setAppearance()
{
setLayout(null);
int lm,tm;
lm=10;
tm=2;
moduleTitleLabel.setFont(new Font("Verdana",Font.BOLD,24));
moduleTitleLabel.setBounds(lm+0,tm+0,100,40);
add(moduleTitleLabel);
Font dataFont=new Font("Verdana",Font.PLAIN,16);
searchErrorLabel.setFont(new Font("Verdana",Font.BOLD,10));
searchErrorLabel.setForeground(Color.RED);
searchErrorLabel.setBounds(lm+0+55+3+450-60,tm+0+25,60,20);
add(searchErrorLabel);
searchCaptionLabel.setFont(dataFont);
searchCaptionLabel.setBounds(lm+0,tm+0+40+5,55,30);
add(searchCaptionLabel);
searchTextField.setFont(dataFont);
searchTextField.setBounds(lm+0+55+3,tm+0+40+5,450,30);
add(searchTextField);
clearSearchTextFieldButton.setBounds(lm+0+55+3+450+2,tm+0+40+5,30,30);
add(clearSearchTextFieldButton);
filterCategoryCaptionLabel.setFont(dataFont);
filterCategoryCaptionLabel.setBounds(lm+0,tm+0+40+5+30+5,75,30);
add(filterCategoryCaptionLabel);
filterCategoryComboBox.setFont(dataFont);
filterCategoryComboBox.setBounds(lm+0+75+3,tm+0+40+5+30+5,250,30);



add(filterCategoryComboBox);
itemViewTable.setFont(dataFont);
itemViewTableScrollPane.setBounds(lm+0,tm+0+40+5+30+5+30+5,560,250);
itemViewTable.getTableHeader().setFont(new Font("Verdana",Font.BOLD,16));
itemViewTable.getColumnModel().getColumn(0).setPreferredWidth(50);
itemViewTable.getColumnModel().getColumn(1).setPreferredWidth(460);
itemViewTable.setRowHeight(30);
itemViewTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
add(itemViewTableScrollPane);
itemCRUDPanel.setBounds(lm+0,tm+0+40+5+30+5+30+5+250+5,560,225);
itemCRUDPanel.setBorder(BorderFactory.createLineBorder(new Color(122,138,153)));
add(itemCRUDPanel);
}
private void searchItem()
{
searchErrorLabel.setText("");
String lookFor=searchTextField.getText().trim();
if(lookFor.length()==0) return;
try
{
ItemInterface itemInterface=this.itemModel.search(lookFor,true,true);
int index=this.itemModel.getIndexOf(itemInterface);
this.itemViewTable.setRowSelectionInterval(index,index);
this.itemViewTable.scrollRectToVisible(new Rectangle(itemViewTable.getCellRect(index,0,false)));
}catch(BLException blException)
{
this.searchErrorLabel.setText("Not found");
}

}
public void valueChanged(ListSelectionEvent ev)
{
int selectedRow=itemViewTable.getSelectedRow();
if(selectedRow<0 || selectedRow>=itemModel.getRowCount())
{
itemCRUDPanel.clearItem();
return;
}
try
{
ItemInterface item=itemModel.getItemAt(selectedRow);
itemCRUDPanel.setItem(item);
}catch(BLException blException)
{
itemCRUDPanel.clearItem();
}
}
private void addEventListeners()
{
itemViewTable.getSelectionModel().addListSelectionListener(this);
filterCategoryComboBox.addItemListener(new ItemListener(){
public void itemStateChanged(ItemEvent ev)
{
int selectedIndex=filterCategoryComboBox.getSelectedIndex();
if(selectedIndex<0 || selectedIndex>=categories.size()) 
{
itemModel.clearFilter();
return;
}
if(selectedIndex==0)
{
itemModel.clearFilter();
}
else
{
String selectedCategory=categories.elementAt(selectedIndex);
itemModel.applyFilter(selectedCategory);
}
}
});


clearSearchTextFieldButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
searchTextField.setText("");
}
});

this.searchTextField.getDocument().addDocumentListener(new DocumentListener(){
public void insertUpdate(DocumentEvent event)
{
searchItem();
}
public void removeUpdate(DocumentEvent event)
{
searchItem();
}
public void changedUpdate(DocumentEvent event)
{
searchItem();
}

});
}
private void setViewMode()
{
if(itemModel.getRowCount()==0)
{
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
filterCategoryComboBox.setEnabled(false);
itemViewTable.setEnabled(false);
}
else
{
searchTextField.setEnabled(true);
clearSearchTextFieldButton.setEnabled(true);
filterCategoryComboBox.setEnabled(true);
itemViewTable.setEnabled(true);
}
this.mode=MODE.VIEW_MODE;
}
private void setAddMode()
{
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
filterCategoryComboBox.setEnabled(false);
itemViewTable.setEnabled(false);
this.mode=MODE.ADD_MODE;
}
private void setEditMode()
{
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
filterCategoryComboBox.setEnabled(false);
itemViewTable.setEnabled(false);
this.mode=MODE.EDIT_MODE;
}
private void setDeleteMode()
{
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
filterCategoryComboBox.setEnabled(false);
itemViewTable.setEnabled(false);
this.mode=MODE.DELETE_MODE;
}
private void setExportToPDFMode()
{
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
filterCategoryComboBox.setEnabled(false);
itemViewTable.setEnabled(false);
this.mode=MODE.EXPORT_TO_PDF_MODE;
}
//  inner class starts
class ItemCRUDPanel extends JPanel
{
private ItemInterface item=null;
private JLabel nameCaptionLabel;
private JLabel categoryCaptionLabel;
private JLabel nameLabel;
private JLabel categoryLabel;
private JTextField nameTextField;
private JTextField categoryTextField;
private JButton clearNameTextFieldButton;
private JButton clearCategoryTextFieldButton;
private JButton addButton;
private JButton editButton;
private JButton cancelButton;
private JButton deleteButton;
private JButton exportToPDFButton;
private JPanel buttonsPanel;
ItemCRUDPanel()
{
initComponents();
setAppearance();
addEventListeners();
setViewMode();
}
private void initComponents()
{
nameCaptionLabel=new JLabel("Item : ");
nameLabel=new JLabel("");
nameTextField=new JTextField();
clearNameTextFieldButton=new JButton("x");
categoryCaptionLabel=new JLabel("Category : ");
categoryLabel=new JLabel("");
categoryTextField=new JTextField();
clearCategoryTextFieldButton=new JButton("x");
addButton=new JButton("A");
editButton=new JButton("E");
cancelButton=new JButton("C");
deleteButton=new JButton("D");
exportToPDFButton=new JButton("P");
buttonsPanel=new JPanel();
}
private void setAppearance()
{
Font dataFont=new Font("Verdana",Font.PLAIN,16);
setLayout(null);
int lm,tm;
lm=0;
tm=0;
nameCaptionLabel.setBounds(lm+10,tm+10,100,30);
nameLabel.setBounds(lm+10+85+5,tm+10,300,30);
nameTextField.setBounds(lm+10+85+5,tm+10,300,30);
clearNameTextFieldButton.setBounds(lm+10+85+5+300+2,tm+10,30,30);
nameCaptionLabel.setFont(dataFont);
nameLabel.setFont(dataFont);
nameTextField.setFont(dataFont);
categoryCaptionLabel.setBounds(lm+10,tm+10+30+5,100,30);
categoryLabel.setBounds(lm+10+85+5,tm+10+30+5,300,30);
categoryTextField.setBounds(lm+10+85+5,tm+10+30+5,300,30);
clearCategoryTextFieldButton.setBounds(lm+10+85+5+300+2,tm+10+30+5,30,30);
categoryCaptionLabel.setFont(dataFont);
categoryLabel.setFont(dataFont);
categoryTextField.setFont(dataFont);
add(nameCaptionLabel);
add(nameLabel);
add(nameTextField);
add(clearNameTextFieldButton);
add(categoryCaptionLabel);
add(categoryLabel);
add(categoryTextField);
add(clearCategoryTextFieldButton);
addButton.setBounds(10,10,50,50);
editButton.setBounds(70,10,50,50);
cancelButton.setBounds(130,10,50,50);
deleteButton.setBounds(190,10,50,50);
exportToPDFButton.setBounds(250,10,50,50);
buttonsPanel.setLayout(null);
buttonsPanel.add(addButton);
buttonsPanel.add(editButton);
buttonsPanel.add(cancelButton);
buttonsPanel.add(deleteButton);
buttonsPanel.add(exportToPDFButton);
buttonsPanel.setBounds(135,tm+10+30+5+30+30,310,70);



buttonsPanel.setBorder(BorderFactory.createLineBorder(new Color(122,138,153)));
add(buttonsPanel);
}
private void addEventListeners()
{
addButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(mode==MODE.VIEW_MODE)
{
ItemPanel.this.setAddMode();
ItemCRUDPanel.this.setAddMode();
}
else
{
// logic to add : Assignment
// and back to view mode
String name=nameTextField.getText();
String category=categoryTextField.getText();
try
{
ItemInterface itemInterface=new Item();
itemInterface.setName(name);
itemInterface.setCategory(category);
itemModel.add(itemInterface);
}catch(BLException daoe)
{
System.out.println(daoe);
}
categories=itemModel.getCategories();
categories.add(0,"<ALL>");
filterCategoryComboBox.removeAllItems();
for(String i:categories)
{
filterCategoryComboBox.addItem(i);
}
ItemPanel.this.setViewMode();
ItemCRUDPanel.this.setViewMode();
}
}
});
editButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(mode==MODE.VIEW_MODE)
{
int selectedRow=itemViewTable.getSelectedRow();
if(selectedRow<0 || selectedRow>=itemModel.getRowCount())
{
JOptionPane.showMessageDialog(ItemPanel.this,"Selected item to edit");
return;
}
ItemPanel.this.setEditMode();
ItemCRUDPanel.this.setEditMode();
}
else
{
// logic to update : Assignment
// and back to view mode
String name=nameTextField.getText();
String category=categoryTextField.getText();
try
{
ItemInterface itemInterface=new Item();
int selectedRow=itemViewTable.getSelectedRow();
ItemInterface item=itemModel.getItemAt(selectedRow);
int code=item.getCode();
itemInterface.setCode(code);
itemInterface.setName(name);
itemInterface.setCategory(category);
itemModel.update(itemInterface);
}catch(BLException ble)
{
System.out.println(ble);
}
categories=itemModel.getCategories();
categories.add(0,"<ALL>");
filterCategoryComboBox.removeAllItems();
for(String i:categories)
{
filterCategoryComboBox.addItem(i);
}
ItemPanel.this.setViewMode();
ItemCRUDPanel.this.setViewMode();
}
}
});
deleteButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
int selectedRow=itemViewTable.getSelectedRow();
if(selectedRow<0 || selectedRow>=itemModel.getRowCount())
{
JOptionPane.showMessageDialog(ItemPanel.this,"Selected item to Delete");
return;
}
ItemPanel.this.setDeleteMode();
ItemCRUDPanel.this.setDeleteMode();
try
{
ItemInterface item=itemModel.getItemAt(selectedRow);
int code=item.getCode();
itemModel.delete(code);
}catch(BLException ble)
{
System.out.println(ble);
}
categories=itemModel.getCategories();
categories.add(0,"<ALL>");
filterCategoryComboBox.removeAllItems();
for(String i:categories)
{
filterCategoryComboBox.addItem(i);
}
ItemPanel.this.setViewMode();
ItemCRUDPanel.this.setViewMode();
}
});
cancelButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(mode==MODE.ADD_MODE)
{
nameTextField.setText("");
categoryTextField.setText("");
ItemPanel.this.setViewMode();
ItemCRUDPanel.this.setViewMode();
}
if(mode==MODE.EDIT_MODE)
{
nameTextField.setText("");
categoryTextField.setText("");
ItemPanel.this.setViewMode();
ItemCRUDPanel.this.setViewMode();
}
}
});
clearNameTextFieldButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
nameTextField.setText("");
}
});
clearCategoryTextFieldButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
categoryTextField.setText("");
}
});


}
private void setViewMode()
{
nameTextField.setVisible(false);
clearNameTextFieldButton.setVisible(false);
nameLabel.setVisible(true);
categoryTextField.setVisible(false);
clearCategoryTextFieldButton.setVisible(false);
categoryLabel.setVisible(true);
cancelButton.setEnabled(false);
addButton.setEnabled(true);
addButton.setText("A");
editButton.setText("E");
if(itemModel.getRowCount()>0)
{
editButton.setEnabled(true);
deleteButton.setEnabled(true);
exportToPDFButton.setEnabled(true);
}
else
{
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
}
private void setAddMode()
{
nameLabel.setVisible(false);
categoryLabel.setVisible(false);
nameTextField.setText("");
categoryTextField.setText("");
nameTextField.setVisible(true);
categoryTextField.setVisible(true);
clearNameTextFieldButton.setVisible(true);
clearCategoryTextFieldButton.setVisible(true);
addButton.setText("S");
editButton.setEnabled(false);
cancelButton.setEnabled(true);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
private void setEditMode()
{
nameLabel.setVisible(false);
categoryLabel.setVisible(false);
nameTextField.setVisible(true);
categoryTextField.setVisible(true);
clearNameTextFieldButton.setVisible(true);
clearCategoryTextFieldButton.setVisible(true);
addButton.setEnabled(false);
editButton.setText("U");
cancelButton.setEnabled(true);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
private void setDeleteMode()
{
addButton.setEnabled(false);
editButton.setEnabled(false);
cancelButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
private void setExportToPDFMode()
{
addButton.setEnabled(false);
editButton.setEnabled(false);
cancelButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
public void clearItem()
{
this.item=null;
nameLabel.setText("");
categoryLabel.setText("");
nameTextField.setText("");
categoryTextField.setText("");
}
public void setItem(ItemInterface item)
{
this.item=item;
if(this.item==null)
{
clearItem();
return;
}
nameLabel.setText(item.getName());
categoryLabel.setText(item.getCategory());
nameTextField.setText(item.getName());
categoryTextField.setText(item.getCategory());
}
}
// inner class ends
}