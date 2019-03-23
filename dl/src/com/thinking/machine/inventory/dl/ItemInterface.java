package com.thinking.machine.inventory.dl;
import java.io.*;
public interface ItemInterface extends Comparable<ItemInterface>,Serializable
{
public void setCode(int code);
public int getCode();
public void setName(String name);
public String getName();
public void setCategory(String category);
public String getCategory();
}