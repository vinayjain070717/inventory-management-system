import java.awt.*;
import java.awt.event.*;
import com.thinking.machine.inventory.dl.*;
class ItemGetCountTestCase extends Frame implements ActionListener
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
}
class pspItemGetCountTestCase
{
public static void main(String gg[])
{
ItemGetCountTestCase t=new ItemGetCountTestCase();
}
}