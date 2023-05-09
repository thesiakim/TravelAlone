<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/mypage.css">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/list.css">
<title>Insert title here</title>
	<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
	<script defer src="/js/mypage.js"></script>
<c:import url="../fragments/header.jsp"></c:import>
</head>
<body>
<div id="img_benner">
	<img src="img/main-picture.png" alt="배너">
</div>
<div class="profile-block">
	<div class="img-block">
		<div>
		<c:url value='/display' var='url'><c:param name='file' value='${response.storedImgName}'/></c:url>
			<img class="profile-pic" alt="#" src="${url}" width="400px" height="400px">	
		</div>
		<div class="button-block">
			<button onclick="openProfileWindow()">수정</button>
			<button onclick="deleteProfile()">삭제</button>
		</div>
	</div>
	<br>

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
	<div class="score-container">
		<div class="bar-label">${scoreCount.word}</div>
		<div class="bar-container">
			<div class="bar" style="width: 70%">
				<div class="bar-text">${scoreCount.count}</div>
			</div>
		</div>
	</div>
	</c:forEach>
</div>
	<table>
		<tr>
			<td colspan="2" width="600px;">
				<h1>기본 회원 정보</h1>
			</td>
			<td colspan="2" width="600px;">
				<h1>활동 내역</h1>
			</td>
		</tr>
		<tr>
			<td width="150px;">ID</td>
			<td width="250px;">${response.email}</td>
			
			<td width="100px;"><a href="myPageCommunityList">내가 작성한 글</a></td>
			<td width="50px;">${response.writingCount}</td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td>${response.nickName}</td>
			
			<td><a href="reviewPageTra">내가 작성한 리뷰</a></td>
			<td>${response.reviewCount}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${response.name}</td>
			
			<td>문의 내역</td>
			<td>${response.csCount}</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>${response.phone}</td>
			
			<td>즐겨 찾기</td>
			<td>${response.favCount}</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${response.gender}</td>
			<td></td>
			<td></td>
		</tr>
	</table>
	<div class="info-btn">
		<button onclick="openEditWindow()">수정</button>
		<button onclick="openWithdrawalWindow()">회원탈퇴</button>
	</div>
</body>

	<c:import url="footer.jsp"/>
</html>