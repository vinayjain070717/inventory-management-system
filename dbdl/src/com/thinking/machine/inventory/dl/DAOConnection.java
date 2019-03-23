package com.thinking.machine.inventory.dl;
import java.sql.*;
import java.io.*;
public class DAOConnection
{
private static  String database;
static
{
try
{
File file=new File("db.conf");
if(file.exists()==false)
{
database=null;
}
else {
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
database=null;
}
else
{
database=randomAccessFile.readLine();
randomAccessFile.close();
}
}
}catch(Exception exception)
{
//database=null;
}
}
public static Connection getConnection()
{
if(database==null) return null;
try
{
Class.forName("org.apache.derby.jdbc.ClientDriver");
Connection connection;
connection=DriverManager.getConnection(database);
return connection;
}catch(Exception exception)
{
return null;
}
}
}
