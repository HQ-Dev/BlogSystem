<%@page import="java.util.Map,java.util.Date"%>
<%@page import="java.util.ArrayList, java.text.SimpleDateFormat"%>
<%@page import="com.archy.blog.model.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Blog Archive</title>
	<link rel="stylesheet" href="/BlogSystem/static/css/userPosts.css">
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
</head>
<body>

	<!-- 导航条 -->
	<jsp:include page="/static/common/head.jsp" />	
	
	<!-- --------------------------------------------------------------------------------------------- -->
	
	<div class="container">
		<div class="page-header">
	    <h1>${user.userName} 
				<small> 欢迎来到你的博客主页.</small>
			</h1>
		</div>

		<div class="row">
			<div class="col-sm-8">文章列表
				
				<!-- 使用Jstl 改写了 JSP 表达式 -->
				<%! 
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					
				%>
				<% Map<Date, String> posts = (Map<Date, String>) request.getSession().getAttribute("postsMap"); %>
				<% 
					if(posts != null) {
						for (Date date: posts.keySet())	{
				%>
						<div class="blog-post">
					  <h3 class="blog-post-title"><a href="#"><%=posts.get(date) %> </a></h3>
						<p class="blog-post-meta"> 创建时间：<%=dateFormat.format(date) %> 分组：<a href="#">Web开发</a></p>
						<p class="blog-post-content"> 略 ...... </p>
					</div>
					<hr>
			  <% } } else {%>
        <h4> 你还没有博文，请创建你的第一篇博文！ </h4>
        <% } %>
        <!-- 加上翻页的效果  -->
				<nav>
					<ul class="pager">
						<li class="previous"><a href="#"><span aria-hidden="true">&larr;</span> 上一页</a></li>
						<li class="next"><a href="#">下一页 <span aria-hidden="true">&rarr;</span></a></li>
					</ul>
				</nav>
			</div>


			<!-- --------------------------------------------------------------------------------------- -->

			<div class="col-sm-3 col-sm-offset-1">信息栏
			
				<jsp:include page="/static/common/userInfo.jsp" />
			
			</div>

		</div>
	</div>

	<!-- ------------------------------------------------------------------------------------------- -->

	<!-- 增加页角  -->
 	<jsp:include page="/static/common/footer.jsp" />
</body>
</html>