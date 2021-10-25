<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="_Error.jsp"%>
<%
	String path = request.getContextPath();//获取项目名称
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智能化停车场管理系统</title>
<link href="<%=path %>/Style/Index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<%=path %>/Script/Index.js"></script>
</head>
<body>
	
	 <%-- 判断uName是否存在，如何不存在则证明非登录操作，跳转至登录页面 --%>
	 <%  if (session.getAttribute("user_name") == null ) {%>
	 <jsp:forward page="Login.jsp" ></jsp:forward>
	 <% } %>

	<!--header-->
 	<div class="header">
	        <div class="header_logo">
	        	智能化停车场管理系统
	        </div>
	        <%-- 
	        <div class="func">
	        	<ul>
	            	<li class="li_func1"><a href="<%=path %>/Common/ChagePwd.jsp" target="mainFrame">修改密码</a></li>
	                <li class="li_func2"><a href="<%=path %>/Common/UserInfo.jsp" target="mainFrame">用户信息</a></li>
	                <li class="li_func3"><a href="<%=path %>/Common/Logout.jsp">退出系统</a></li>
	            </ul>
	        </div>
	       --%>
    	</div>

    
    <div class="wrap">
        <ul class="siderbar">
        	
        	<%
        		if(session.getAttribute("role_id").toString().equals("r001"))
        		{
        	%>
        	<li><span>系统信息管理</span>
	        	<ul>
	        	
	               <li><a href="<%=path %>/Admin/RoleAdd.jsp" target="mainFrame">添加角色信息</a></li>
	               <li><a href="<%=path %>/RoleHandle?type=4" target="mainFrame">管理角色信息</a></li>
	               <li><a href="<%=path %>/Admin/UserAdd.jsp" target="mainFrame">添加用户信息</a></li>
	               <li><a href="<%=path %>/UserHandle?type=4" target="mainFrame">管理用户信息</a></li>
	        	</ul>
	        </li>
	        <li><span>车位信息管理</span>
	            <ul>
	               <li><a href="<%=path %>/Admin/SeatAdd.jsp" target="mainFrame">添加车位信息</a></li>
	               <li><a href="<%=path %>/SeatHandle?type=4" target="mainFrame">管理车位信息</a></li>
	            </ul>
	        </li>
	        <li><span>IC卡信息管理</span>
	            <ul>
	                <li><a href="<%=path %>/Admin/CardAdd.jsp" target="mainFrame">添加IC卡类型</a></li>
	                <li><a href="<%=path %>/CardHandle?type=4" target="mainFrame">管理IC卡类型</a></li>
	            </ul>
	        </li>
	        <li><span>固定车主停车管理</span>
	            <ul>
	                <li><a href="<%=path %>/FixedHandle?type=6" target="mainFrame">出入场设置</a></li>
	                <li><a href="<%=path %>/FixedHandle?type=4" target="mainFrame">停车信息管理</a></li>
	        	</ul>
	        </li>
	        <li><span>临时车辆停车管理</span>
	        	<ul>
	                <li><a href="<%=path %>/Admin/TempAdd.jsp" target="mainFrame">车主入场信息</a></li>
	                <li><a href="<%=path %>/TempHandle?type=4" target="mainFrame">车主出场信息</a></li>
	        	</ul>
            </li>
	        <li><span>系统功能操作</span>
	        	<ul>
	                <li><a href="<%=path %>/Common/ChagePwd.jsp" target="mainFrame">修改密码</a></li>
	                <li><a href="<%=path %>/Common/Logout.jsp">退出系统</a></li>
	        	</ul>
	       </li>
	        <%
	        	}
	        	else
	        	{
	        %>  	        	    	        
	        <li><span>系统功能操作</span>
	        <div>
	                <a href="<%=path %>/Common/ChagePwd.jsp" target="mainFrame">修改密码</a>
	                <a href="<%=path %>/Common/UserInfo.jsp" target="mainFrame">个人信息</a>
	                <a href="<%=path %>/Common/Logout.jsp">退出系统</a>
	        </div>

	        <%
	        	}
	         %>
	       	 
        </ul>
        
       	<div class="content" >
              	<iframe style="display:block;" width="999px" height="100%" name="mainFrame" frameborder="0" >
                  
                </iframe>
        </div>
   </div>
        
   <div class="footer"></div>
</body>
</html>
