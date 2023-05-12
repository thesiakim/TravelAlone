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
<h1>아이디 찾기</h1>
<form action="<%=contextPath%>/api/v1/password" method="post">
	<p>
		<label for="email">아이디</label>
		<input type="text" name="email" id="email" required>
	</p>
	<p>
		<label for="name">이름</label>
		<input type="text" name="name" id="name" required>
	</p>
	<p>
		<label for="phone">휴대폰 번호</label>
		<input type="tel" name="phone" id="phone" required>
	</p>
	<p>
		<input type="submit" value="비밀번호 찾기">
	</p>
	<p>
		<c:if test="${not empty error}">
			<p>${error}</p>
		</c:if>
	</p>
</form>
</body>
</html>