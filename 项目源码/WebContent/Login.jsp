<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();//获取项目名称
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/Style/Login.css" />
<script type="text/javascript" src="Script/jquery-1.10.1.js"></script>
<script type="text/javascript">
	$(function()
	{
		$(document).on("click",".a_reg",function()
		{
			$(".model").fadeIn();
		});
		
		$(document).on("click",".a_close",function()
		{
			$(".model").fadeOut();
		});
	});
	
	function checkPwd()
	{
		if($("[name=user_pwd1]").val()!=$("[name=re_pwd]").val())
		{
			alert("两次输入密码不一致~~~");
			return false;
		}
		else
		{
			return true;
		}
	};
</script>
</head>
<body>
	<div class="wrapLogin">
		    <div class="loginPanel">
		    	<form action="<%=path %>/LoginHandle" method="post">
		    		<h2>智能化停车场管理系统</h2>
			       	<p><label>用名：</label><input type="text" name="user_id" value="" /></p>
			           	<p><label>密码：</label><input type="password" name="user_pwd" value=""  /></p>
			        	<p class="btn"><input type="submit" class="btnLogin" value="登录"  /><input type="button" class="btnCancel" value="重置" /></p>	       		
		       	</form>
		     </div>
    </div>
		<!-- loginPanel End -->
</body>
</html>