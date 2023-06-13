<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		.category-container{
			display: flex;
			flex-direction: row;
			align-items: center;
		}
	</style>
	<link href="<%=contextPath%>/css/main.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
</head>
<body>
<h1>나의 작성 리뷰</h1>
<div class="category-container">
	<a href="?category=travel">여행지</a>
	<a href="?category=house">숙소</a>
	<a href="?category=restaurant">맛집</a>
</div>

<p>Score: ${review.r_score}</p>
<p>Content: ${review.r_content}</p>
<p>Created Date: ${review.create_date}</p>
<p>Modified Date: ${review.modified_date}</p>

</body>
<c:import url="../fragments/footer.jsp"/>
</html>