<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">    
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

	<!-- 导航条 -->	
	<jsp:include page="/static/common/head.jsp" />
	
	<!-- ------------------------------------------------------------------------------------------------- -->
	
	<!-- 主题内容 -->
	
	<div class="container">  <!--使用支持响应式布局的容器类型，另一个是container-fluid占据全部视口（viewpoint）的容器-->
    <div class="title">
      <h3>欢迎来到天码营，这里将会给你提供最好的在线教学环境<small> 如果你还没有注册，请在下方注册自己的个人信息</small></h3>
    </div>
    <hr>
    <br>
    <form action="/BlogSystem/register" method="POST" class="form-group">
      <label>邮 箱</label> 
      <input type="text" name="email" class="form-control" placeholder="tianmaying@163.com" required="">  <br>
      <label>用户名</label>
      <input type="text" name="userName" class="form-control" placeholder="tianmaying" required="">  <br>
      <label>设置密码</label>
      <input type="password" name="password" class="form-control" required=""><br>
      <label>确认密码</label>
      <input type="password" name="rePassword" class="form-control" required="">  <br>
      <button type="submit" class="btn btn-primary">立即注册</button>
    </form>  
    <p>如果您已有帐号，请点此登录</p><a href="/BlogSystem/static/templates/login.jsp"><button class="btn btn-success">登录</button></a>
  </div>
  <br>
  <hr>
  
  <!-- ------------------------------------------------------------------------------------------------- -->
  
   <!-- 增加页角  -->
 	<jsp:include page="/static/common/footer.jsp" />	
 	
</body>
</html>