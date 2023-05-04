<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/mypage.css">
<link rel="stylesheet" href="css/list.css">
<link rel="stylesheet" href="css/login.css">
<title>Insert title here</title>
<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<script defer src="/js/mypage-info-update.js"></script>
</head>
<body>


<div class="profile-block">
	<div class="img-block">
			<img class="profile-pic" 
				 src="/display?file=${response.storedImgName}"
				 alt="#"/>
		<div class="button-block">
			<button>수정</button>
			<button>삭제</button>
		</div>
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
	<div class="container">
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
		<td colspan="2" style="width: 620px;">
			<h1>기본 회원 정보</h1>
		</td>
		<td colspan="2" style="width: 620px;">
			<h1>활동 내역</h1>
		</td>
	</tr>
	<tr>
		<td>ID</td>
		<td>${response.email}</td>
		<td><a href="myPageCommunityList">내가 작성한 글</a></td>
		<td>${response.writingCount}</td>
	</tr>
	<tr>
		<td>닉네임</td>
		<td>${response.nickName}</td>
		<td>내가 작성한 리뷰</td>
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
	<button>회원탈퇴</button>
</div>

</body>
	<c:import url="footer.jsp"/>
</html>