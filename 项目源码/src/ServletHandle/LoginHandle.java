package ServletHandle;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandle extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");//设置输出编码格式
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session=request.getSession();
		String user_id=request.getParameter("user_id");//获取前台url传过来的uName参数
		String user_pwd=request.getParameter("user_pwd");//获取前台url传过来的uPwd参数
		DAL.Login _login=new DAL.Login();//实例化Login对象，来至DAL包
		boolean result=_login.checkLogin(user_id, user_pwd);//检查登陆用户是否合法
		if(result)//登陆正确
		{
			session.setAttribute("user_id", user_id);//将用户userId保存在session对象中全局使用
			String user_name=_login.getName(user_id);//获取用户名
			session.setAttribute("user_name", user_name);
			String role_id=_login.getSysLevel(user_id);
			session.setAttribute("role_id", role_id);
			request.getRequestDispatcher("/Index.jsp").forward(request, response);
		
		}
		else {//登陆错误
			PrintWriter out=response.getWriter();
			out.write("<script>alert('用户名或密码错误！');location.href='Login.jsp';</script>");
		}
	}

}
