package com.thinking.machine.inventory.pl;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
public class ItemFrame extends JFrame
{
private ItemPanel itemPanel;
private Container container;
public ItemFrame()
{
initComponents();
setAppearance();
}
private void initComponents()
{
this.itemPanel=new ItemPanel();
}
private void setAppearance()
{
setTitle("Inventory Automation");
container=getContentPane();
container.setLayout(null);
setSize(600,650);
int lm,tm;
lm=0;
tm=0;
int titleBarHeight=40;
int borderWidths=16;
itemPanel.setBounds(lm+2,tm+2,getWidth()-((lm+2)*2)-borderWidths,getHeight()-((tm+2)*2)-titleBarHeight);
itemPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
container.add(itemPanel);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation(d.width/2-getWidth()/2,d.height/2-getHeight()/2);
setVisible(true);
}
}
