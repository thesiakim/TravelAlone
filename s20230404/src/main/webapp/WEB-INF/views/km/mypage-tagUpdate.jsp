<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/login.css">
	<link rel="stylesheet" href="css/list.css">
<title>Insert title here</title>
<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
	<style type="text/css">
		#userTag {
			width: 120px;		height: 40px;
			border-radius: 7px;	font-weight:bolder;
			font-size: 15px;	color:white;
			background-color: #5F9EA0; 
		}
	</style>
</head>
<body>
	<h2>${nickName} 님</h2>
	
	<h3>등록한 태그</h3>
		<c:forEach var="interest" items="${mypageTagUpdate}">
			<div id="userTag">${interest.word}</div>
		</c:forEach>
	<h3>등록하지 않은 태그</h3>


	<input type="submit" value="완료">
	<input type="reset" value="취소">

</body>
</html>