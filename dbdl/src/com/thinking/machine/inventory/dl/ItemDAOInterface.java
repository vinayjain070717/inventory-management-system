package com.thinking.machine.inventory.dl;
import java.util.*;
public interface ItemDAOInterface
{
public void add(ItemInterface itemInterface) throws DAOException;
public void update(ItemInterface itemInterface) throws DAOException;
public void delete(int code) throws DAOException;
public ItemInterface getByCode(int code) throws DAOException;
public ItemInterface getByName(String name) throws DAOException;
public List<ItemInterface> getAll() throws DAOException;
public List<ItemInterface> getByCategory(String category) throws DAOException;
public long getCount() throws DAOException;
public long getCategoriesCount() throws DAOException;
public List<String> getCategories() throws DAOException;
}
