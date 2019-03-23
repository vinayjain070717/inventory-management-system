import java.awt.*;
import java.awt.event.*;
import com.thinking.machine.inventory.dl.*;
class ItemDeleteTestCase extends Frame
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
}
class pspItemDeleteTestCase
{
public static void main(String gg[])
{
ItemDeleteTestCase d=new ItemDeleteTestCase();
}
}
