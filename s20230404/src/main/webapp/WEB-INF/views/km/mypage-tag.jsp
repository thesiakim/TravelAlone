<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="<%=contextPath%>/css/login.css">
	<link rel="stylesheet" href="<%=contextPath%>/css/list.css">
<title>Insert title here</title>
<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
	<style type="text/css">
		.userTag {
			width: 120px;		height: 40px;
			border-radius: 7px;	font-weight:bolder;
			font-size: 15px;	color:white;
			background-color: #5F9EA0; 
		}
	</style>
	<style>
		/* 등록한 태그 목록 스타일 */
		.userTag {
			cursor: pointer;
			opacity: 1;
			transition: opacity 0.5s;
			animation: slide-up 0.5s ease;
		}

		/* 슬라이드 업 애니메이션 */
		@keyframes slide-up {
			0% {
				opacity: 0;
				transform: translateY(50px);
			}
			100% {
				opacity: 1;
				transform: translateY(0);
			}
		}

		/* 등록되지 않은 태그 목록 스타일 */
		.dont {
			cursor: pointer;
			color: blue;
			text-decoration: underline;
		}

		.dont:hover {
			color: red;
		}
	</style>
</head>
<body>
<h2>${nickName} 님</h2>

<h3>등록한 태그</h3>
<c:forEach var="commonInterest" items="${interests.commonInterests}">
	<c:choose>
		<c:when test="${commonInterest.savedChecked}">
			<div class="userTag saved" id="saved${commonInterest.mcd}" style="display: block" onclick="toggleDisplay('dont${commonInterest.mcd}', this)">${commonInterest.word}</div>
		</c:when>
		<c:otherwise>
			<div class="userTag saved" id="saved${commonInterest.mcd}" style="display: none" onclick="toggleDisplay('dont${commonInterest.mcd}', this)">${commonInterest.word}</div>
		</c:otherwise>
	</c:choose>
</c:forEach>

<h3>등록하지 않은 태그</h3>
<c:forEach var="commonInterest" items="${interests.commonInterests}">
	<c:choose>
		<c:when test="${commonInterest.savedChecked}">
			<div class="userTag dont" id="dont${commonInterest.mcd}" style="display: none" onclick="toggleDisplay('saved${commonInterest.mcd}', this)">${commonInterest.word}</div>
		</c:when>
		<c:otherwise>
			<div class="userTag dont" id="dont${commonInterest.mcd}" style="display: block" onclick="toggleDisplay('saved${commonInterest.mcd}', this)">${commonInterest.word}</div>
		</c:otherwise>
	</c:choose>
</c:forEach>

<input type="submit" value="완료" onclick="interestSave()">
<input type="reset" value="취소" onclick="windowClose()">

<script>
	function toggleDisplay(id, clicked) {
		const element = document.getElementById(id);
		console.log(element);
		if (element) {
			element.style.display = element.style.display === 'none' ? 'block' : 'none';
		}
		clicked.style.display = 'none';
	}

	function interestSave(){
		const savedTagIds = [];
		const savedTags = document.querySelectorAll('.saved');
		savedTags.forEach(tag => {
			if (tag.style.display === 'block') {
				const id = tag.id;
				savedTagIds.push(id);
			}
		});

		const data = {savedTagIds};
		console.log(data);

		$.ajax({
			type: "PATCH",
			url: "/api/v1/mypage/interest",
			dataType: "text",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
		})
				.done(function (responseText) {
					alert("관심사 :"+responseText+"개가 설정되었습니다.");
					window.close();
					window.opener.location.reload();
				})
				.fail(function (error) {
					alert(JSON.stringify(error));
				});

	}

	function windowClose(){

		if (confirm("관심사가 저장되지 않았습니다. 닫으시겠습니까?")){
			window.close();
		}
	}

</script>
</body>
</html>