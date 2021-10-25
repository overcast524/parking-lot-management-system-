        package DAL;

        import java.util.*;
        
import DBUtil.SQLUtil;
        
        public class User {

		//获取用户表信息列表
		public List<Object> getEntity()
		{
			String sqlCmd="select *from User";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
            
       //获取分页后用户表信息列表
		public List<Object> getEntity(int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from V_User limit "+size+",15";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
                
        //根据查询条件sqlWhere获取分页后用户表信息列表
		public List<Object> getEntityByWhere(String sqlWhere,int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from V_User where "+sqlWhere+" limit "+ size+",15";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
        
        //删除用户表信息
        public int deleteEntity(String user_id)
        {
            String sqlCmd="delete from User where user_id='"+user_id+"'";
            return DBUtil.SQLUtil.executeNonQuery(sqlCmd, null);//执行非查询操作executeNonQuery
        }
        
        //根据用户表编号获取用户表信息
        public List<Object> getEntityById(String user_id)
        {
            String sqlCmd="select *From V_User where user_id='"+user_id+"'";
            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
        
        //更新用户表信息
        public int updateEntity(String user_id,String role_id,String user_name,String real_name,String user_pwd,String user_phone)
        {
            String sqlCmd="Update User set role_id='" + role_id + "',user_name='" + user_name + "',real_name='" + real_name + "',user_pwd='" + user_pwd + "',user_phone='" + user_phone + "' where user_id='"+user_id+"'";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        
        //插入用户表信息
        public int insertEntity(String user_id,String role_id,String user_name,String real_name,String user_pwd,String user_phone)
        {
            String sqlCmd="Insert into User values('" + user_id + "','" + role_id + "','" + user_name + "','" + real_name + "','" + user_pwd + "','"+user_phone+"')";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        
        //检查插入主键是否重复
        public boolean checkExist(String user_id)
        {
            String sqlCmd="select count(*) from V_User where user_id='"+user_id+"'";
            if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
            {
                return true;
            }
            return false;
        }

		//获取分页总数
		public Object getPageCount()
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM V_User ";
			return SQLUtil.excuteScalar(sqlCmd, null);
		}

		//根据查询条件获取分页总数
		public Object getPageCountByWhere(String sqlWhere)
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM V_User where "+sqlWhere;
			return SQLUtil.excuteScalar(sqlCmd, null);
		}
		
		//检查插入密码是否输入正确（改密码）
        public boolean checkPwd(String UserId,String pwd)
        {
            String sqlCmd="select count(*) from V_User where user_id='"+UserId+"' and user_pwd='"+pwd+"'";
            if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
            {
                return true;
            }
            return false;
        }
        
        //检查插入密码是否输入正确（改密码）
        public boolean updataPwd(String UserId,String pwd)
        {
            String sqlCmd="Update user set user_pwd='"+pwd+"'  where user_id='"+UserId+"'";
            if(SQLUtil.executeNonQuery(sqlCmd, null)==1)
            {
            	return true;
            }
            return false;
        }
            
    }
