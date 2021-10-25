			package ServletHandle;

			import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

			import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.text.SimpleDateFormat;
import java.util.*;

			public class UserHandle extends HttpServlet {

			HttpServletRequest request;
			HttpServletResponse response;
			DAL.User user=new DAL.User();
	
			//通过表单get方式传值 将进入doGet函数（method="get"）
			public void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
				this.response=response;
					this.request=request;
					int handleType=Integer.parseInt(request.getParameter("type").toString());
					switch (handleType) {
					case 1://类型1代表删除表中的数据
						deleteEntity();
						break;
					case 4://类型4代表获取表中信息
						getEntity();
						break;
					case 5://类型5代表根据查询条件获取表中信息
						getEntityByWhere();
						break;
					default:
						break;
					}
			}
	
			//通过表单post方式传值 将进入doPost函数（method="post"）
			public void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
					this.request=request;
					this.response=response;
					int handleType=Integer.parseInt(request.getParameter("type").toString());//将前台页面传过来的type类型转化成整型
					switch (handleType) {
					case 2://类型2代表更新表中的数据
						updateEntity();
						break;
					case 3://类型3代表向表中添加数据
						insertEntity();
						break;
					case 6://类型6代表向更改密码
						chagePwd();
						break;
					case 7://类型7代表用户更新个人数据
						updateEntity();
						break;
					case 8://用户注册
						register();
					default:
						break;
					}
			}
			
			//更改密码
			private void chagePwd() throws IOException
			{
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				HttpSession session=request.getSession();
				String userId=session.getAttribute("user_id").toString();
				String oldPwd=new String(request.getParameter("OldPwd").getBytes("ISO8859_1"),"UTF-8");
				String newPwd=new String(request.getParameter("NewPwd").getBytes("ISO8859_1"),"UTF-8");
				if(user.checkPwd(userId, oldPwd))
				{
					if(user.updataPwd(userId, newPwd))
					{
						out.write("<script>alert('密码更改成功~~~');location.href='/Parking/Common/UserInfo.jsp'</script>");
					}
					else {
						out.write("<script>alert('密码更改失败~~~');location.href='/Parking/Common/ChagePwd.jsp'</script>");
					}
				}
				else {
					out.write("<script>alert('原始密码错误~~~');location.href='/Parking/Common/ChagePwd.jsp'</script>");
				}
			}
			
			//用户注册
			private void register() throws UnsupportedEncodingException, IOException
			{
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				
				String UserId=new String(request.getParameter("user_id").getBytes("ISO8859_1"),"UTF-8");
				String RoleId=new String(request.getParameter("role_id").getBytes("ISO8859_1"),"UTF-8");
				String UserName=new String(request.getParameter("user_name").getBytes("ISO8859_1"),"UTF-8");
				String RealName=new String(request.getParameter("real_name").getBytes("ISO8859_1"),"UTF-8");
				String UserPwd=new String(request.getParameter("user_pwd1").getBytes("ISO8859_1"),"UTF-8");
				String UserPhone=new String(request.getParameter("user_phone").getBytes("ISO8859_1"),"UTF-8");

				if(!user.checkExist(UserId))
				{
					if(user.insertEntity(UserId,RoleId,UserName,RealName,UserPwd,UserPhone)==1)
					{
					    SimpleDateFormat dateFormat =new    SimpleDateFormat("yyyyMMddHHmmss"); 
					    String AId=dateFormat.format(new Date());
					    //Account account=new Account();
					    //account.insertEntity(AId, UserId, "0","2015-12-30");
					    out.write("<script>alert('恭喜你，注册成功~'); location.href = '/Parking/Login.jsp';</script>");
					}
				}
				else {
					  out.write("<script>alert('您注册的登陆账号已存在，请重新注册！'); location.href = '/Parking/Login.jsp';</script>");
				}
			}

			//删除数据操作
			private void deleteEntity() throws IOException
			{
				String user_id=request.getParameter("user_id");//获取前台通过get方式传过来的JId
				user.deleteEntity(user_id);//执行删除操作
				response.sendRedirect("/Parking/UserHandle?type=4");//删除成功后跳转至管理页面
			}

			//更新数据操作
			private void updateEntity() throws UnsupportedEncodingException
			{
				String user_id=new String(request.getParameter("user_id").getBytes("ISO8859_1"),"UTF-8");
				String role_id=new String(request.getParameter("role_id").getBytes("ISO8859_1"),"UTF-8");
				String user_name=new String(request.getParameter("user_name").getBytes("ISO8859_1"),"UTF-8");
				String real_name=new String(request.getParameter("real_name").getBytes("ISO8859_1"),"UTF-8");
				String user_pwd=new String(request.getParameter("user_pwd").getBytes("ISO8859_1"),"UTF-8");
				String user_phone=new String(request.getParameter("user_phone").getBytes("ISO8859_1"),"UTF-8");

				if(user.updateEntity(user_id,role_id,user_name,real_name,user_pwd,user_phone)==1)
				{
					try {
							if(request.getSession().getAttribute("role_id").toString().equals("r001"))
							{
								response.sendRedirect("/Parking/UserHandle?type=4");//成功更新数据后跳转至UserInfo.jsp页面
							}
							else {
								response.sendRedirect("/Parking/Common/UserInfo.jsp");//成功更新数据后跳转至UserInfo.jsp页面
							}
					} catch (IOException e) {
						e.printStackTrace();//异常处理
					}
				}
			}

			//插入数据操作
			private void insertEntity() throws UnsupportedEncodingException, IOException
			{
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				
				String user_id=new String(request.getParameter("user_id").getBytes("ISO8859_1"),"UTF-8");
				String role_id=new String(request.getParameter("role_id").getBytes("ISO8859_1"),"UTF-8");
				String user_name=new String(request.getParameter("user_name").getBytes("ISO8859_1"),"UTF-8");
				String real_name=new String(request.getParameter("real_name").getBytes("ISO8859_1"),"UTF-8");
				String user_pwd=new String(request.getParameter("user_pwd").getBytes("ISO8859_1"),"UTF-8");
				String user_phone=new String(request.getParameter("user_phone").getBytes("ISO8859_1"),"UTF-8");

				if(!user.checkExist(user_id))
				{
					if(user.insertEntity(user_id,role_id,user_name,real_name,user_pwd,user_phone)==1)
					{
						out.write("<script>alert('数据添加成功！'); location.href = '/Parking/UserHandle?type=4';</script>");
					}
					else {
						out.write("<script>alert('数据添失败！'); location.href = '/Parking/UserHandle?type=4';</script>");
					}
				}
				else {
					out.write("<script>alert('主键重复，数据添加失败！'); location.href = '/Parking/UserHandle?type=4';</script>");
				}
			}

			//获取对象所有数据列表
			private void getEntity() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
				int totalPage=Integer.parseInt(user.getPageCount().toString()) ;//获取分页总数
				List<Object> list=user.getEntity(page);//获取数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
				request.getRequestDispatcher("/Admin/UserMsg.jsp").forward(request, response);//请求转发
			}
			
			//根据查询条件获取对象所有数据列表
			private void getEntityByWhere() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				String condition=request.getParameter("condition");//获取查询字段的名称
				//String value=new String(request.getParameter("value").getBytes("ISO8859_1"),"UTF-8");//获取查询的值
				String value = request.getParameter("value");
				String where=condition+"=\""+value+"\"";//拼接查询字符串
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
				int wherePage=Integer.parseInt(user.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
				List<Object> list=user.getEntityByWhere(where, page);//获取查询后的数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("wherePage",wherePage );
				request.setAttribute("condition",condition);
				request.setAttribute("value",value);
				request.getRequestDispatcher("/Admin/UserMsg.jsp").forward(request, response);
			}
		}
