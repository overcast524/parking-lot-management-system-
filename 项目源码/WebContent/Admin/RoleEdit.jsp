<%@page import="DAL.*" errorPage="_Error.jsp"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();//获取项目名称
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../Style/EditStyle.css"/>
<script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>
<script type="text/javascript">
	$(function()
	{		
		//取消按钮点击事件
		$("#btnCancel").click(function()
		{
			location.href="<%=path %>/RoleHandle?type=4";//点击后跳转至RoleHandle Servlet页面
		});
	});
</script>

</head>
<body>
	<%
		response.setCharacterEncoding("UTF-8");//设置输出编码格式
		String role_id=request.getParameter("role_id").toString();//获取url传过来的role_id
	        DAL.Role role=new DAL.Role();//实例化Role对象
	        List<Object> roleList=role.getEntityById(role_id);//根据ID获取Role数据
	        Object[] obj=(Object[])roleList.get(0);//将List数据转换成Object[]
	        
	 %>
	 <form action="<%=path %>/RoleHandle?type=2" method="post">
		<table style=" margin:50px auto;">
	            <tbody>
			 <tr><td>角色编号：</td><td><input type="text" name="role_id" value="<%=obj[0] %>" readonly="readonly" /></td></tr>
 			 <tr><td>角色名称：</td><td><input type="text" name="role_name" value="<%=obj[1] %>" required /></td></tr>
		    </tbody>   
	            
	            <tfoot>
	                <tr><td><input type="submit" value="确定" id="btnSure"/></td><td><input type="button" value="取消" id="btnCancel"/></td></tr>
	            </tfoot> 
	        </table>
     </form>
</body>
</html>
