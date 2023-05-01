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
	<h1>숙소글 상세		</h1>
	
<div style="display:flex; justify-content:center;">	
	<!-- 사진 올리기 -->
<table style="margin:left;">
		<tr>
			<td hidden>번호</td>													
			<td>사진</td>
			
		</tr>
		<c:forEach items="${imgHouList}" var="houImg">
			<tr>
				<td hidden>${houImg.img_id}</td>
			 	<td >
			
				<c:url value='/display' var='url'>
					<c:param name='file' value='${houImg.img_stored_file}'/>
				</c:url>
                     <img alt="#" src="${url}"  width="500" height="300">

			 	
			 	</td>
								
		</c:forEach>
		
</table>
	 
	 
	 
	 
	<table style="margin:auto;">
				
		<tr><th>숙소명</th>			<td>${house.h_name}</td></tr>
		<tr><th>숙소주소</th>			<td>${house.h_address}</td></tr>
		<tr><th>객실 가격</th>			<td>${house.h_room}</td></tr>
		<tr><th>체크인 체크아웃시간</th>	<td>${house.h_checkinout}</td></tr>
		<tr><th>문의전화</th>			<td>${house.h_call}</td></tr>
		<tr><th>조식여부</th>			<td>${house.h_eat}</td></tr>
		<tr><th>주차장여부</th>		<td>${house.h_parking}</td></tr>
		<tr><th>비고</th>				<td>${house.h_content}</td></tr>
		<tr> <th hidden> ${house.house_id} </th>  </tr>
		

		<tr><td colspan="2">
		<!--     <input type="button" value="목록" 
				onclick="location.href='hou'"> -->
				
				<a href="hou" class="button">목록</a>
				<a href="houUpdateForm?house_id=${house.house_id}" class="button">수정</a>
				<a href="deleteHouse?house_id=${house.house_id}" class="button">삭제</a>
			

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
					<c:forEach items="${houRevList}" var="houRev">
					<tr>
						<td>${houRev.review_id}</td>
					 	<td>${houRev.member_id}</td>
					 	<td>${houRev.r_content}</td>
					 	<td>${houRev.r_score}</td>
					 	<td>${houRev.create_date}</td>
					 <td><a href="houRevUpdateForm?house_id=${houRev.house_id}&review_id=${houRev.review_id}">수정</a></td>
					 	<td>
					 <%-- 	<a href="deleteHouRev?review_id=${review_id}">삭제</a> --%>
					 	

					 	 <a href="deleteHouRev?review_id=${houRev.review_id}" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</a>
					 	 </td>
						
											
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5"><a href="houRevWriteForm?house_id=${house.house_id}">리뷰작성</a></td>
				</tr>
			</table>
	
	
	
	
</body>
	<c:import url="footer.jsp"/>
</html>