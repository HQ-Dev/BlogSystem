<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP简介作业</title>
	</head>
	<body>
		<p>现在是北京时间: <%= new Date().toString() %> </p>
		<%	int a,b;
			// 加上判断url中是否有用户的参数，可以防止无参数时页面报出异常
			if(request.getParameter("para1") != null
					&& request.getParameter("para2") != null) {
			 a = Integer.valueOf(request.getParameter("para1"));
			 b = Integer.valueOf(request.getParameter("para2"));
		%>
		<br>
		<p><%= a %> + <%= b %> = <%= a+b %></p>
		
		<% } %>
		
		<!-- 3. 定义一个函数，并使用 -->
		<%!
			String transform(String str) {
				return str + "成都其实也没有很有趣";			
			}
		%>
		<!-- 调用此方法 -->
		<%=transform("你的看法是，") %>
	</body>
</html>