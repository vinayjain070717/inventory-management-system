package com.thinking.machine.inventory.dl;
import java.io.*;
public class Item implements ItemInterface
{
private int code;
private String name;
private String category;
public Item()
{
code=0;
name="";
category="";
}
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setCategory(String category)
{
this.category=category;
}
public String getCategory()
{
return this.category;
}
public boolean equals(Object object)
{
if(!(object instanceof Item)) return false;
Item item=(Item)object;
return this.code==item.code;
}
public int compareTo(ItemInterface itemInterface)
{
return this.code-itemInterface.getCode();
}
public int hashCode()
{
return code;
}
}