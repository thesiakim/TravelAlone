<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		td{
			padding: 10px;
		}

	</style>
</head>
<body>

		<!-- 여행지리스트 테이블 -->
		  <h2>즐겨찾기 </h2>
		<table style="margin:auto;">
			<tr>
				<td><h3><a href="/mypage/favorite?category=tra">여행지</a></h3></td>
				<td><h3><a href="/mypage/favorite?category=hou">숙소</a></h3></td>
				<td><h3><a href="/mypage/favorite?category=res">맛집</a></h3></td>
			</tr>
		</table>

		<table style="margin:auto;">
		<tr>
			<td hidden>번호</td>
			<td>사진</td>
			<td>지역</td>
			<td>이름</td>
			<td>평점</td>
			<td>리뷰수</td>
			<td>등록일자</td>
		</tr>
		<c:forEach items="${favorites}" var="favorite">
			<tr>
				<td hidden>${favorite.id}</td>
				<td>
					<c:url value='/display' var='url'><c:param name='file' value='${favorite.thumbnail}'/></c:url>
					<img alt="#" src="${url}" width="100%" height="100%">
				</td>
			 	<td>${favorite.loc}</td>
				<td > <a href="traDetail?tid=${favorite.id}">${favorite.name}</a></td>
				<td>
					<c:choose>
						<c:when test="${favorite.score eq '1'}">
							<c:out value="★☆☆☆☆"/>
						</c:when>
						<c:when test="${favorite.score eq '2'}">
							<c:out value="★★☆☆☆"/>
						</c:when>
						<c:when test="${favorite.score eq '3'}">
							<c:out value="★★★☆☆"/>
						</c:when>
						<c:when test="${favorite.score eq '4'}">
							<c:out value="★★★★☆"/>
						</c:when>
						<c:when test="${favorite.score eq '5'}">
							<c:out value="★★★★★"/>
						</c:when>
						<c:otherwise>
							<c:out value="☆☆☆☆☆"/>
						</c:otherwise>
					</c:choose>												
				</td>																				
				<td>${favorite.reviewCount}</td>
				<td>${favorite.modifiedDate}</td>
		</c:forEach>
		
	</table>
	<hr>
	<!-- 페이징 처리 페이징 처리  페이징 처리  페이징 처리   -->

		<div class="page-button">
			<c:choose>
				<c:when test="${not empty param.category}">
					<c:set var="queryString" value="&category=${param.category}" />
				</c:when>
				<c:otherwise>
					<c:set var="queryString" value="" />
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${not empty param.page}">
					<c:set var="currentPage" value="${param.page}" />
				</c:when>
				<c:otherwise>
					<c:set var="currentPage" value="1" />
				</c:otherwise>
			</c:choose>

			<c:set var="startPage" value="${currentPage - 5}" />
			<c:if test="${startPage < 0}">
				<c:set var="startPage" value="1" />
			</c:if>

			<c:set var="endPage" value="${currentPage + 5}" />
			<c:if test="${endPage > totalPage}">
				<c:set var="endPage" value="${totalPage}" />
			</c:if>

			<c:choose>
				<c:when test="${currentPage > 1}">
					<a href="?page=${currentPage - 1}${queryString}">이전</a>
				</c:when>
				<c:otherwise>
					<span class="disabled">이전</span>
				</c:otherwise>
			</c:choose>

			<c:forEach var="page" begin="${startPage}" end="${endPage}">
				<c:choose>
					<c:when test="${page == currentPage}">
						<span class="current">${page}</span>
					</c:when>
					<c:otherwise>
						<a href="?page=${page}${queryString}">${page}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:choose>
				<c:when test="${currentPage < (totalPage)}">
					<a href="?page=${currentPage + 1}${queryString}">다음</a>
				</c:when>
				<c:otherwise>
					<span class="disabled">다음</span>
				</c:otherwise>
			</c:choose>
		</div>
	

</body>
</html>