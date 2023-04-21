<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h1>숙소글 글업데이트	</h1>
		<form action="updateHouse" method="post">
		<input type="hidden" name="house_id" value="${house.house_id }">
		<table>
											
			<tr>
				<th>숙소종류</th>
				<td><input type="text" name="h_common_house"
					required="required" value="${house.h_common_house }"></td>
			</tr>	
			<tr>
				<th>숙소지역</th>
				<td><input type="text" name="h_common_loc"
					required="required" value="${house.h_common_loc }"></td>
			</tr>	
								
			<tr>
				<th>숙소명</th>
				<td><input type="text" name="h_name"
					required="required" value="${house.h_name }"></td>
			</tr>
			<tr>
				<th>소개글</th>
				<td><input type="text" name="h_content"
					required="required" value="${house.h_content }"></td>
			</tr>
			<tr>
				<th>숙소주소</th>
				<td><input type="text" name="h_address"
					required="required" value="${house.h_address }"></td>
			</tr>
			<tr>
				<th>객실가격</th>
				<td><input type="text" name="h_room"
					required="required" value="${house.h_room }"></td>
			</tr>
			<tr>
				<th>체크인 체크아웃시간</th>
				<td><input type="text" name="h_checkinout"
					required="required" value="${house.h_checkinout }"></td>
			</tr>
			<tr>
				<th>문의전화</th>
				<td><input type="text" name="h_call"
					required="required" value="${house.h_call }"></td>
			</tr>
			<tr>
				<th> 조식여부</th>
				<td><input type="text" name="h_eat"
					required="required" value="${house.h_eat }"></td>
			</tr>
			<tr>
				<th>주차장여부</th>
				<td><input type="text" name="h_parking"
					required="required" value="${house.h_parking }"></td>
			</tr>


			<tr>
				<td colspan="2"><input type="submit" value="확인"></td>
			</tr>
		</table>
	</form>
	
	
	
</body>
</html>