<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 사용자 스크립트 추가 -->
</head>
	<link href="<%=contextPath%>/css/list.css" rel="stylesheet" type="text/css">
	<link href="<%=contextPath%>/css/login.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<body>
<div class="loginLogo">
	<a href="<%=contextPath%>/"><img src="<%=contextPath%>/img/gosunee.png"></a>
</div>
<h1>아이디 찾기</h1>
<h2>회원님의 아이디는 ${email} 입니다.</h2>
<input type="button" style="width: 275px; height: 30px" value="로그인" onclick="<%=contextPath%>window.location.href='/login'">
</body>
</html>