package DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUtil {
	@SuppressWarnings("finally")
	//执行非查询语句（delete、update、insert）
	public static int executeNonQuery(String sqlCmd,Object[] objList)
	{
		Connection conn=null;//数据库连接对象		
		Statement sm=null;//可执行sql语句的Statement对象
		int result=-1;//返回结果，-1为数据操作失败，非-1为操作成功
		try {
			conn=DBUtil.ConnectionFactory.getConnection();//获取数据库连接对象
			if(objList!=null)//判断是否有传入参数（也就是判断 Object[] objList 是否为空）
			{
				PreparedStatement pStatement=conn.prepareStatement(sqlCmd);//创建可执行带参数SQL命令 pStatement对象
				//对参数进行赋值
				for(int i=0;i<objList.length;i++)
				{
					pStatement.setObject(i+1, objList[i]);//获取传入参数的值
				}
				result=pStatement.executeUpdate();//执行相应命令
			}
			else {//采用字符串拼接方式
				conn=DBUtil.ConnectionFactory.getConnection();//获取数据库连接对象
				sm=conn.createStatement();//创建可执行sql语句的Statement对象
				result=sm.executeUpdate(sqlCmd);//执行相应sql命令
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DBUtil.CloseFactory.close(conn,sm);//关闭相应资源
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	//执行查询操作 返回List型的数据集合   （如select *from table/select name,age from table）
	public static List<Object> executeQuery(String sqlCmd,Object[] objList)
	{
		Connection conn=null;
		Statement sm=null;
		ResultSet rSet=null;
		List<Object> list=new ArrayList<Object>();
		try
		{
			conn=DBUtil.ConnectionFactory.getConnection();//获取数据库连接对象
			if(objList!=null)//判断是否有参数传入
			{
				PreparedStatement pStatement=conn.prepareStatement(sqlCmd);//生成PreparedStatement，用于执行T-SQL命令
				for(int i=0;i<objList.length;i++)
				{
					pStatement.setObject(i+1, objList[i]);
				}
				rSet=pStatement.executeQuery();//执行查询命令，返回ResultSet
				ResultSetMetaData rsmdData=rSet.getMetaData();
				int column=rsmdData.getColumnCount();//获取返回的单条数据的字段数目
				while(rSet.next())
				{
					Object[] object=new Object[column];//对象数值，用于作为获取单条数据的载体
					for(int i=1;i<=column;i++)
					{
						object[i-1]=rSet.getObject(i);
					}
					list.add(object);//将获取的数据添加到集合中
				}
			}
			else {
				System.out.println("我进入空的之中了");
				//conn=DBUtil.ConnectionFactory.getConnection();//获取数据库连接对象
				sm=conn.createStatement();//创建Statement对象
				rSet=sm.executeQuery(sqlCmd);//执行查询查询命令
				System.out.println("rSet ="+rSet);
				ResultSetMetaData rsmdData=rSet.getMetaData();
				int column=rsmdData.getColumnCount();//获取单条数据中属性个数（如 select name,age from employee）属性为：name,age
				while(rSet.next())
				{
					Object[] object=new Object[column];
					for(int i=1;i<=column;i++)
					{
						object[i-1]=rSet.getObject(i);
					}
					list.add(object);//将相应数据添加到集合中
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			list=null;
		}
		finally{
			DBUtil.CloseFactory.close(conn,sm,rSet);
			return list;
		}
	}
	

	//执行标量操作，返回首行首列的数据   select count(*) from table/select name from table
	@SuppressWarnings("finally")
	public static Object excuteScalar(String sqlCmd,Object[] objList)
	{
		Connection conn=null;
		Statement sm=null;
		ResultSet rSet=null;
		Object obj=null;
		try {
			conn=DBUtil.ConnectionFactory.getConnection();
			if(objList!=null)
			{
				PreparedStatement pStatement=conn.prepareStatement(sqlCmd);
				for(int i=0;i<objList.length;i++)
				{
					pStatement.setObject(i+1, objList[i]);
				}
				rSet=pStatement.executeQuery();
				while(rSet.next())
				{
					obj=rSet.getObject(1);
					break;
				}
			}
			else {
				conn=DBUtil.ConnectionFactory.getConnection();
				sm=conn.createStatement();
				rSet=sm.executeQuery(sqlCmd);
				while(rSet.next())
				{
					obj=rSet.getObject(1);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		finally{
			CloseFactory.close(conn,sm,rSet);
			return obj;
		}
	}
}
