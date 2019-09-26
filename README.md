# Inventory Management System
This project is basically can be used in restraunt and some other warehourse type application where we have to manage data for store goods and changing records in our system if goods are changed. Also in this you can take all the store goods records in form of pdf at any time you want. Firstly i developed this project with use of file management system in for storing data instead of database and i developed full application with help of this concept after that i developed full application with database at back end also.
So In this application we have following features:

1) Add Data To Inventory
2) Update data from inventory.
3) Delete data from inventory.
4) Search data according to category or in all categories from inventory.
5) Generate pdf of stored data in inventory.

## PREREQUISITE
Front end : Java Swing
Back end: Java
Database: Java db

## INSTALLATION AND RUNNING 
* Firstly fork full project
* If you want to see database management in backend for application then download java db database from (https://www.oracle.com/technetwork/java/javadb/index-jsp-155940.html) otherwise you can use project from file management system for storing data also. This project is developed from both the techiques.
* Make a folder named as inventory management system and copy all the folders in it
* You have two choice if you want to run application from file system then use dl otherwise use dbdl and skip this three four steps of dl.
* Now go to dl folder and make a folder named as classes in it
* Now go to dl/src folder and compile our code in dl with following command
```
javac -d ..\classes com\thinking\machine\inventory\dl\*.java;
```
* Now go to testcases folder and compile and run testcases for testing dl by following commanns.
```
For Compile: javac -classpath ..\classes;. *.java
For running: java -classpath ..\classes;. class-name class-parameter
```
In this class-name may be like ItemAddTestCase,ItemUpdateTestCase etc and in class-parameter you have to see particular java code and see which values this class wants in command line arguments.
* For using application with database make a folder named as db in dbdl and download derby.jar file write following commands.
```
In command prompt(for connection): connet 'jdbc:derby://localhost:1527/inventorydb;create=true';
create table item(
code int primary key generated always as identity(start with 1,increment by 1),
name char(50) not null unique,
category char(35) not null);
```
If there is some problem then use web for understand usage of java db that how it works or otherwise you can use file system and skip this whole dbdl part.
* Now make a folder in dbdl named as classes.
* Now go to dbdl/src folder and compile code of dbdl with followig command
```
javac -d ..\classes -classpath path_of_derby.jar_file;. com\thinking\machine\inventory\dl\*.java;
```
* Now go to testcases folder and compile and run testcases for testing dl by following commanns.
```
For Compile: javac -classpath ..\classes;path_of_derby.jar_file;. *.java
For running: java -classpath ..\classes;path_of_derby.jar_file;. class-name class-parameter
```
In this class-name may be like ItemAddTestCase,ItemUpdateTestCase etc and in class-parameter you have to see particular java code and see which values this class wants in command line arguments.

* Now go to bl folder and make a folder named as classes in it
* Now go to bl/src folder and compile our code in dl with following command
```
javac -d ..\classes -classpath ..\..\dl\classes;. com\thinking\machine\inventory\bl\*.java;
```
* Now go to testcases folder and compile and run testcases for testing dl by following commanns.
```
For Compile: javac -classpath ..\classes;..\..\dl\classes;. *.java
For running: java -classpath ..\classes;..\..\dl\classes;. class-name class-parameter
```
In this class-name may be like ItemAddTestCase,ItemUpdateTestCase etc and in class-parameter you have to see particular java code and see which values this class wants in command line arguments.
* Now go to pl folder and make a folder named as classes in it
* Now go to pl/src folder and compile our code in dl with following command
```
javac -d ..\classes -classpath ..\..\dl\classes;..\..\bl\classes;. com\thinking\machine\inventory\ma\*.java;
javac -d ..\classes -classpath ..\..\dl\classes;..\..\bl\classes;. com\thinking\machine\inventory\pl\*.java;
```
* Now its time to run full application so for this you have to go to classes folder and type the following command.
```
java -classpath ..\..\dl\classes;..\..\bl\classes;. com.thinking.machines.inventory.ma.Main
```
