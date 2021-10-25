        package DAL;

        import java.util.*;
        
        import DBUtil.SQLUtil;
        
        public class Role {

		//获取角色表信息列表
		public List<Object> getEntity()
		{
			String sqlCmd="select *from Role";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
            
               //获取分页后角色表信息列表
		public List<Object> getEntity(int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select * from Role limit "+size+",15";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
                
               //根据查询条件sqlWhere获取分页后角色表信息列表
		public List<Object> getEntityByWhere(String sqlWhere,int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select * from Role where "+sqlWhere+" limit "+ size+",15";
			System.out.println("sqlCmd =="+sqlCmd);
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
                }
                
                //删除角色表信息
                public int deleteEntity(String role_id)
                {
                    String sqlCmd="delete from Role where role_id='"+role_id+"'";
                    return DBUtil.SQLUtil.executeNonQuery(sqlCmd, null);//执行非查询操作executeNonQuery
                }
                
                //根据角色表编号获取角色表信息
                public List<Object> getEntityById(String role_id)
                {
                    String sqlCmd="select *From Role where role_id='"+role_id+"'";
                    System.out.println(sqlCmd);
                    return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
                }
              //根据角色表编号获取角色表信息
                public List<Object> getEntityByName(String role_name)
                {
                    String sqlCmd="select * From Role where role_name='"+role_name+"'";
                    System.out.println(sqlCmd);
                    return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
                }
                
                //更新角色表信息
                public int updateEntity(String role_id,String role_name)
                {
                    String sqlCmd="Update Role set role_name='" + role_name + "' where role_id='"+role_id+"'";
                    return SQLUtil.executeNonQuery(sqlCmd, null);
                }
                
                //插入角色表信息
                public int insertEntity(String role_id,String role_name)
                {
                    String sqlCmd="Insert into Role values('" + role_id + "','"+role_name+"')";
                    return SQLUtil.executeNonQuery(sqlCmd, null);
                }
                
                //检查插入主键是否重复
                public boolean checkExist(String role_id)
                {
                    String sqlCmd="select count(*) from Role where role_id='"+role_id+"'";
                    if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
                    {
                        return true;
                    }
                    return false;
                }

		//获取分页总数
		public Object getPageCount()
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM Role ";
			return SQLUtil.excuteScalar(sqlCmd, null);
		}

		//根据查询条件获取分页总数
		public Object getPageCountByWhere(String sqlWhere)
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM Role where "+sqlWhere;
			return SQLUtil.excuteScalar(sqlCmd, null);
		}
            
        }
