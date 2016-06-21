<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">    
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

	<!-- 导航条 -->	
	<jsp:include page="/static/common/head.jsp" />
	
	<!-- ------------------------------------------------------------------------------------------------- -->
	
	<!-- 主体内容 -->
	<div class="container">  <!--使用支持响应式布局的容器类型，另一个是container-fluid占据全部视口（viewpoint）的容器-->
    <div class="title">
      <h3>欢迎来到天码营，这里将会给你提供最好的在线教学环境<small> 如果你还没有注册，请点击下方的注册按钮进行注册</small></h3>
    </div>
    <hr>
    <br>
    <form action="/BlogSystem/login" method="POST" class="form-group">
      <label>用户名</label> 
      <input type="text" name="userName" class="form-control" placeholder="用户名" required="">  <br>
      <label>密码</label>
      <input type="password" name="password" class="form-control" placeholder="密码" required=""><br>
      <div class="checkbox">
    		<label>
      		<input type="checkbox" name="remember-me" value="remember-me"> 下次自动登录
    		</label>
  		</div>
      <button class="btn btn-success">登录</button>
    </form>  
    <a href="/BlogSystem/static/templates/register.jsp"><button type="submit" class="btn btn-primary">立即注册</button></a>
  </div>
  <br>
  <hr>
  
  <!-- ---------------------------------------------------------------------------------------------------- -->
  
  <!-- 增加页角  -->
 	<jsp:include page="/static/common/footer.jsp" />

</body>
</html>