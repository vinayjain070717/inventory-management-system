import java.util.*;
import com.thinking.machine.inventory.dl.*;
class ItemGetAllTestCase
{
public static void main(String data[])
{
try
{
List<ItemInterface> items;
ItemDAOInterface itemDAOInterface=new ItemDAO();
items=itemDAOInterface.getAll();
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