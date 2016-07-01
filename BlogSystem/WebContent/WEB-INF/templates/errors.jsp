<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">    
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>errors page</title>
</head>
<body>

	<!-- 导航条 -->	
	<jsp:include page="/WEB-INF/common/head.jsp" />


	<c:if test="${errors != null}">
		<c:forEach var="error" items="${errors}">
		  ${error} <hr><br>
		</c:forEach>
	</c:if>

	<label><a href="/BlogSystem/register">返回注册页面</a></label>
	<label><a href="/BlogSystem/login">返回登录页面</a></label>
	<!-- 增加页角  -->
 	<jsp:include page="/WEB-INF/common/footer.jsp" />

</body>
</html>