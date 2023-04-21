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
	<h1>여행지글 상세		</h1>
	<table>
		<tr><th>여행지명</th>				<td>${travel.t_name}</td></tr>
		<tr><th>여행지주소</th>				<td>${travel.t_address}</td></tr>
		<tr><th>입장료</th>				<td>${travel.t_fee}</td></tr>
		<tr><th>문의전화</th>				<td>${travel.t_call}</td></tr>
		<tr><th>운영시간</th>				<td>${travel.t_hour}</td></tr>
		<tr><th>주차장여부</th>				<td>${travel.t_parking}</td></tr>
		<tr><th>여행지정보</th>				<td>${travel.t_content}</td></tr>
		<tr> <th hidden> ${travel.travel_id} </th>  </tr>

		<tr><td colspan="2">
		    <input type="button" value="목록" 
				onclick="location.href='tra'">
			<input type="button" value="수정" 
				onclick="location.href='traUpdateForm?travel_id=${travel.travel_id}'">
			<input type="button" value="삭제" 
				onclick="location.href='deleteTravel?travel_id=${travel.travel_id}'"></td>
		</tr>
	</table>
	
	
</body>
	<c:import url="footer.jsp"/>
</html>