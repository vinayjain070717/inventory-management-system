import java.awt.*;
import java.awt.event.*;
import com.thinking.machine.inventory.dl.*;
class ItemGetByCategoryTestCase extends Frame implements ActionListener
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
}
class pspItemGetByCategoryTestCase
{
public static void main(String gg[])
{
ItemGetByCategoryTestCase t=new ItemGetByCategoryTestCase();
}
}