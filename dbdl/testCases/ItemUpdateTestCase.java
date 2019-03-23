import com.thinking.machine.inventory.dl.*;
class ItemUpdateTestCase
{
public static void main(String data[])
{
try
{
int code=Integer.parseInt(data[0]);
String name=data[1];
String category=data[2];
ItemInterface itemInterface;
itemInterface=new Item();
itemInterface.setCode(code);
itemInterface.setName(name);
itemInterface.setCategory(category);
ItemDAOInterface itemDAOInterface=new ItemDAO();
itemDAOInterface.update(itemInterface);
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}