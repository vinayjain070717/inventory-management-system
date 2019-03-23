package com.thinking.machine.inventory.bl;
public class Item implements ItemInterface
{
private int code;
private String name;
private String category;
public Item()
{
this.code=0;
this.name="";
this.category="";
}
public int getCode()
{
return this.code;
}
public void setCode(int code)
{
this.code=code;
}
public String getName()
{
return this.name;
}
public void setName(String name)
{
this.name=name;
}
public String getCategory()
{
return this.category;
}
public void setCategory(String category)
{
this.category=category;
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