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
	<h1>맛집글 상세		</h1>
	
<div style="display:flex; justify-content:center;">	
	<!-- 사진 올리기 -->
<table style="margin:left;">
		<tr>
			<td hidden>번호</td>													
			<td>사진</td>
			
		</tr>
		<c:forEach items="${imgResList}" var="resImg">
			<tr>
				<td hidden>${resImg.img_id}</td>
			 	<td >
		<img  alt="UpLoad Image" src="${pageContext.request.contextPath}/restaurantUpload/${resImg.img_stored_file}" width="500" height="300"> 
			 	
			 	</td>
								
		</c:forEach>
		
</table>	
	

	<table style="margin:auto;">
		<tr><th>맛집명</th>			<td>${restaurant.r_name}</td></tr>
		<tr><th>맛집주소</th>			<td>${restaurant.r_address}</td></tr>
		<tr><th>메뉴</th>				<td>${restaurant.r_menu}</td></tr>
		<tr><th>운영시간</th>			<td>${restaurant.r_hour}</td></tr>
		<tr><th>문의 전화</th>			<td>${restaurant.r_call}</td></tr>
		<tr><th>주차장여부</th>		<td>${restaurant.r_parking}</td></tr>
		<tr><th>비고</th>				<td>${restaurant.r_content}</td></tr>
		<tr> <th hidden>			    ${restaurant.restaurant_id} </th></tr>
	

		<tr><td colspan="2">
<!-- 		    <input type="button" value="목록" 
				onclick="location.href='res'"> -->
			
			<a href="res" class="button">목록</a>	
			<a href="resUpdateForm?restaurant_id=${restaurant.restaurant_id}" class="button">수정</a>
			<a href="deleteRestaurant?restaurant_id=${restaurant.restaurant_id}" class="button">삭제</a>
			
			<%-- <input type="button" value="수정" 
				onclick="location.href='resUpdateForm?restaurant_id=${restaurant.restaurant_id}'">
			<input type="button" value="삭제" 
				onclick="location.href='deleteRes?restaurant_id=${restaurant.restaurant_id}'"></td> --%>
		</tr>
	</table>
</div>
		<h3>리뷰		</h3>
	<table style="margin:auto;">
				<tr>
					<td>리뷰번호</td>													
					<td>아이디</td>
					<td>내용</td>
					<td>평점</td>
					<td>작성일</td>
					
					

				</tr>
					<c:forEach items="${resRevList}" var="resRev">
					<tr>
						<td>${resRev.review_id}</td>
					 	<td>${resRev.member_id}</td>
					 	<td>${resRev.r_content}</td>
					 	<td>${resRev.r_score}</td>
					 	<td>${resRev.create_date}</td>
					 <td><a href="resRevUpdateForm?restaurant_id=${resRev.restaurant_id}&review_id=${resRev.review_id}">수정</a></td>
					 	<td>
					 	

					 	 <a href="deleteResRev?review_id=${resRev.review_id}" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</a>
					 	 </td>
						
											
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5"><a href="resRevWriteForm?restaurant_id=${restaurant.restaurant_id}">리뷰작성</a></td>
				</tr>
			</table>
	
	
	
	
</body>
	<c:import url="footer.jsp"/>
</html>