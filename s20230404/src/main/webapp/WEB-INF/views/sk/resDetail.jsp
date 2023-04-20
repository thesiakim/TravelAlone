<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>맛집글 상세		</h1>
	<table>
		<tr><th>맛집명</th>			<td>${res.r_name}</td></tr>
		<tr><th>맛집주소</th>			<td>${res.r_address}</td></tr>
		<tr><th>메뉴 가격</th>			<td>${res.r_menu}</td></tr>
		<tr><th>체크인 체크아웃시간</th>	<td>${res.r_hour}</td></tr>
		<tr><th>문의 전화</th>			<td>${res.r_call}</td></tr>
		<tr><th>주차장여부</th>		<td>${res.r_parking}</td></tr>
		<tr><th>비고</th>				<td>${res.r_content}</td></tr>
		<tr> <th hidden> ${restaurant.restaurant_id} </th>  </tr>
		

		<tr><td colspan="2">
		    <input type="button" value="목록" 
				onclick="location.href='res'">
			<input type="button" value="수정" 
				onclick="location.href='resUpdateForm?restaurant_id=${restaurant.restaurant_id}'">
			<input type="button" value="삭제" 
				onclick="location.href='deleteres?restaurant_id=${restaurant.restaurant_id}'"></td>
		</tr>
	</table>
	
	
</body>
</html>