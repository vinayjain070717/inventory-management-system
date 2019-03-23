import java.awt.*;
import java.awt.event.*;
import com.thinking.machine.inventory.dl.*;
import java.io.*;
class ItemUpdateTestCase extends Frame
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
}
class pspItemUpdateTestCase
{
public static void main(String gg[])
{
ItemUpdateTestCase i=new ItemUpdateTestCase();
}
}