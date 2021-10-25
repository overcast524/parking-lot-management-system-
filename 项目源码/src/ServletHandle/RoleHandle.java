			package ServletHandle;

			import java.io.IOException;
			import java.io.PrintWriter;
			import java.io.UnsupportedEncodingException;

			import javax.servlet.ServletException;
			import javax.servlet.http.HttpServlet;
			import javax.servlet.http.HttpServletRequest;
			import javax.servlet.http.HttpServletResponse;

			import java.util.*;

			public class RoleHandle extends HttpServlet {

			HttpServletRequest request;
			HttpServletResponse response;
			DAL.Role role=new DAL.Role();
	
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
					default:
						break;
					}
			}

			//删除数据操作
			private void deleteEntity() throws IOException
			{
				String role_id=request.getParameter("role_id");//获取前台通过get方式传过来的JId
				role.deleteEntity(role_id);//执行删除操作
				response.sendRedirect("/Parking/RoleHandle?type=4");//删除成功后跳转至管理页面
			}

			//更新数据操作
			private void updateEntity() throws UnsupportedEncodingException
			{
				String role_id=new String(request.getParameter("role_id").getBytes("ISO8859_1"),"UTF-8");
String role_name=new String(request.getParameter("role_name").getBytes("ISO8859_1"),"UTF-8");

				if(role.updateEntity(role_id,role_name)==1)
				{
					try {
						response.sendRedirect("/Parking/RoleHandle?type=4");//成功更新数据后跳转至RoleMsg.jsp页面
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
				
				String role_id=new String(request.getParameter("role_id").getBytes("ISO8859_1"),"UTF-8");
String role_name=new String(request.getParameter("role_name").getBytes("ISO8859_1"),"UTF-8");

				if(!role.checkExist(role_id))
				{
					if(role.insertEntity(role_id,role_name)==1)
					{
						out.write("<script>alert('数据添加成功！'); location.href = '/Parking/RoleHandle?type=4';</script>");
					}
					else {
						out.write("<script>alert('数据添失败！'); location.href = '/Parking/RoleHandle?type=4';</script>");
					}
				}
				else {
					out.write("<script>alert('主键重复，数据添加失败！'); location.href = '/Parking/RoleHandle?type=4';</script>");
				}
			}

			//获取对象所有数据列表
			private void getEntity() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
				int totalPage=Integer.parseInt(role.getPageCount().toString()) ;//获取分页总数
				List<Object> list=role.getEntity(page);//获取数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
				request.getRequestDispatcher("/Admin/RoleMsg.jsp").forward(request, response);//请求转发
			}
			
			//根据查询条件获取对象所有数据列表
			private void getEntityByWhere() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				String condition=request.getParameter("condition");//获取查询字段的名称
				System.out.println(condition);
				//String value=new String(request.getParameter("value").getBytes("ISO8859_1"),"UTF-8");//获取查询的值
				String value = request.getParameter("value");
				System.out.println("value  "+value);
				String where=condition+"=\""+value+"\"";//拼接查询字符串
				System.out.println("where  "+where);
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
				//System.out.println("page +"+page );
				int wherePage=Integer.parseInt(role.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
				List<Object> list=role.getEntityByWhere(where, page);//获取查询后的数据列表
				System.out.println();
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("wherePage",wherePage );
				request.setAttribute("condition",condition);
				request.setAttribute("value",value);
				request.getRequestDispatcher("/Admin/RoleMsg.jsp").forward(request, response);
			}
		}
