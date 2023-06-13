<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=contextPath%>/css/mypage.css">
<link rel="stylesheet" href="<%=contextPath%>/css/login.css">
<link rel="stylesheet" href="<%=contextPath%>/css/list.css">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script defer src="<%=contextPath%>/js/userPage.js"></script>
</head>
<body>
	<div class="profile-block">
		<div class="img-block">
			<c:url value='/display' var='url'><c:param name='file' value='${response.storedImgName}'/></c:url>
			<img class="profile-pic" alt="#" src="${url}" width="400px" height="400px">	
		</div>
		<div class="content-block">
			<div class="nickname">${response.nickName}</div>
			<div class="tags">
			<c:forEach var="interest" items="${response.interests}">
				<div class="tag">${interest.word}</div>
			</c:forEach>
			</div>
		</div>
	</div>
	
	<div class="review-block">
	<c:forEach var="scoreCount" items="${response.scoreCounts}">
		<form action="userScoreUpdate">
			<div class="container" >
				<div class="bar-label">${scoreCount.word}</div>
				<div class="bar-container">
					<input type="hidden" name="member_id" value="${member_id}">
					<input type="hidden" name="s_common_spec" value="${scoreCount.s_common_spec}">
					<input type="submit" style="width: 160px;" value="${scoreCount.count}">
				</div>
			</div>
		</form>
	</c:forEach>
	</div>
	
	<table>
		<tr>
			<td colspan="2">
				<h1>기본 회원 정보</h1>
			</td>
		</tr>
		<tr>
			<td>ID</td>
			<td>${response.email}</td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td>${response.nickName}</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${response.gender}</td>
		</tr>
	</table>
</body>
</html>