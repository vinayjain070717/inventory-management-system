import java.awt.*;
import java.awt.event.*;
import com.thinking.machine.inventory.dl.*;
class ItemGetByNameTestCase extends Frame implements ActionListener
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
}
class pspItemGetByNameTestCase
{
public static void main(String gg[])
{
ItemGetByNameTestCase t=new ItemGetByNameTestCase();
}
}