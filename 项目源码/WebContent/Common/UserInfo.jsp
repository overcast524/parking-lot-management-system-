<%@page import="DAL.*"%>
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
<link rel="stylesheet" type="text/css" href="<%=path %>/Style/EditStyle.css"/>
<script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>
<script type="text/javascript">
	$(function()
	{		
		//取消按钮点击事件
		$("#btnCancel").click(function()
		{
			location.href="<%=path%>/Common/UserInfo.jsp";//点击后跳转至UserHandle Servlet页面
		});
	});
</script>

</head>
<body>
	<%
		response.setCharacterEncoding("UTF-8");//设置输出编码格式
		String user_id=session.getAttribute("user_id").toString();//获取url传过来的UserId
	        DAL.User user=new DAL.User();//实例化User对象
	        List<Object> userList=user.getEntityById(user_id);//根据ID获取User数据
	        Object[] obj=(Object[])userList.get(0);//将List数据转换成Object[]
	 %>
	 <form action="<%=path %>/UserHandle?type=7" method="post">
		<table style=" margin:50px auto;">
	            <tbody>
			 <tr><td>用户编号：</td><td><input type="text" name="user_id" value="<%=obj[0] %>" readonly="readonly"  /></td></tr>
			 <tr><td>角色名称：</td><td>
			 	<input type="hidden" name="role_id"  value="<%=obj[1] %>" />
			 	<input type="text"    value="<%=obj[6] %>" readonly="readonly" />
			 <td></tr>
			 <tr><td>用户姓名：</td><td><input type="text" name="user_name" value="<%=obj[2] %>"  required/></td></tr>
			 <tr><td>真实姓名：</td><td><input type="text" name="real_name" value="<%=obj[3] %>"  /></td></tr>
			 <tr><td>联系方式：</td><td>
			 	<input type="text" name="user_phone" value="<%=obj[5] %>" pattern="^1[1-9][0-9]\d{8}$"  title="手机号码有误"   />
			 	<input type="hidden" name="user_pwd" value="<%=obj[4] %>" />
			 </td></tr>
		    	 
		    </tbody>   
	            
	            <tfoot>
	                <tr><td><input type="submit" value="确定" id="btnSure"/></td><td><input type="button" value="取消" id="btnCancel"/></td></tr>
	            </tfoot> 
	        </table>
     </form>
</body>
</html>