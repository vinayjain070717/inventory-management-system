import com.thinking.machine.inventory.dl.*;
class ItemAddTestCase
{
public static void main(String data[])
{
try
{
String name=data[0];
String category=data[1];
ItemInterface itemInterface;
itemInterface=new Item();
itemInterface.setName(name);
itemInterface.setCategory(category);
ItemDAOInterface itemDAOInterface=new ItemDAO();
itemDAOInterface.add(itemInterface);
System.out.println("Item added with code as:"+itemInterface.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}