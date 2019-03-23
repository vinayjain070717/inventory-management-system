import com.thinking.machine.inventory.dl.*;
class ItemGetByCodeTestCase
{
public static void main(String data[])
{
try
{
int code;
code=Integer.parseInt(data[0]);
ItemDAOInterface itemDAOInterface=new ItemDAO();
ItemInterface itemInterface;
itemInterface=itemDAOInterface.getByCode(code);
System.out.println(itemInterface.getName());
System.out.println(itemInterface.getCategory());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}