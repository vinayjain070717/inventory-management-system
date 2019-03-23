import java.awt.*;
import java.awt.event.*;
import com.thinking.machine.inventory.dl.*;
class ItemGetAllTestCase extends Frame implements ActionListener
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
}
class pspItemGetAllTestCase
{
public static void main(String gg[])
{
ItemGetAllTestCase t=new ItemGetAllTestCase();
}
}