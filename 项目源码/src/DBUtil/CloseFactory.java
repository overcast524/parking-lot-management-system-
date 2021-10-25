package DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CloseFactory {
	
	//关闭Connection conn,Statement sm
	public static void close(Connection conn,Statement sm)
	{
		close(conn);
		close(sm);
	}
	
	//关闭Connection conn,Statement sm,ResultSet rs
	public static void close(Connection conn,Statement sm,ResultSet rs)
	{
		close(conn);
		close(sm);
		close(rs);
	}
	
	//关闭数据库链接对象
	public static void close(Connection conn)
	{
		try {
			if(conn!=null)
			{
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//关闭Statement对象
	public static void close(Statement sm)
	{
		try {
			if(sm!=null)
			{
				sm.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//关闭ResultSet对象
	public static void close(ResultSet rs)
	{
		try {
			if(rs!=null)
			{
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
