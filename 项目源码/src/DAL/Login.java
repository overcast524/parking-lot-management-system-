package DAL;

import DBUtil.SQLUtil;

public class Login {
	
	//检测用户登录信息是否合法，合法这返回true
	public boolean checkLogin(String user_id,String user_pwd)
	{
		String sqlCmd="select count(*) from user where user_id=? and user_pwd=?";//要执行的查询T-SQL命令
		Object[] objList=new Object[2];//对象数组，用来作为？参数的容器
		objList[0]=user_id;
		objList[1]=user_pwd;
		String result=SQLUtil.excuteScalar(sqlCmd,objList).toString();//执行带参数查询
		if(result.equals("1"))
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	//根据用户Id获取用户名
	public String  getName(String user_id)
	{
		String sqlCmd="select user_name from user where user_id='"+user_id+"'";
		String result=SQLUtil.excuteScalar(sqlCmd, null).toString();
		return result;
	}
	
	//获取用户等级（角色Id）信息
	public String  getSysLevel(String user_id)
	{
		String sqlCmd="select role_id from user where user_id='"+user_id+"'";
		String result=SQLUtil.excuteScalar(sqlCmd, null).toString();
		return result;
	}
}
