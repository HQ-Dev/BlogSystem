<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
</head>
<body>

  <!-- 博客用户右边信息栏目 -->
	<div class="sidebar-module sidebar-module-inset">
		<div class="avatar">
			<img class="img-circle img-rounded img-thumbnail avatar" src="/BlogSystem/static/img/cat.jpg"
			width="140" height="140">
			<div>
				<h4>${user.userName}</h4>
				<a href="#">${user.email}</a>
			</div>
		</div>
		<p>
       Java 学徒
		</p>
	</div>

	<div class="sidebar-module">
		<h4>归档</h4>
		<ol class="list-unstyled">
		  <li><a href="#">2016年5月</a></li>
			<li><a href="#">2016年4月</a></li>
			<li><a href="#">2016年3月</a></li>
			<li><a href="#">2016年2月</a></li>
			<li><a href="#">2016年1月</a></li>
			<li><a href="#">2015年12月</a></li>
			<li><a href="#">2015年11月</a></li>
		</ol>
	</div>
	
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
</body>
</html>