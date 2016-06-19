<%@page import="java.util.List"%>
<%@page import="com.archy.blog.model.Data"%>
<%@page import="com.archy.blog.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
</head>
<body>

	<!-- 导航条 -->
	<jsp:include page="/static/common/head.jsp" />	
	
	
	<!-- 已经注册的用户列表 -->
	<h2>已注册用户</h2><hr><br>
	
	<%
		Data usersData = new Data();
		List<User> users = Data.getUsers();
		pageContext.setAttribute("users", users);
	%>
	
	<c:forEach var="user" items="${users}">
		<tr>
			用户ID：<td>${user.userId}</td><br>
			用户名：<td>${user.userName}</td><br>
			用户email：<td>${user.email}</td><br>
		</tr>
		<hr>
	</c:forEach>
	
	
	<!-- 增加页角  -->
 	<jsp:include page="/static/common/footer.jsp" />

</body>
</html>