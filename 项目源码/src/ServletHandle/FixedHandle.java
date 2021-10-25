			package ServletHandle;

			import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

			import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.*;

			public class FixedHandle extends HttpServlet {

			HttpServletRequest request;
			HttpServletResponse response;
			DAL.Fixed fixed=new DAL.Fixed();
	
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
					case 6://类型6代表管理员获取未出场车辆
						getNoOut();
						break;
					case 10://类型10代表更新车辆出场
						setOut();
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
				String fixed_id=request.getParameter("fixed_id");//获取前台通过get方式传过来的JId
				fixed.deleteEntity(fixed_id);//执行删除操作
				response.sendRedirect("/Parking/FixedHandle?type=4");//删除成功后跳转至管理页面
			}
			
			//车辆出场更新操作
			private void setOut() throws IOException
			{
				String fixed_id=new String(request.getParameter("fixed_id").getBytes("ISO8859_1"),"UTF-8");
				SimpleDateFormat dateFormat =new    SimpleDateFormat("yyyy-MM-dd"); 
			    String out_date=dateFormat.format(new Date());
			    SimpleDateFormat timeFormat =new    SimpleDateFormat("HH:mm:ss"); 
			    String out_time=timeFormat.format(new Date());
			    if(fixed.setOut(fixed_id, out_date, out_time)==1)
			    {
			    	response.sendRedirect("/Parking/FixedHandle?type=6");
			    }
			}

			//更新数据操作
			private void updateEntity() throws UnsupportedEncodingException
			{
				String fixed_id=new String(request.getParameter("fixed_id").getBytes("ISO8859_1"),"UTF-8");
				String card_id=new String(request.getParameter("card_id").getBytes("ISO8859_1"),"UTF-8");
				String entry_date=new String(request.getParameter("entry_date").getBytes("ISO8859_1"),"UTF-8");
				String entry_time=new String(request.getParameter("entry_time").getBytes("ISO8859_1"),"UTF-8");
				String out_date=new String(request.getParameter("out_date").getBytes("ISO8859_1"),"UTF-8");
				String out_time=new String(request.getParameter("out_time").getBytes("ISO8859_1"),"UTF-8");

				if(fixed.updateEntity(fixed_id,card_id,entry_date,entry_time,out_date,out_time)==1)
				{
					try {
						response.sendRedirect("/Parking/FixedHandle?type=4");//成功更新数据后跳转至FixedMsg.jsp页面
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
				
			    SimpleDateFormat dateFormat =new    SimpleDateFormat("yyyyMMddHHmmss"); 
			    String fixed_id=dateFormat.format(new Date());
				String card_id=new String(request.getParameter("card_id").getBytes("ISO8859_1"),"UTF-8");
				SimpleDateFormat dFormat =new    SimpleDateFormat("yyyy-MM-dd"); 
			    String entry_date=dFormat.format(new Date());
			    SimpleDateFormat tFormat =new    SimpleDateFormat("HH:mm:ss"); 
			    String entry_time=tFormat.format(new Date());
				String out_date="1111-11-11";
				String out_time="11:11:11";

				if(!fixed.checkExist(fixed_id))
				{
					if(fixed.insertEntity(fixed_id,card_id,entry_date,entry_time,out_date,out_time)==1)
					{
						out.write("<script>alert('数据添加成功！'); location.href = '/Parking/FixedHandle?type=6';</script>");
					}
					else {
						out.write("<script>alert('数据添失败！'); location.href = '/Parking/FixedHandle?type=6';</script>");
					}
				}
				else {
					out.write("<script>alert('主键重复，数据添加失败！'); location.href = '/Parking/FixedHandle?type=4';</script>");
				}
			}

			//获取对象所有数据列表
			private void getEntity() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
				int totalPage=Integer.parseInt(fixed.getPageCount().toString()) ;//获取分页总数
				List<Object> list=fixed.getEntity(page);//获取数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
				request.getRequestDispatcher("/Admin/FixedMsg.jsp").forward(request, response);//请求转发
			}
			
			//获取未出场的车辆
			private void getNoOut() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
				int totalPage=Integer.parseInt(fixed.getPageCount().toString()) ;//获取分页总数
				List<Object> list=fixed.getNoOut(page);//获取数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
				request.getRequestDispatcher("/Admin/FixedOut.jsp").forward(request, response);//请求转发
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
				int wherePage=Integer.parseInt(fixed.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
				List<Object> list=fixed.getEntityByWhere(where, page);//获取查询后的数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("wherePage",wherePage );
				request.setAttribute("condition",condition);
				request.setAttribute("value",value);
				request.getRequestDispatcher("/Admin/FixedMsg.jsp").forward(request, response);
			}
		}
