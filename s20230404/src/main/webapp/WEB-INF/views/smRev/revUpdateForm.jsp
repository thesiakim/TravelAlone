<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>여행지 글업데이트	</h1>
		<form action="updateTravel" method="post">
		<input type="hidden" name="travel_id" value="${travel.travel_id }">
		<table>
											
			<tr>
				<th>여행지 종류</th>
				<td><input type="text" name="t_common_travel"
					required="required" value="${travel.t_common_travel }"></td>
			</tr>	
			<tr>
				<th>여행지 지역</th>
				<td><input type="text" name="t_common_loc"
					required="required" value="${travel.t_common_loc }"></td>
			</tr>	
								
			<tr>
				<th>여행지명</th>
				<td><input type="text" name="t_name"
					required="required" value="${travel.t_name }"></td>
			</tr>
			<tr>
				<th>여행지 정보</th>
				<td><input type="text" name="t_content"
					required="required" value="${travel.t_content }"></td>
			</tr>
			<tr>
				<th>여행지 주소</th>
				<td><input type="text" name="t_address"
					required="required" value="${travel.t_address }"></td>
			</tr>
			<tr>
				<th>입장료</th>
				<td><input type="text" name="t_fee"
					required="required" value="${travel.t_fee }"></td>
			</tr>
			<tr>
				<th>운영 시간</th>
				<td><input type="text" name="t_hour"
					required="required" value="${travel.t_hour }"></td>
			</tr>
			<tr>
				<th>문의 전화</th>
				<td><input type="text" name="t_call"
					required="required" value="${travel.t_call }"></td>
			</tr>

			<tr>
				<th>주차장 여부</th>
				<td><input type="text" name="t_parking"
					required="required" value="${travel.t_parking }"></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="확인"></td>
			</tr>
		</table>
	</form>
	
	
	
</body>
</html>