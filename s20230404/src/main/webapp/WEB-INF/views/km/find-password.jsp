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
	<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<body>
<div class="loginLogo">
	<a href="<%=contextPath%>/"><img src="<%=contextPath%>/img/gosunee.png"></a>
</div>
<h1>비밀번호 찾기</h1>
<form action="<%=contextPath%>/password/info" method="get">
	<table>
	<tr>
		<td><label for="email">아이디</label></td>
		<td><input type="text" name="email" id="email" required></td>
	</tr>
	<tr>
		<td><label for="name">이름</label></td>
		<td><input type="text" name="name" id="name" required></td>
	</tr>
	<tr>
		<td><label for="phone">휴대폰 번호</label></td>
		<td><input type="tel" name="phone" id="phone" required></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" style="width: 275px; height: 30px" value="비밀번호 찾기"></td>
	</tr>
	<tr>
		<td></td>
		<td style="text-align: center">
			<c:if test="${not empty error}">
			${error}
			</c:if>
		</td>
	</tr>
	</table>
</form>
</body>
</html>