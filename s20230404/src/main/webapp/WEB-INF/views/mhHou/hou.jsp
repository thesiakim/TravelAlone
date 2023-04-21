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
	
	
	
	
	
	<!-- 검색 -->
			<form action="houseSearch">
				<select name="search">
					<option value="s_title">제목</option>
					<option value="s_content">내용</option>
					
				</select> <input type="text" name="keyword" placeholder="keyword을 입력하세요" value="${search}">
				<button type="submit">keyword검색</button>
				<p>
			</form>
		
			<c:set var="num" value="${page.total-page.start+1 }"></c:set>
	
<!-- 지역 종류 -->
						<c:forEach items="${boardLocList}" var="list">
							<td>
								<!-- 컨트롤러로 보내는거 -->
								<a href="houLocCodeFilter?code=${list.code}">${list.value}</a> 
									
							</td>
						</c:forEach>
	
	
	
	
	
	<!-- 찐본문 -->

			<table style="margin:auto;">
				<tr>
					<td hidden>번호</td>													
					<td>지역</td>
					<td>숙소명</td>
					<td>평점</td>
					<td>리뷰수</td>
					<td>조회수</td>

				</tr>
				<c:forEach items="${houseList}" var="house">
					<tr>
						<td hidden>${house.house_id}</td>
						
					<%-- 	<td>${house.h_common_loc}</td> --%>
						<td>
						
			
						${list.value}
				
						</td>
						
						
						
						
						<td> <a href="houDetail?hid=${house.house_id}">${house.h_name}</a></td>
						<td>★임시방편</td>
						<td>777임시방편</td>
						<td>777임시방편</td>						

				</c:forEach>
				<tr>
					<td colspan="5"><a href="houWriteForm">글작성</a></td>
				</tr>
			</table>
	








</body>
	<c:import url="footer.jsp"/>
</html>