import com.thinking.machine.inventory.dl.*;
class ItemDeleteTestCase
{
public static void main(String data[])
{
try
{
int code=Integer.parseInt(data[0]);
ItemDAOInterface itemDAOInterface=new ItemDAO();
itemDAOInterface.delete(code);
System.out.println("Item deleted");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}