import java.awt.*;
import java.awt.event.*;
import com.thinking.machine.inventory.dl.*;
interface CrossButtonListener
{
public void windowClosing(WindowEvent e);
}
class CrossButtonHandler extends WindowAdapter
{
private CrossButtonListener target;
CrossButtonHandler(CrossButtonListener t)
{
target=t;
}
public void windowClosing(WindowEvent ev)
{
target.windowClosing(ev);
}
}
class ItemAddTestCase extends Frame implements CrossButtonListener
{
private Label itemNameLabel,itemCatLabel,l5;
private Label moduleTitleLabel,statusBarLabel;
private TextField itemName,itemCategory;
private Button add;
ItemAddTestCase()
{
itemNameLabel=new Label("item Name");
itemCatLabel=new Label("item Category");
l5=new Label(" ");
moduleTitleLabel=new Label("Item (Add Module)");
java.util.Date d=new java.util.Date();
String today=d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()+1900);
statusBarLabel=new Label("Date : "+today);
itemName=new TextField(30);
itemCategory=new TextField(30);
add=new Button("ADD");
Panel p1=new Panel();
p1.setLayout(new GridLayout(1,3));
p1.add(new Label(" "));
p1.add(moduleTitleLabel);
p1.add(new Label(" "));
Panel p3=new Panel();
p3.setLayout(new GridLayout(4,2));
p3.add(itemNameLabel);
p3.add(itemName);
p3.add(itemCatLabel);
p3.add(itemCategory);
Panel p4=new Panel();
p4.setLayout(new GridLayout(1,3));
p4.add(new Label(" "));
p4.add(add);
p4.add(l5);
Panel p5=new Panel();
p5.setLayout(new BorderLayout());
p5.add(p3,BorderLayout.CENTER);
p5.add(p4,BorderLayout.SOUTH);
setLayout(new GridLayout(1,2));
add(moduleTitleLabel);
add(p5);
add(statusBarLabel);
CrossButtonHandler ch;
ch=new CrossButtonHandler(this);
addWindowListener(ch);
setVisible(true);
setSize(800,450);
setLocation(0,0);
add.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ev)
{
try
{
String name=itemName.getText();
String category=itemCategory.getText();
ItemInterface itemInterface;
itemInterface=new Item();
itemInterface.setName(name);
itemInterface.setCategory(category);
ItemDAOInterface itemDAOInterface;
itemDAOInterface=new ItemDAO();
itemDAOInterface.add(itemInterface);
l5.setText("Item added with code as : "+itemInterface.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
});
}
public void windowClosing(WindowEvent ev)
{
setVisible(false);
}
}
class ItemDeleteTestCase extends Frame implements CrossButtonListener
{
private Label moduleTitleLabel,statusBarLabel;
private Label ItemCodeLabel;
private TextField ItemCode;
private Button Delete;
private Label l1;
ItemDeleteTestCase()
{
moduleTitleLabel=new Label("Delete module ");
ItemCodeLabel=new Label("Code");
ItemCode=new TextField(30);
Delete=new Button("Delete");
l1=new Label(" ");
Panel p1=new Panel();
p1.setLayout(new GridLayout(1,3));
p1.add(new Label(" "));
p1.add(moduleTitleLabel);
p1.add(new Label(" "));
Panel p2=new Panel();
p2.setLayout(new GridLayout(1,2));
p2.add(ItemCodeLabel);
p2.add(ItemCode);
Panel p3=new Panel();
p3.setLayout(new GridLayout(1,3));
p3.add(new Label(" "));
p3.add(Delete);
p3.add(l1);
Panel p4=new Panel();
p4.setLayout(new BorderLayout());
p4.add(p2,BorderLayout.CENTER);
p4.add(p3,BorderLayout.SOUTH);
setLayout(new BorderLayout());
add(p1,BorderLayout.NORTH);
add(p4,BorderLayout.CENTER);
CrossButtonHandler ch;
ch=new CrossButtonHandler(this);
addWindowListener(ch);
setLocation(10,10);
setSize(800,600);
setVisible(true);
Delete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e)
{
try
{
int code=Integer.parseInt(ItemCode.getText());
ItemDAOInterface itemDAOInterface=new ItemDAO();
itemDAOInterface.delete(code);
l1.setText("Item deleted with code as :"+code);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
});
}
public void windowClosing(WindowEvent ev)
{
setVisible(false);
}
}
class ItemUpdateTestCase extends Frame implements CrossButtonListener
{
private Label lT,lC,lN,lCa,l5;
private TextField tC,tN,tCa;
private Button b;
ItemUpdateTestCase()
{
lT=new Label("Update module");
lC=new Label("Code");
lN=new Label("Name");
lCa=new Label("Category");
l5=new Label(" ");
tC=new TextField(20);
tN=new TextField(20);
tCa=new TextField(20);
b=new Button("Update");
Panel p1=new Panel();
p1.setLayout(new GridLayout(1,3));
p1.add(new Label(" "));
p1.add(lT);
p1.add(new Label(" "));
Panel p2=new Panel();
p2.setLayout(new GridLayout(3,2));
p2.add(lC);
p2.add(tC);
p2.add(lN);
p2.add(tN);
p2.add(lCa);
p2.add(tCa);
Panel p3=new Panel();
p3.setLayout(new GridLayout());
p3.add(new Label(" "));
p3.add(b);
p3.add(l5);
setLayout(new BorderLayout());
add(p1,BorderLayout.NORTH);
add(p2,BorderLayout.CENTER);
add(p3,BorderLayout.SOUTH);
CrossButtonHandler ch;
ch=new CrossButtonHandler(this);
addWindowListener(ch);
setSize(400,600);
setLocation(0,0);
setVisible(true);
b.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ev)
{
try
{
int code=Integer.parseInt(tC.getText());
String name=tN.getText();
String category=tCa.getText();
ItemInterface itemInterface;
itemInterface=new Item();
itemInterface.setCode(code);
itemInterface.setName(name);
itemInterface.setCategory(category);
ItemDAOInterface itemDAOInterface=new ItemDAO();
itemDAOInterface.update(itemInterface);
l5.setText("Item updated with code as :"+itemInterface.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
});
}
public void windowClosing(WindowEvent ev)
{
setVisible(false);
}
}
class ItemGetByCodeTestCase extends Frame implements ActionListener,CrossButtonListener
{
private Label code,l1,l2;
private TextField tC;
private Button b;
ItemGetByCodeTestCase()
{
l1=new Label("ItemGetByCode module");
code=new Label("Code");
l2=new Label(" ");
tC=new TextField(30);
b=new Button("GetByCode");
Panel p1=new Panel();
p1.setLayout(new GridLayout(1,3));
p1.add(new Label(" "));
p1.add(l1);
p1.add(new Label(" "));
Panel p2=new Panel();
p2.setLayout(new GridLayout(2,2));
p2.add(code);
p2.add(tC);
p2.add(b);
b.addActionListener(this);
setLayout(new BorderLayout());
add(p1,BorderLayout.NORTH);
add(p2,BorderLayout.CENTER);
add(l2,BorderLayout.SOUTH);
CrossButtonHandler ch;
ch=new CrossButtonHandler(this);
addWindowListener(ch);
setSize(400,500);
setLocation(10,10);
setVisible(true);
}
public void actionPerformed(ActionEvent e)
{
try
{
int code;
code=Integer.parseInt(tC.getText());
ItemDAOInterface itemDAOInterface=new ItemDAO();
ItemInterface itemInterface;
itemInterface=itemDAOInterface.getByCode(code);
l2.setText("Code : "+itemInterface.getCode()+"Name : "+itemInterface.getName()+"Category : "+itemInterface.getCategory());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
public void windowClosing(WindowEvent ev)
{
setVisible(false);
}
}
class ItemGetByNameTestCase extends Frame implements ActionListener,CrossButtonListener
{
private Label code,l1,l2;
private TextField tC;
private Button b;
ItemGetByNameTestCase()
{
l1=new Label("ItemGetByName module");
code=new Label("Name");
l2=new Label(" ");
tC=new TextField(30);
b=new Button("GetByName");
Panel p1=new Panel();
p1.setLayout(new GridLayout(1,3));
p1.add(new Label(" "));
p1.add(l1);
p1.add(new Label(" "));
Panel p2=new Panel();
p2.setLayout(new GridLayout(2,2));
p2.add(code);
p2.add(tC);
p2.add(b);
b.addActionListener(this);
setLayout(new BorderLayout());
add(p1,BorderLayout.NORTH);
add(p2,BorderLayout.CENTER);
add(l2,BorderLayout.SOUTH);
CrossButtonHandler ch;
ch=new CrossButtonHandler(this);
addWindowListener(ch);
setSize(400,500);
setLocation(10,10);
setVisible(true);
}
public void actionPerformed(ActionEvent e)
{
try
{
String name;
name=tC.getText();
ItemDAOInterface itemDAOInterface=new ItemDAO();
ItemInterface itemInterface;
itemInterface=itemDAOInterface.getByName(name);
l2.setText("Code : "+itemInterface.getCode()+"Name : "+itemInterface.getName()+"Category : "+itemInterface.getCategory());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
public void windowClosing(WindowEvent ev)
{
setVisible(false);
}
}
class ItemGetByCategoryTestCase extends Frame implements ActionListener,CrossButtonListener
{
private Label category,l1,l2;
private TextField tC;
private Button b;
ItemGetByCategoryTestCase()
{
l1=new Label("ItemGetByName module");
category=new Label("Category");
l2=new Label(" ");
tC=new TextField(30);
b=new Button("GetByCategory");
Panel p1=new Panel();
p1.setLayout(new GridLayout(1,3));
p1.add(new Label(" "));
p1.add(l1);
p1.add(new Label(" "));
Panel p2=new Panel();
p2.setLayout(new GridLayout(2,2));
p2.add(category);
p2.add(tC);
p2.add(b);
b.addActionListener(this);
setLayout(new BorderLayout());
add(p1,BorderLayout.NORTH);
add(p2,BorderLayout.CENTER);
add(l2,BorderLayout.SOUTH);
CrossButtonHandler ch;
ch=new CrossButtonHandler(this);
addWindowListener(ch);
setSize(400,500);
setLocation(10,10);
setVisible(true);
}
public void actionPerformed(ActionEvent e)
{
String category=tC.getText();
try
{
java.util.List<ItemInterface> items;
ItemDAOInterface itemDAOInterface=new ItemDAO();
items=itemDAOInterface.getByCategory(category);
for(ItemInterface i:items)
{
l2.setText(i.getCode()+","+i.getName()+","+i.getCategory());
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
public void windowClosing(WindowEvent ev)
{
setVisible(false);
}
}
class ItemGetAllTestCase extends Frame implements ActionListener,CrossButtonListener
{
private List opt;
private Label l1;
private Button b;
ItemGetAllTestCase()
{
opt=new List(4,true);
l1=new Label("ItemGetByName module");
b=new Button("GetAll");
Panel p1=new Panel();
p1.setLayout(new GridLayout(1,3));
p1.add(new Label(" "));
p1.add(l1);
p1.add(new Label(" "));
Panel p2=new Panel();
p2.setLayout(new GridLayout(1,3));
p2.add(new Label(" "));
p2.add(b);
p2.add(new Label(" "));
b.addActionListener(this);
setLayout(new BorderLayout());
add(p1,BorderLayout.NORTH);
add(p2,BorderLayout.CENTER);
add(opt,BorderLayout.SOUTH);
CrossButtonHandler ch;
ch=new CrossButtonHandler(this);
addWindowListener(ch);
setSize(400,500);
setLocation(10,10);
setVisible(true);
}
public void actionPerformed(ActionEvent e)
{
try
{
java.util.List<ItemInterface> items;
ItemDAOInterface itemDAOInterface=new ItemDAO();
items=itemDAOInterface.getAll();
for(ItemInterface i:items)
{
opt.add(i.getCode()+","+i.getName()+","+i.getCategory());
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
public void windowClosing(WindowEvent ev)
{
setVisible(false);
}
}
class ItemGetCategoriesCountTestCase extends Frame implements ActionListener,CrossButtonListener
{
private Label l1,l2;
private Button b;
ItemGetCategoriesCountTestCase()
{
l1=new Label("ItemGetByName module");
l2=new Label(" ");
b=new Button("GetCategoriesCount");
Panel p1=new Panel();
p1.setLayout(new GridLayout(1,3));
p1.add(new Label(" "));
p1.add(l1);
p1.add(new Label(" "));
Panel p2=new Panel();
p2.setLayout(new GridLayout(1,3));
p2.add(new Label(" "));
p2.add(b);
p2.add(new Label(" "));
b.addActionListener(this);
setLayout(new BorderLayout());
add(p1,BorderLayout.NORTH);
add(p2,BorderLayout.CENTER);
add(l2,BorderLayout.SOUTH);
CrossButtonHandler ch;
ch=new CrossButtonHandler(this);
addWindowListener(ch);
setSize(400,500);
setLocation(10,10);
setVisible(true);
}
public void actionPerformed(ActionEvent e)
{
try
{
ItemDAOInterface itemDAOInterface=new ItemDAO();
l2.setText("Number of categories : "+itemDAOInterface.getCategoriesCount());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
public void windowClosing(WindowEvent e)
{
setVisible(false);
}
}
class ItemGetCategoriesTestCase extends Frame implements ActionListener,CrossButtonListener
{
private List opt;
private Label l1;
private Button b;
ItemGetCategoriesTestCase()
{
opt=new List(4,true);
l1=new Label("ItemGetCategories module");
b=new Button("GetCategories");
Panel p1=new Panel();
p1.setLayout(new GridLayout(1,3));
p1.add(new Label(" "));
p1.add(l1);
p1.add(new Label(" "));
Panel p2=new Panel();
p2.setLayout(new GridLayout(1,3));
p2.add(new Label(" "));
p2.add(b);
p2.add(new Label(" "));
b.addActionListener(this);
setLayout(new BorderLayout());
add(p1,BorderLayout.NORTH);
add(p2,BorderLayout.CENTER);
add(opt,BorderLayout.SOUTH);
CrossButtonHandler ch;
ch=new CrossButtonHandler(this);
addWindowListener(ch);
setSize(400,500);
setLocation(10,10);
setVisible(true);
}
public void actionPerformed(ActionEvent e)
{
try
{
java.util.List<String> categories;
ItemDAOInterface itemDAOInterface=new ItemDAO();
categories=itemDAOInterface.getCategories();
for(String c:categories)
{
opt.add(c);
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
public void windowClosing(WindowEvent e)
{
setVisible(false);
}
}
class ItemGetCountTestCase extends Frame implements ActionListener,CrossButtonListener
{
private Label l1,l2;
private Button b;
ItemGetCountTestCase()
{
l1=new Label("ItemGetCount module");
l2=new Label(" ");
b=new Button("GetCount");
Panel p1=new Panel();
p1.setLayout(new GridLayout(1,3));
p1.add(new Label(" "));
p1.add(l1);
p1.add(new Label(" "));
Panel p2=new Panel();
p2.setLayout(new GridLayout(1,3));
p2.add(new Label(" "));
p2.add(b);
p2.add(new Label(" "));
b.addActionListener(this);
setLayout(new BorderLayout());
add(p1,BorderLayout.NORTH);
add(p2,BorderLayout.CENTER);
add(l2,BorderLayout.SOUTH);
CrossButtonHandler ch;
ch=new CrossButtonHandler(this);
addWindowListener(ch);
setSize(400,500);
setLocation(10,10);
setVisible(true);
}
public void actionPerformed(ActionEvent e)
{
try
{
ItemDAOInterface itemDAOInterface=new ItemDAO();
l2.setText("Number of items : "+itemDAOInterface.getCount());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
public void windowClosing(WindowEvent e)
{
setVisible(false);
}
}



class MainMenu extends Frame implements ActionListener,CrossButtonListener
{
private MenuBar mb;
private Menu m1,m2,m3;
private MenuItem addMenuItem,delete,update,getByCode,getByName,getByCategory,getAll,getCategoriesCount,getCategories,getCount;
MainMenu()
{
addMenuItem=new MenuItem("ItemAddTestCase");
addMenuItem.addActionListener(this);
delete=new MenuItem("ItemDeleteTestCase");
delete.addActionListener(this);
update=new MenuItem("ItemUpdateTestCase");
update.addActionListener(this);
getByCode=new MenuItem("ItemGetByCodeTestCase");
getByCode.addActionListener(this);
getByName=new MenuItem("ItemGetByNameTestCase");
getByName.addActionListener(this);
getByCategory=new MenuItem("ItemGetByCategoryTestCase");
getByCategory.addActionListener(this);
getAll=new MenuItem("ItemGetAllTestCase");
getAll.addActionListener(this);
getCategoriesCount=new MenuItem("ItemGetCategoriesCountTestCase");
getCategoriesCount.addActionListener(this);
getCategories=new MenuItem("ItemGetCategoriesTestCase");
getCategories.addActionListener(this);
getCount=new MenuItem("ItemGetCountTestCase");
getCount.addActionListener(this);
m1=new Menu("Options");
m2=new Menu("Item");
m3=new Menu("Help");
m2.add(addMenuItem);
m2.add(delete);
m2.add(update);
m2.add(getByCode);
m2.add(getByName);
m2.add(getByCategory);
m2.add(getAll);
m2.add(getCategoriesCount);
m2.add(getCategories);
m2.add(getCount);
m1.add(m2);
mb=new MenuBar();
mb.add(m1);
mb.add(m3);
setMenuBar(mb);
addWindowListener(new CrossButtonHandler(this));
setLocation(10,10);
setSize(500,500);
setVisible(true);
}
public void windowClosing(WindowEvent e)
{
System.exit(0);
}
public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==addMenuItem)
{
ItemAddTestCase i=new ItemAddTestCase();
}
if(ev.getSource()==delete)
{
ItemDeleteTestCase d=new ItemDeleteTestCase();
}
if(ev.getSource()==update)
{
ItemUpdateTestCase u=new ItemUpdateTestCase();
}
if(ev.getSource()==getByCode)
{
ItemGetByCodeTestCase g=new ItemGetByCodeTestCase();
}
if(ev.getSource()==getByName)
{
ItemGetByNameTestCase g=new ItemGetByNameTestCase();
}
if(ev.getSource()==getByCategory)
{
ItemGetByCategoryTestCase g=new ItemGetByCategoryTestCase();
}
if(ev.getSource()==getAll)
{
ItemGetAllTestCase g=new ItemGetAllTestCase();
}
if(ev.getSource()==getCategoriesCount)
{
ItemGetCategoriesCountTestCase g=new ItemGetCategoriesCountTestCase();
}
if(ev.getSource()==getCategories)
{
ItemGetCategoriesTestCase g=new ItemGetCategoriesTestCase();
}
if(ev.getSource()==getCount)
{
ItemGetCountTestCase g=new ItemGetCountTestCase();
}
}
}
class pspMenu
{
public static void main(String gg[])
{
MainMenu m=new MainMenu();
}
}