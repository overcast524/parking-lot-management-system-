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
			location.href="<%=path %>/CardHandle?type=4";//点击后跳转至CardHandle Servlet页面
		});
	});
</script>

</head>
<body>
	<%
		response.setCharacterEncoding("UTF-8");//设置输出编码格式
		String card_id=request.getParameter("card_id").toString();//获取url传过来的card_id
	        DAL.Card card=new DAL.Card();//实例化Card对象
	        List<Object> cardList=card.getEntityById(card_id);//根据ID获取Card数据
	        Object[] obj=(Object[])cardList.get(0);//将List数据转换成Object[]
	        
	 %>
	 <form action="<%=path %>/CardHandle?type=2" method="post">
		<table style=" margin:50px auto;">
	            <tbody>
			 <tr><td>IC卡编号：</td><td><input type="text" name="card_id" value="<%=obj[0] %>" readonly /></td></tr>
			 <tr><td>车位编号：</td><td>
				<select name="seat_id">
				<%
				     Seat seat=new Seat();
				     List<Object> list=seat.getEntity();
				     for(int i=0;i<list.size();i++)
				     {
				      Object[] object=(Object[])list.get(i);
				      if(obj[1].toString().equals(object[0].toString()))
				      {
				       out.write("<option value='"+object[0]+"' selected='selected'>"+object[1]+"</option>");
				      }
				      else
				      {
				       out.write("<option value='"+object[0]+"'>"+object[1]+"</option>");
				      }
				     }
				 %>
				</select>
			 </td></tr>
			 <tr><td>用户名称：</td><td><input type="text" name="user_name" value="<%=obj[2] %>" /></td></tr>
			 <tr><td>用户性别：</td><td>
			 	<select name="user_gender">
				      <%
				       if(obj[3].toString().equals("男"))
				       {
				      %>
				       <option value="男" selected="selected">男</option>
				      <option value="女">女</option>
				      <%
				       }
				       else
				       {
				       %>
				       <option value="男" >男</option>
				       <option value="女" selected="selected">女</option>
				      <% } %>
				  </select>
			 </td></tr>
			 <tr><td>用户地址：</td><td><input type="text" name="user_addr" value="<%=obj[4] %>" required /></td></tr>
			 <tr><td>车牌号码：</td><td><input type="text" name="car_num" value="<%=obj[5] %>" required /></td></tr>

		    </tbody>   
	            
	            <tfoot>
	                <tr><td><input type="submit" value="确定" id="btnSure"/></td><td><input type="button" value="取消" id="btnCancel"/></td></tr>
	            </tfoot> 
	        </table>
     </form>
</body>
</html>
