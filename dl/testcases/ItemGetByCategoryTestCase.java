import java.util.*;
import com.thinking.machine.inventory.dl.*;
class ItemGetByCategoryTestCase
{
public static void main(String data[])
{
String category=data[0];
try
{
List<ItemInterface> items;
ItemDAOInterface itemDAOInterface=new ItemDAO();
items=itemDAOInterface.getByCategory(category);
for(ItemInterface i:items)
{
System.out.println(i.getCode()+","+i.getName()+","+i.getCategory());
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}