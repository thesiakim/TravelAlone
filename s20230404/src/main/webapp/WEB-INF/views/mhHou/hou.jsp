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
	


<!-- 숙소종류 -->
	<div >  																						
		<c:forEach items="${boardList}" var="list">
					<td>
							<!-- 컨트롤러로 보내는거 -->
						<a href="houseCodeFilter?code=${list.code}">${list.value}</a>									
					</td>
		</c:forEach>																
			   <c:set var="num" value="${page.total-page.start+1 }"></c:set>
	</div>				
	
	
	
	
	
<!--검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 -->	
	<form action="houseSearch">
		<select name="search">
			<option value="s_title">제목</option>
			<option value="s_content">내용</option>
			
		</select> 
		<input type="text" name="keyword" placeholder="keyword을 입력하세요" value="${search}">
		<!-- <button type="submit">keyword검색</button> -->
		   <a href="houseSearch?search=${search}&amp;keyword=${keyword}">keyword검색</a>
		
		<p>
	</form>

	<c:set var="num" value="${page.total-page.start+1 }"></c:set>
	
	
<!-- 지역 종류 지역 종류  지역 종류  지역 종류  지역 종류  지역 종류  -->
	<c:forEach items="${boardLocList}" var="list">
		<td>
			<!-- 컨트롤러로 보내는거 -->
			<a href="houLocCodeFilter?code=${list.code}">${list.value}</a> 
				
		</td>
	</c:forEach>
	
			<hr>		
	<!-- 숙소리스트 테이블  숙소리스트 테이블  숙소리스트 테이블  숙소리스트 테이블  숙소리스트 테이블-->

	<table style="margin:auto;">
		<tr>
			<td hidden>번호</td>													
			<td>지역</td>
			<td>숙소명</td>
			<td>평점</td>
			<td>리뷰수</td>			
		</tr>
		<c:forEach items="${houseList}" var="house">
			<tr>
				<td hidden>${house.house_id}</td>
			 	<td>${house.h_common_loc}</td>
				<td > <a href="houDetail?hid=${house.house_id}">${house.h_name}</a></td>
				<td>
					<c:choose>
						<c:when test="${house.h_avgscore eq '1'}">
							<c:out value="★☆☆☆☆"/>
						</c:when>
						<c:when test="${house.h_avgscore eq '2'}">
							<c:out value="★★☆☆☆"/>
						</c:when>
						<c:when test="${house.h_avgscore eq '3'}">
							<c:out value="★★★☆☆"/>
						</c:when>
						<c:when test="${house.h_avgscore eq '4'}">
							<c:out value="★★★★☆"/>
						</c:when>
						<c:when test="${house.h_avgscore eq '5'}">
							<c:out value="★★★★★"/>
						</c:when>
						<c:otherwise>
							<c:out value="☆☆☆☆☆"/>
						</c:otherwise>
					</c:choose>												
				</td>																				
				<td>${house.h_revcount}</td>							
		</c:forEach>
		
	</table>
	<hr>

		<a style="text-align: right;" href="houWriteForm">글작성</a>
<!-- 페이징 처리 페이징 처리  페이징 처리  페이징 처리   -->

 <div>
	<c:if test="${page.startPage > page.pageBlock }">
		<a href="hou?currentPage=${page.startPage-page.pageBlock}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
		<a
			href="hou?currentPage=${i}">[${i}]</a>
	</c:forEach>
	<c:if test="${page.endPage < page.totalPage }">
		<a
			href="hou?currentPage=${page.startPage+page.pageBlock}">[다음]</a>
	</c:if>
</div> 






</body>
	<c:import url="footer.jsp"/>
</html>