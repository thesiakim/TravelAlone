<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="img_benner">
		<img src="img/main-picture.png" alt="배너">
	</div>
	


<!-- 맛집종류 -->
	<div >  																						
		<c:forEach items="${boardList}" var="list">
					<td>
							<!-- 컨트롤러로 보내는거 -->
						<a href="restaurantCodeFilter?code=${list.code}">${list.value}</a>									
					</td>
		</c:forEach>																
			   <c:set var="num" value="${page.total-page.start+1 }"></c:set>
	</div>				
	
	
	
<!-- 검색 -->
	<form action="restaurantSearch">
		<select name="search">
			<option value="s_title">제목</option>
			<option value="s_content">내용</option>
			
		</select>
		 <input type="text" name="keyword" placeholder="keyword을 입력하세요" value="${search}">
		<a href="restaurantSearch?search=${search}&amp;keyword=${keyword}"> 검색</a>
		<p>
	</form>

	<c:set var="num" value="${page.total-page.start+1 }"></c:set>
	
<!-- 지역 종류 -->
	<c:forEach items="${boardLocList}" var="list">
		<td>
			<!-- 컨트롤러로 보내는거 -->
			<a href="resLocCodeFilter?code=${list.code}">${list.value}</a> 
								
		</td>
	</c:forEach>	
	
	<hr>
<!-- 맛집리스트 테이블 -->

			<table style="margin:auto;">
				<tr>
					<td hidden>번호</td>													
					<td>지역</td>
					<td>맛집명</td>
					<td>평점</td>
					<td>리뷰수</td>


						</tr>
				<c:forEach items="${restaurantList}" var="restaurant">
					<tr>
						<td hidden>${restaurant.restaurant_id}</td>
					 	<td>${restaurant.r_common_loc}</td>
						<td > <a href="resDetail?rid=${restaurant.restaurant_id}">${restaurant.r_name}</a></td>
					<%-- 	<td>${restaurant.r_avgscore}</td> --%>
						<td>
							<c:choose>
								<c:when test="${restaurant.r_avgscore eq '1'}">
									<c:out value="★☆☆☆☆"/>
								</c:when>
								<c:when test="${restaurant.r_avgscore eq '2'}">
									<c:out value="★★☆☆☆"/>
								</c:when>
								<c:when test="${restaurant.r_avgscore eq '3'}">
									<c:out value="★★★☆☆"/>
								</c:when>
								<c:when test="${restaurant.r_avgscore eq '4'}">
									<c:out value="★★★★☆"/>
								</c:when>
								<c:when test="${restaurant.r_avgscore eq '5'}">
									<c:out value="★★★★★"/>
								</c:when>
								<c:otherwise>
									<c:out value="☆☆☆☆☆"/>
								</c:otherwise>
							</c:choose>
						</td>
						<td>${restaurant.r_revcount}</td>
		</c:forEach>
		
	</table>
	<hr>

		<a style="text-align: right;" href="resWriteForm">글작성</a>
<!-- 페이징 처리   -->

 <div>
	<c:if test="${page.startPage > page.pageBlock }">
		<a href="res?currentPage=${page.startPage-page.pageBlock}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
		<a
			href="res?currentPage=${i}">[${i}]</a>
	</c:forEach>
	<c:if test="${page.endPage < page.totalPage }">
		<a
			href="res?currentPage=${page.startPage+page.pageBlock}">[다음]</a>
	</c:if>
</div> 

</body>
	<c:import url="footer.jsp"/>
</html>