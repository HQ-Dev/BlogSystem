<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
<title>${post.title}</title>
</head>
<body>

	<!-- 导航条 -->
	<jsp:include page="/WEB-INF/common/head.jsp" />	
	
	<div class="container">
		<div class="page-header">
	    <h1>${user.userName} 
				<small> 欢迎来到你的博客主页.</small>
			</h1>
		</div>

		<div class="row">
			<div class="col-sm-8">
				
				<h2>${post.title }</h2>
				<label>${post.createdDate }</label>
				<hr>
        <p>${post.content }</p>
        <hr>
        <br>
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
				<jsp:include page="/WEB-INF/common/userInfo.jsp" />
			</div>

		</div>
	</div>
	
	<!-- ------------------------------------------------------------------------------------------- -->

	<!-- 增加页角  -->
 	<jsp:include page="/WEB-INF/common/footer.jsp" />

</body>
</html>