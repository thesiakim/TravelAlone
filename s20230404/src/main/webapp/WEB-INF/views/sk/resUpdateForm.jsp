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
	<h1>맛집글 업데이트	</h1>
	<form action="updateRes" method="post">
		<input type="hidden" name="restaurant_id" value="${res.restaurant_id }">
		<table>
		
			<tr>
				<td> 맛집종류</td>
				<td> <input type="text" name="r_common_restaurant"
					required="required" value="${res.r_common_restaurant }"> </td>
			</tr>
			<tr>
				<td> 맛집지역</td>
				<td> <input type="text" name="r_common_loc" 
					required="required" value="${res.r_common_loc }"> </td>
			</tr>
		
			<tr>
				<td> 맛집명</td>
				<td> <input type="text" name="r_name"
					required="required" value="${res.r_name}"> </td>
			</tr>
				<tr>
				<td> 소개글</td>
				<td> <input type="text" name ="r_content"
					required="required" value="${res.r_content}"> </td>
			</tr>
			
			<tr>
				<td> 맛집주소 </td>
				<td> <input type="text" name="r_address"
					required="required" value="${res.r_address}"> </td>
			</tr>
			<tr>
				<td> 메뉴 </td>
				<td> <input type="text" name="r_menu"
					required="required" value="${res.r_menu}"> </td>
			</tr>
			<tr>
				<td> 운영시간 </td>
				<td> <input type="text" name="r_hour"
					required="required" value="${res.r_hour}"></td>
			</tr>
			
			<tr>
				<td>문의전화 </td>
				<td> <input type="text" name="r_call"
					required="required" value="${res.r_call}"></td>
			</tr>
			
			<tr>
				<td>주차장여부 </td>
				<td> <input type="text" name="r_parking"
					required="required" value="${res.r_parking}"></td>
			</tr>
								
		
			<tr >
				<td colspan="2"> <input type="submit" value="확인">
			</tr>
		</table>
	</form>
</body>
</html>