import java.util.*;
import com.thinking.machine.inventory.dl.*;
class ItemGetCategoriesTestCase
{
public static void main(String data[])
{
try
{
List<String> categories;
ItemDAOInterface itemDAOInterface=new ItemDAO();
categories=itemDAOInterface.getCategories();
for(String c:categories)
{
System.out.println(c);
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}