import com.thinking.machine.inventory.bl.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
class ItemViewTestCase extends JFrame
{
private JTextField code;
private JTextField name;
private JTextField category;
private JButton add;
private JButton update,delete;
private ItemModel itemModel;
private JTable jtb;
private JScrollPane jsp;
ItemViewTestCase()
{
itemModel=new ItemModel();
Container c=getContentPane();
jtb=new JTable(itemModel);
jsp=new JScrollPane(jtb,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
c.setLayout(new BorderLayout());
c.add(jsp);
code=new JTextField(20);
name=new JTextField(20);
category=new JTextField(20);
add=new JButton("Add");
update=new JButton("Update");
delete=new JButton("Delete");
JPanel p1=new JPanel();
p1.setLayout(new GridLayout(6,1));
p1.add(code);
p1.add(name);
p1.add(category);
p1.add(add);
p1.add(update);
p1.add(delete);
c.add(p1,BorderLayout.SOUTH);
add.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
ItemInterface i=new Item();
i.setName(name.getText());
i.setCategory(category.getText());
try
{
ItemViewTestCase.this.itemModel.add(i);
}catch(BLException blException)
{
JOptionPane.showMessageDialog(ItemViewTestCase.this,blException.getMessage());
}
}
});
c.add(p1,BorderLayout.SOUTH);
update.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
ItemInterface i=new Item();
i.setCode(Integer.parseInt(code.getText()));
i.setName(name.getText());
i.setCategory(category.getText());
try
{
ItemViewTestCase.this.itemModel.update(i);
}catch(BLException blException)
{
JOptionPane.showMessageDialog(ItemViewTestCase.this,blException.getMessage());
}
}
});
c.add(p1,BorderLayout.SOUTH);
delete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
int c1;
c1=Integer.parseInt(code.getText());
try
{
ItemViewTestCase.this.itemModel.delete(c1);
}catch(BLException blException)
{
JOptionPane.showMessageDialog(ItemViewTestCase.this,blException.getMessage());
}
}
});

setLocation(10,10);
setSize(500,400);
setVisible(true);
}
public static void main(String gg[])
{
ItemViewTestCase ivtc=new ItemViewTestCase();
}
}
