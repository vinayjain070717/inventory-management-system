package com.thinking.machine.inventory.dl;
import java.util.*;
import java.sql.*;
public class ItemDAO implements ItemDAOInterface
{
public void add(ItemInterface itemInterface) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
if(connection==null)
{
throw new DAOException("Unable to connect, db.conf missing or invalid entry in db.conf");
}
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select code from item where name=?");
preparedStatement.setString(1,itemInterface.getName());
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Item : "+itemInterface.getName()+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into item (name,category) values(?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,itemInterface.getName());
preparedStatement.setString(2,itemInterface.getCategory());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
itemInterface.setCode(resultSet.getInt(1));
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException); // remove after testing
throw new DAOException(sqlException.getMessage());
}
}
public void update(ItemInterface itemInterface) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
if(connection==null)
{
throw new DAOException("Unable to connect, db.conf missing or invalid entry in db.conf");
}
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select code from item where name=?");
preparedStatement.setString(1,itemInterface.getName());
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Item : "+itemInterface.getName()+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("Update item set name=?,category=? where code=?",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,itemInterface.getName());
preparedStatement.setString(2,itemInterface.getCategory());
preparedStatement.setString(3,String.valueOf(itemInterface.getCode()));
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
public void delete(int code) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
if(connection==null)
{
throw new DAOException("Unable to connect db.conf missing or invalid entry in db.conf");
}
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from item where code=?");
preparedStatement.setString(1,String.valueOf(code));
ResultSet resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Item code : "+code+" not exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from item where code=?");
preparedStatement.setString(1,String.valueOf(code));
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
public ItemInterface getByCode(int code) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
if(connection==null)
{
throw new DAOException("Unable to connect db.conf missing or invalid entry in db.conf");
}
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from item where code=?");
preparedStatement.setString(1,String.valueOf(code));
ResultSet resultSet=preparedStatement.executeQuery();
ItemInterface itemInterface;
while(resultSet.next())
{
itemInterface=new Item();
itemInterface.setCode(resultSet.getInt("code"));
itemInterface.setName(resultSet.getString("name").trim());
itemInterface.setCategory(resultSet.getString("category").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return itemInterface;
}
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Item code : "+code+" not exists");
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
public ItemInterface getByName(String name) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
if(connection==null)
{
throw new DAOException("Unable to connect db.conf missing or invalid entry in db.conf");
}
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from item where name=?");
preparedStatement.setString(1,name);
ResultSet resultSet=preparedStatement.executeQuery();
ItemInterface itemInterface;
while(resultSet.next())
{
itemInterface=new Item();
itemInterface.setCode(resultSet.getInt("code"));
itemInterface.setName(resultSet.getString("name").trim());
itemInterface.setCategory(resultSet.getString("category").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return itemInterface;
}
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Item name : "+name+" not exists");
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
public List<String> getCategories() throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select distinct category from item");
List<String> items=new LinkedList<String>();
while(resultSet.next())
{
items.add(resultSet.getString("category").trim());
}
resultSet.close();
statement.close();
connection.close();
return items;
}catch(SQLException sqlException)
{
System.out.println(sqlException); // remove after testing
throw new DAOException(sqlException.getMessage());
}
}
public List<ItemInterface> getAll() throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select * from item");
List<ItemInterface> items=new LinkedList<ItemInterface>();
ItemInterface itemInterface;
while(resultSet.next())
{
itemInterface=new Item();
itemInterface.setCode(resultSet.getInt("code"));
itemInterface.setName(resultSet.getString("name").trim());
itemInterface.setCategory(resultSet.getString("category").trim());
items.add(itemInterface);
}
resultSet.close();
statement.close();
connection.close();
return items;
}catch(SQLException sqlException)
{
System.out.println(sqlException); // remove after testing
throw new DAOException(sqlException.getMessage());
}
}
public List<ItemInterface> getByCategory(String category) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
if(connection==null)
{
throw new DAOException("Unable to connect db.conf missing or invalid entry in db.conf");
}
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from item where category=?");
preparedStatement.setString(1,category);
ResultSet resultSet=preparedStatement.executeQuery();
ItemInterface itemInterface;
List<ItemInterface> items=new LinkedList<ItemInterface>();
while(resultSet.next())
{
itemInterface=new Item();
itemInterface.setCode(resultSet.getInt("code"));
itemInterface.setName(resultSet.getString("name").trim());
itemInterface.setCategory(resultSet.getString("category").trim());
items.add(itemInterface);
}
if(items.size()==0)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Item not found");
}
resultSet.close();
preparedStatement.close();
connection.close();
return items;
}catch(SQLException sqlException)
{
System.out.println(sqlException);
throw new DAOException(sqlException.getMessage());
}
}
public long getCategoriesCount() throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select distinct category from item");
List<String> items=new LinkedList<String>();
while(resultSet.next())
{
items.add(resultSet.getString("category").trim());
}
resultSet.close();
statement.close();
connection.close();
return items.size();
}catch(SQLException sqlException)
{
System.out.println(sqlException); // remove after testing
throw new DAOException(sqlException.getMessage());
}
}
public long getCount() throws DAOException
{
throw new DAOException("Not yet implemented");
}
}