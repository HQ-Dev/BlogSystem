<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>	
<head>
	<meta charset="utf-8">
	<title>博客首页</title>
	<link rel="stylesheet" href="/BlogSystem/static/css/homepage.css">
	<!-- rel是relationship的缩写，它表示的是文档与外部链接href的关系，stylesheet 样式表 -->
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">     	
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
	<!-- 导航条 -->
	
	<jsp:include page="/WEB-INF/common/head.jsp" />	
	<!-- -------------------------------------------------------------------------------------------- -->
	
	<!-- 增加一个轮播的页面 -->
	<div id="myCarousel" class="carousel slide" data-ride="carousel">  <!-- data-ride 让轮播在页面一加载就开始动画，不需要JS的控制 -->
		<!-- 轮播的指标 -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<!-- 轮播（carousel）的项目 -->
		<div class="carousel-inner">
		
			<div class="item active" >
				<img class="first-slide center-block" src="/BlogSystem/static/img/cat.jpg" alt="First slide">   
				 <!-- alt 表示图片显示不出来的可替换文本 -->
				<div class="carousel-caption">   <!-- carousel-caption 是轮播内容的说明文字 -->
					<h2>极致体验</h2>
					<p>优雅简介的外观设计，细心的交互方式，帮助您更快捷地创建博客，更方便地浏览您的博客内容。</p>
					<p><a class="btn btn-primary" href="#">立即加入</a></p>
				</div>
			</div>
			
			<div class="item" >   <!-- 图片中增加 .center-block 可让图片居中显示 -->
				<img class="second-slide center-block" src="/BlogSystem/static/img/dog.jpg" alt="Second slide" >
				<div class="carousel-caption">
					<h2>Markdown 引擎</h2>
					<p>Sample Blog 使用 Markdown 创建易读易写的博客文章。Markdown 由一些经过精挑细选的符号组成，作用一目了然。</p>
					<p><a class="btn btn-primary" href="#">深入了解</a></p>
				</div>
			</div>
			
			<div class="item" >
				<img class="third-slide center-block" src="/BlogSystem/static/img/chick.jpg" alt="Third slide" >
				<div class="carousel-caption">
					<h2>Markdown 引擎</h2>
					<p>Sample Blog 使用 Markdown 创建易读易写的博客文章。Markdown 由一些经过精挑细选的符号组成，作用一目了然。</p>
					<p><a class="btn btn-primary" href="#">深入了解</a></p>
				</div>
			</div>
			
		</div>
		
		<!-- 轮播的导航 -->
		<a class="carousel-control left" href="#myCarousel" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a>
		
		<a class="carousel-control right" href="#myCarousel" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span>
		</a>
		
	</div>
	
	<!------------------------------------------------------------------------------------------------------>
	
	<div class="container project-list">
		<div class="row">
		
		<%! 
			public class Project {
				public String name;
				public String description;
				public String url;
				public String logo;
				
				// 定义Project的构造函数
				public Project(String name, String description, String url, String logo) {
					this.name = name;
					this.description = description;
					this.url = url;
					this.logo = logo;
				}
				
				// 不设置 get 方法 ， 将无法通过 JSTL ${project.description} 等等方法来获取属性值， 同时需要注意以后可能需要设置set方法
				public String getDescription() {
					return description;
				}
				public String getUrl() {
					return url;
				}
				public String getLogo() {
					return logo;
				}
				public String getName() {
					return name;
				}
			}
		
		%>
		
		<%
			List<Project> projects = new ArrayList<>();
		
			// 添加个人项目数据
			projects.add(new Project("天码营博客","优雅简介的外观设计，细心的交互方式，帮助您更快捷地创建博客，更方便地浏览您的博客内容。",
					"#","/BlogSystem/static/img/cat.jpg"));
			projects.add(new Project("Markdown 引擎","Sample Blog 使用 Markdown 创建易读易写的博客文章。Markdown 由一些经过精挑细选的符号组成，作用一目了然。",
					"#","/BlogSystem/static/img/dog.jpg"));
			projects.add(new Project("Markdown 引擎","Sample Blog 使用 Markdown 创建易读易写的博客文章。Markdown 由一些经过精挑细选的符号组成，作用一目了然。",
					"#","/BlogSystem/static/img/chick.jpg"));
			
			// 使用 JSTL 必须在该页面下添加 项目列表属性，才能提取
			pageContext.setAttribute("projects", projects);
		%>
		
		<!-- 通过JSTL的方法获取 -->
		<c:forEach var="project" items="${projects}">		
			<div class="col-sm-4">	
				<img class="img-circle" src="${project.logo}" width="140" height="140">
				<h2>${project.name}</h2>
				<p>${project.description}</p>
				<p><a class="btn btn-default" href="${project.url}">访问 &raquo;</a></p>
			</div>	
		</c:forEach>
		
		</div>
	</div>
	
	<br>
	<hr>
	<!-- 增加页角  -->
 	<jsp:include page="/WEB-INF/common/footer.jsp" />
</body>

</html>
