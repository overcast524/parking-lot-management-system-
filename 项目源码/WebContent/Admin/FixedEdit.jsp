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
			location.href="<%=path %>/FixedHandle?type=4";//点击后跳转至FixedHandle Servlet页面
		});
	});
</script>

</head>
<body>
	<%
		response.setCharacterEncoding("UTF-8");//设置输出编码格式
		String fixed_id=request.getParameter("fixed_id").toString();//获取url传过来的fixed_id
	        DAL.Fixed fixed=new DAL.Fixed();//实例化Fixed对象
	        List<Object> fixedList=fixed.getEntityById(fixed_id);//根据ID获取Fixed数据
	        Object[] obj=(Object[])fixedList.get(0);//将List数据转换成Object[]
	        
	 %>
	 <form action="<%=path %>/FixedHandle?type=2" method="post">
		<table style=" margin:50px auto;">
	            <tbody>
			 <tr><td>记录编号：</td><td><input type="text" name="fixed_id" value="<%=obj[0] %>" /></td></tr>
			 <tr><td>IC卡号：</td><td><input type="text" name="card_id" value="<%=obj[1] %>" /></td></tr>
			 <tr><td>入场日期：</td><td><input type="text" name="entry_date" value="<%=obj[2] %>" /></td></tr>
			 <tr><td>入场时间：</td><td><input type="text" name="entry_time" value="<%=obj[3] %>" /></td></tr>
			 <tr><td>出场日期：</td><td><input type="text" name="out_date" value="<%=obj[4] %>" /></td></tr>
			 <tr><td>出场时间：</td><td><input type="text" name="out_time" value="<%=obj[5] %>" /></td></tr>

		    </tbody>   
	            
	            <tfoot>
	                <tr><td><input type="submit" value="确定" id="btnSure"/></td><td><input type="button" value="取消" id="btnCancel"/></td></tr>
	            </tfoot> 
	        </table>
     </form>
</body>
</html>
