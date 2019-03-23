package com.thinking.machine.inventory.bl;
import java.io.*;
public interface ItemInterface extends Comparable<ItemInterface>,Serializable
{
public int getCode();
public void setCode(int code);
public String getName();
public void setName(String name);
public String getCategory();
public void setCategory(String category);
}
