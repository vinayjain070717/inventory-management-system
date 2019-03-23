import java.awt.*;
import java.awt.event.*;
import com.thinking.machine.inventory.dl.*;
class ItemGetByCodeTestCase extends Frame implements ActionListener
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
}
class pspItemGetByCodeTestCase
{
public static void main(String gg[])
{
ItemGetByCodeTestCase t=new ItemGetByCodeTestCase();
}
}