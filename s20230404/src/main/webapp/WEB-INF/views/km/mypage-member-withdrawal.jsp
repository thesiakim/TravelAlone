<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
	<script defer src="/js/mypage-Withdrawal.js"></script>  
</head>
<body>
<h1>회원 탈퇴</h1>
<p>정말로 탈퇴하시겠습니까?</p>
    <input type="text" name="memberEmail" id="memberEmail" value="${memberId}" readonly>
    <label for="password">비밀번호:</label>
    <input type="password" id="password" name="password">
    <button type="button" onclick="withdrawal()">확인</button>
</body>
</html>