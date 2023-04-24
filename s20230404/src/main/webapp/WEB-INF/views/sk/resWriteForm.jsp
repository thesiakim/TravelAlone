<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>맛집글 작성	</h1>
	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="resWriteForm" method="post" name="frm">
		<table>
			<tr>
				<td> 맛집종류</td>
				<td> <input type="text" name="r_common_restaurant" size = "50"> </td>
			</tr>
				<tr>
				<td> 맛집지역</td>
				<td> <input type="text" name="r_common_loc" size = "50"> </td>
			</tr>
		
			<tr>
				<td> 맛집명</td>
				<td> <input type="text" name="r_name" size = "50"> </td>
			</tr>
				<tr>
				<td>소개글</td>
				<td><textarea name="r_content" rows="10"></textarea></td>
			</tr>
			
			<tr>
				<td> 맛집주소 </td>
				<td> <input type="text" name="r_address" size = "50"> </td>
			</tr>
			<tr>
				<td> 메뉴 </td>
				<td> <input type="text" name="r_menu" size = "50"> </td>
			</tr>
			<tr>
				<td> 운영시간 </td>
				<td> <input type="text" name="r_hour" size = "50"> </td>
			</tr>
			
			<tr>
				<td>문의전화 </td>
				<td> <input type="text" name="r_call" size = "50"> </td>
			</tr>
			
			<tr>
				<td>주차장여부 </td>
				<td> <input type="text" name="r_parking" size = "50"> </td>
			</tr>
								
		
			<tr >
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; 
				<a href="res">목록보기</a></td>
			</tr>
		</table>
	</form>
</body>
</html>