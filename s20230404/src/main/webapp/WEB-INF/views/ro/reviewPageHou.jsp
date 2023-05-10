<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
         <%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/css/main.css" rel="stylesheet" type="text/css">
	<link href="/css/list.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

	<style>
		th, td { border-bottom: 2px solid rgb(64, 64, 64); }
	</style>
</head>

<body>
	
	<div id="img_benner">
		<img src="img/community-picture.png" alt="배너">
	</div>
   	
   	<h1><a href="mypage"><img src="img/myPage-picture.png" alt="마이페이지" width=250px height=250px></a></h1>
	
   	<h1>리 뷰</h1>
	<br>
	<a href="reviewPageTra" style="margin: 50px;">여행지</a>
	<a href="reviewPageHou" style="margin: 50px; color:#205E61;">숙소</a>
	<a href="reviewPageRes" style="margin: 50px;">맛집</a>
	<hr><br>

	<h3>작성한 숙소 리뷰 내역</h3>
	<br>
	<c:set var="num" value="${page.total - page.start + 1 }"></c:set>
	
	<div>
      	<table>
         	<c:forEach var="houReview" items="${listReviewPageHou}">
						<tr>
							<td style="width: 270px;">
							<a href="houDetail?hid=${houReview.house_id}">${houReview.h_name}</a>
							</td>
							<td style="width: 250px;">${houReview.r_content}</td>
							<td style="width: 130px; color: #ffd700;" >
								<c:choose>
									<c:when test="${houReview.r_score eq '1'}">
										<c:out value="★☆☆☆☆"/>
									</c:when>
									<c:when test="${houReview.r_score eq '2'}">
										<c:out value="★★☆☆☆"/>
									</c:when>
									<c:when test="${houReview.r_score eq '3'}">
										<c:out value="★★★☆☆"/>
									</c:when>
									<c:when test="${houReview.r_score eq '4'}">
										<c:out value="★★★★☆"/>
									</c:when>
									<c:when test="${houReview.r_score eq '5'}">
										<c:out value="★★★★★"/>
									</c:when>
									<c:otherwise>
										<c:out value="☆☆☆☆☆"/>
									</c:otherwise>
								</c:choose>				
							</td>
							<td style="width: 110px;">${houReview.create_date}</td>
							
							<c:set var="num" value="${num - 1 }"></c:set>
						</tr>
         	</c:forEach>
      	</table>
   	</div>
	
	<c:if test="${page.startPage > page.pageBlock }">
      	<a href="reviewPageHou?currentPage=${page.startPage - page.pageBlock }">[이전]</a>
   	</c:if>
   	<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
      	<a href="reviewPageHou?currentPage=${i }">[${i }]</a>
   	</c:forEach>
   	<c:if test="${page.endPage < page.totalPage }">
      	<a href="reviewPageHou?currentPage=${page.startPage + page.pageBlock }">[다음]</a>
   	</c:if>
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>