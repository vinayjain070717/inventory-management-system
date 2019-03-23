import java.awt.*;
import java.awt.event.*;
import com.thinking.machine.inventory.dl.*;
class ItemGetCategoriesTestCase extends Frame implements ActionListener
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
}
class pspItemGetCategoriesTestCase
{
public static void main(String gg[])
{
ItemGetCategoriesTestCase t=new ItemGetCategoriesTestCase();
}
}