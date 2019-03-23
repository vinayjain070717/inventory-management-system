import com.thinking.machine.inventory.dl.*;
class ItemGetByNameTestCase
{
public static void main(String data[])
{
String name=data[0];
try
{
ItemDAOInterface itemDAOInterface=new ItemDAO();
ItemInterface itemInterface=itemDAOInterface.getByName(name);
System.out.println(itemInterface.getCode()+","+itemInterface.getName()+","+itemInterface.getCategory());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
