<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>create blog</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">    
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 引用富文本编辑工具 -->
<link rel="stylesheet" href="/BlogSystem/static/serve/bootstrap3-wysihtml5.css">
<script src="/BlogSystem/static/serve/bootstrap3-wysihtml5.js"></script>
</head>
<body>

	<!-- 导航条 -->	
	<jsp:include page="/WEB-INF/common/head.jsp" />
	
	<div class="container">
		<div class="page-header">
	    <h2>新建博客
				<small> 在这里新建一篇博客</small>
			</h2>
		</div>

		<div class="row">
			<div class="col-sm-8">
				
				<form action="/BlogSystem/createPost" method="POST">
  				<div class="form-group">
    				<label for="title">标题</label>
			  	  <input type="text" class="form-control" id="title" name="title" oninput="autoSava()" placeholder="文章的标题" autofocus>
				  </div>
				  <div class="form-group">
				    <label for="content">内容</label>
				    <textarea class="form-control" id="content" name="content" oninput="autoSava()" placeholder="文章的内容" rows="18">
				    </textarea>
				    <script type="text/javascript">
  						$('#content').wysihtml5();
						</script>
				  </div>
				  <div id="testdiv">
				  	<button type="submit" class="btn pull-right btn-primary" onclick="show_confirm()">提交</button>
				  </div>
				</form>
			  
			</div>

			<div class="col-sm-3 col-sm-offset-1">信息栏
			
				<jsp:include page="/WEB-INF/common/userInfo.jsp" />
			
			</div>

		</div>
	</div>
	<br>
	<hr>
	
	<!-- 增加页角  -->
 	<jsp:include page="/WEB-INF/common/footer.jsp" />
	<script src="/BlogSystem/static/scripts/storage.js">
	</script>
</body>
</html>