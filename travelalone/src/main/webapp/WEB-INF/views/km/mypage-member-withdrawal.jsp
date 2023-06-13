<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
	<script defer src="<%=contextPath%>/js/mypage-Withdrawal.js"></script>
	<link href="<%=contextPath%>/css/list.css" rel="stylesheet" type="text/css">
	<link href="<%=contextPath%>/css/login.css" rel="stylesheet" type="text/css">
	<link href="<%=contextPath%>/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>회원 탈퇴</h1>
	<table>
		<tr>
			<td colspan="2">
				<p style="color: red; margin-left: 100px;" >정말로 탈퇴하시겠습니까?</p>
			</td>
		</tr>
		<tr>
			<td>
				이메일 :
			</td>
			<td>
				 <input type="text" name="memberEmail" id="memberEmail" value="${memberId}" readonly>
			</td>
		</tr>
		<tr>
			<td>
				<label for="password">비밀번호 :</label>
			</td>
			<td>
				<input type="password" id="password" name="password">
			</td>
		</tr>
	
	</table>
    <button type="button" onclick="withdrawal()">확인</button>
    <button type="reset">취소</button>
</body>
</html>