<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/mypage.css">
<title>Insert title here</title>
<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<script defer type="text/javascript">
	function openEditWindow() {
		// 수정창을 띄울 URL 설정
		var editUrl = "/mypage/member-info";

		// 미니창으로 수정창을 띄우기
		var editWindow = window.open(editUrl, "edit_window", "width=500,height=400");


	}

	function openWithdrawalWindow() {
		// 수정창을 띄울 URL 설정
		var editUrl = "/mypage/withdrawal";

		// 미니창으로 수정창을 띄우기
		var editWindow = window.open(editUrl, "edit_window", "width=500,height=400");


	}

	function openProfileWindow() {
		// 수정창을 띄울 URL 설정
		var editUrl = "/mypage/profile";

		// 미니창으로 수정창을 띄우기
		var editWindow = window.open(editUrl, "edit_window", "width=500,height=400");


	};

	function deleteProfile() {

		if(confirm("기본 이미지로 설정하시겠습니까?")){

			$.ajax({
				type: "POST",
				url: "/api/v1/mypage/profile/normal",
				dataType: "text",
				contentType: "application/json; charset=utf-8",
			})
					.done(function (responseText) {
						alert(responseText+"! 수정되었습니다.");
						window.location.reload();
					})
					.fail(function (error) {
						alert(JSON.stringify(error));
					});
		}
	};
</script>

</head>
<body>
<div id="img_benner">
		<img src="img/main-picture.png" alt="배너">
</div>

<div class="profile-block">
	<div class="img-block">
		<div>
			<img
					class="profile-pic"
					src="/display?file=${response.storedImgName}"
					alt="#"
			/>
		</div>
		<div class="button-block">
			<button onclick="openProfileWindow()">수정</button>
			<button onclick="deleteProfile()">삭제</button>
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

<div class="member-info">
	<div class="info">
		<div><h1>기본 회원 정보</h1></div>
		<div>
			<table>
				<tr>
					<th>ID</th>
					<td>${response.email}</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td>${response.nickName}</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${response.name}</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>${response.phone}</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>${response.gender}</td>
				</tr>
			</table>
		</div>
		<div class="info-btn">
			<button onclick="openEditWindow()">수정</button>
			<button onclick="openWithdrawalWindow()">회원탈퇴</button>
		</div>
	</div>
	<div class="writing-history">
		<div><h1>활동 내역</h1></div>
		<div>
			<table>
				<tr>
					<th><a href="myPageCommunityList">내가 작성한 글</a></th>
					<td>${response.writingCount}</td>
				</tr>
				<tr>
					<th>내가 작성한 리뷰</th>
					<td>${response.reviewCount}</td>
				</tr>
				<tr>
					<th>문의 내역</th>
					<td>${response.csCount}</td>
				</tr>
				<tr>
					<th>즐겨 찾기</th>
					<td>${response.favCount}</td>
				</tr>
			</table>
		</div>
	</div>
</div>

</body>

	<c:import url="footer.jsp"/>
</html>