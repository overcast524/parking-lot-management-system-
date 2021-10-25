<%@page import="java.util.*" errorPage="_Error.jsp"%>
<%@page import="DAL.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();//获取项目名称
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/Style/AddStyle.css"/>
<script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>
</head>
<body>
	<form action="<%=path %>/CardHandle?type=3" method="post" class="Form">
		<table style=" margin:50px auto;"> 
	            <tbody>
			<tr><td>车位编号：</td><td>
				<select name="seat_id">
					<%
					     Seat seat=new Seat();
					     List<Object> list=seat.getNoUseSeat();
					     for(int i=0;i<list.size();i++)
					     {
					      Object[] object=(Object[])list.get(i);
					      out.write("<option value='"+object[0]+"'>"+object[1]+"</option>");
					     }
					 %>
				</select>
			</td></tr>
			<tr><td>用户名称：</td><td><input type="text" name="user_name"  required /></td></tr>
			<tr><td>用户性别：</td><td>
				<select name="user_gender">
				     <option value="男">男</option>
				     <option value="女">女</option>
				</select>
			</td></tr>
			<tr><td>家庭住址：</td><td><input type="text" name="user_addr" required  /></td></tr>
			<tr><td>车牌号码：</td><td><input type="text" name="car_num" required  /></td></tr>
	            </tbody>   
	            
	            <tfoot>
	                <tr><td><input type="submit" value="确定" id="btnSure"/></td><td><input type="reset" value="取消" id="btnCancel"/></td></tr>
	            </tfoot> 
	        </table>
       </form>
</body>
</html>
