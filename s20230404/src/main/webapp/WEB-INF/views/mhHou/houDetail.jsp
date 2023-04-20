<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>숙소글 상세		</h1>
	<table>
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
		    <input type="button" value="목록" 
				onclick="location.href='hou'">
			<input type="button" value="수정" 
				onclick="location.href='houUpdateForm?house_id=${house.house_id}'">
			<input type="button" value="삭제" 
				onclick="location.href='deleteHouse?house_id=${house.house_id}'"></td>
		</tr>
	</table>
	
	
</body>
</html>