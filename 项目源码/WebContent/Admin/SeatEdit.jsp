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
			location.href="<%=path %>/SeatHandle?type=4";//点击后跳转至SeatHandle Servlet页面
		});
	});
</script>

</head>
<body>
	<%
		response.setCharacterEncoding("UTF-8");//设置输出编码格式
		String seat_id=request.getParameter("seat_id").toString();//获取url传过来的seat_id
	        DAL.Seat seat=new DAL.Seat();//实例化Seat对象
	        List<Object> seatList=seat.getEntityById(seat_id);//根据ID获取Seat数据
	        Object[] obj=(Object[])seatList.get(0);//将List数据转换成Object[]
	        
	 %>
	 <form action="<%=path %>/SeatHandle?type=2" method="post">
		<table style=" margin:50px auto;">
	            <tbody>
			 <tr><td>车位ID：</td><td><input type="text" name="seat_id" value="<%=obj[0] %>" readonly /></td></tr>
			 <tr><td>车位编号：</td><td><input type="text" name="seat_num" value="<%=obj[1] %>" required /></td></tr>
			 <tr><td>车位区域：</td><td>
			 	<select name="seat_section">
				      <%
				       if(obj[2].toString().equals("A区"))
				       {
				      %>
				       <option value="A区" selected="selected">A区</option>
				       <option value="B区">B区</option>
				      <%
				       }
				       else
				       {
				       %>
				     	<option value="A区">A区</option>
				        <option value="B区" selected="selected">B区</option>
				      <% } %>
				</select>
			 </td></tr>
			 <tr><td>车位状态：</td><td>
			 	<select name="seat_state">
				      <%
				       if(obj[3].toString().equals("0"))
				       {
				      %>
				       <option value="0" selected="selected">闲置</option>
				       <option value="1">占用</option>
				      <%
				       }
				       else
				       {
				       %>
				     	<option value="0">闲置</option>
				        <option value="1" selected="selected">占用</option>
				      <% } %>
				</select>
			 </td></tr>
			 <tr><td>车位备注：</td><td><input type="text" name="seat_tag" value="<%=obj[4] %>" /></td></tr>

		    </tbody>   
	            
	            <tfoot>
	                <tr><td><input type="submit" value="确定" id="btnSure"/></td><td><input type="button" value="取消" id="btnCancel"/></td></tr>
	            </tfoot> 
	        </table>
     </form>
</body>
</html>
