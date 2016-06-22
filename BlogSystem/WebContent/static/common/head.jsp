<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Blog Archive</title>
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
</head>
<body>
	<!-- 导航条 -->
	<!-- 创建默认导航栏的步骤，在nav标签中添加class .navbar  .navbar-default, navbar-default表示导航栏是白底黑色，
	而 .navbar-inverse表示导航栏是黑底白字。  如果想让"导航栏"固定在页面的顶部，还需要添加 .navbar-fixed-top 。如果想
	让导航栏随页面一起滚动固定在顶部，就添加 .navbar-static-top (并且该class不需要<body>添加内边距) -->
	<!-- 如果导航条设置了 .navbar-fixed-top 为了防止它与文章内容发生重叠，<body>需要设置 50像素的内边距(padding) -->
	<!-- 增加属性 role="navigation" 可提高可访问性（和浏览器的兼容性）-->
	<nav class="navbar navbar-inverse navbar-static-top" role="navigatiion"> 	   <!-- <nav> 标签是 html5 中的新标签，标注一个导航链接的区域，是一个增强语义的标签，方便辨识文档结构。语义化有利于网页的SEO）-->
		<div class="container">		  <!-- 定义了一个容器。[问老师,将下面的标签放在 .container 中有什么好处？更加容易管理吗？] -->
			<div class="navbar-header">
				<a class="navbar-brand" href="/BlogSystem/homepage.jsp">天码营博客之家</a>  	<!-- .navbar-brand 让文本看起来更加大一号 -->
				<!-- 给导航栏添加响应式的特性，即在手机或者小屏幕下点击会有链接显示。 首先被折叠的内容必须包裹在有 .collapse, .navbar-collapse的<div>中。
				而折叠起来的导航栏实际上就是一个带有 .navbar-toggle和两个data-元素的按钮。第一个是 data-toggle，用于告诉 JavaScript 需要对按钮做什么，
				第二个是 data-target，指示要切换到哪一个元素。三个带有 .icon-bar 的 <span> 创建所谓的汉堡按钮。这些会切换为
				 .nav-collapse <div> 中的元素。 -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>    <!-- 在小屏幕右上角会有一个折叠按钮，现有3条杠，随<span>的增加而增加 -->
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>	 
			</div>
		
			<!-- 除了navbar-header 天码营博客之家外，向导航栏添加别的链接 -->
			<!-- 为了在折叠按钮点击之后显示内容，被显示的内容（导航栏链接）需要被<div>包裹，并含有规定的 class -->
			<div id="navbar" class="collapse navbar-collapse">     <!-- 其中的 id="navbar" 已被折叠按钮引用到 -->
				<!-- 为了向导航栏添加链接，只需要简单地添加带有 class .nav、.navbar-nav 的 <ul> 无序列表即可。 -->
				<ul class="nav navbar-nav">
					<c:choose>
						<c:when test="${sessionScope.user != null}"> <!-- 作为判断条件表示当前用户已经登录。 -->
							<li><a href="/BlogSystem/static/templates/userposts.jsp">我的首页</a></li>
							<li class="dropdown">    <!-- "dropdown" 语义化类 -->
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">   <!-- data-toggle="dropdown" 有实意，显示 -->
								帐号管理<span class="caret"></span></a>    <!-- <span class="carset"> 一个三角形图案 -->
								<ul class="dropdown-menu">    <!-- .dropdown-menu 包装了下拉显示菜单，更协调美观 -->
									<li class="dropdown-header">管理</li>     <!-- .dropdown-header 为下拉菜单区域提供了标题,说明此区域的职责 -->
									<li><a href="/BlogSystem/static/templates/userposts.jsp">Blog Archive</a></li>
									<li><a href="/BlogSystem/static/templates/createPost.jsp">Create New Blog</a></li>
									<li class="divider"></li>     <!-- .divider 是一条分割线 -->
									<c:if test="${true}">
									<li class="dropdown-header">帐号</li>
									<li><a href="#">Personal Information</a></li>
									<li><a href="#">Modifier Password</a></li>
									<li><a href="#">Logout</a></li>
									</c:if>
								</ul>
							</li>
						</c:when>
						<c:otherwise>
					  	<li><a href="/BlogSystem/static/templates/login.jsp">登录</a></li>
					  	<li><a href="/BlogSystem/static/templates/register.jsp">注册</a></li>
					  </c:otherwise>
					</c:choose>
				</ul>
				<!-- 将表单放置于 .navbar-form 之内可以呈现很好的垂直对齐，并在较窄的视口（viewport）中呈现折叠状态。
				 使用对齐选项可以规定其在导航条上出现的位置。 -->
				<form class="navbar-form navbar-right" role="search">    <!-- role 角色扮演的属性 -->
					<div class="form-group">    <!-- 使用 .form-group 在位置和显示上有更好的视觉体验 -->
						<input type="text" class="form-control" placeholder="请输入搜索关键词">
						<!-- 加了 .form-control 的效果是宽度100%，设置边框为浅灰色，控件有4px圆角，设置了placeholder的颜色为#999 -->
					</div>
					<button type="submit" class="btn btn-default">Go</button>
				</form>
			</div>				
		</div>
	</nav>
	
	<!-- !!!!! -->
	<!-- 被 include 的页面要保证完整的功能，必须将这两个 jQuery 和 javascript 文件放在 <body> 之中，原因暂时不明确，
	我猜是可以及时编译进文件产生效果 -->
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
	<script src="/BlogSystem/static/serve/bootstrap3-wysihtml5.js"></script>
</body>
</html>