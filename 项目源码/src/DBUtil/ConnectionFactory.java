package DBUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
	private static String user;
	private static String pwd;
	private static String url;
	private static String driver;
	static{
		InputStream iStream=ConnectionFactory.class.getClassLoader().getResourceAsStream("DBUtil/db.properties");//读取db.properties配置文件
		Properties prop=new Properties();
		try {
			prop.load(iStream);
			user=prop.getProperty("user");//获取配置文件中的user对应的值
			pwd=prop.getProperty("pwd");//获取配置文件中的pwd对应的值
			url=prop.getProperty("url");//获取配置文件中的url对应的值
			driver=prop.getProperty("driver");//获取配置文件中的driver对应的值
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection()
	{
		Connection conn=null;
		try {
			Class.forName(driver);//数据库驱动注册
			conn=DriverManager.getConnection(url,user,pwd);//获取数据库链接对象
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
