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
<!-- 引用富文本编辑工具 -->
<link rel="stylesheet" href="/BlogSystem/static/serve/bootstrap3-wysihtml5.css">
<script src="/BlogSystem/static/serve/bootstrap3-wysihtml5.js"></script>
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
        <br>
        
        
        <!-- 增加评论功能功能 -->
				<div class="doComment">
					<form action="/BlogSystem/postInfo.do" method="POST">
						<div class="form-group">
				      <hr>
				      <textarea class="form-control" id="comment" name="comment" 
				      placeholder="你可以在这里发表评论......"  rows="5" required="">
				      </textarea>
				      <script type="text/javascript">
  						$('#comment').wysihtml5();
						  </script>	
				 	  </div>
				 	  <button type="submit" class="btn pull-right btn-primary">发表评论</button>
					</form>
				</div>
				<br><br>
				<c:choose>
					<c:when test="${comments != null}">
						<c:forEach var="comment" items="${comments}">
							<div class="blog-comment">
							  <h5 class="blog-comment-creator"><a href="#">${comment.creator.userName}</a></h5>
								<p class="blog-comment-content"> ${comment.content}  ${comment.commentDate }</p>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
					  <h6> 你的博文暂时还有评论... </h6>
					</c:otherwise>
				</c:choose>
				
        <hr>
        
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