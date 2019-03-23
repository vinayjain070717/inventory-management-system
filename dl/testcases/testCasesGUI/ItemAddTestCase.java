import java.awt.*;
import java.awt.event.*;
import com.thinking.machine.inventory.dl.*;
class ItemAddTestCase extends Frame
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
}
class pspItemAddTestCase
{
public static void main(String gg[])
{
ItemAddTestCase i=new ItemAddTestCase();
}
}