import com.thinking.machine.inventory.dl.*;
class ItemGetCountTestCase
{
public static void main(String data[])
{
try
{
ItemDAOInterface itemDAOInterface=new ItemDAO();
System.out.println("Number of items : "+itemDAOInterface.getCount());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
