import com.thinking.machine.inventory.dl.*;
class ItemGetCategoriesCountTestCase
{
public static void main(String gg[])
{
try
{
ItemDAOInterface itemDAOInterface=new ItemDAO();
System.out.println("Number of categories : "+itemDAOInterface.getCategoriesCount());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}