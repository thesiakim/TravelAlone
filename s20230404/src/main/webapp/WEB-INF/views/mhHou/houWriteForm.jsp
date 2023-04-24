<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
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
<h1>숙소글 작성	</h1>
	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="houWriteForm" method="post" name="frm">
		<table>
			<tr>
				<td> 숙소종류</td>
				<td> <input type="text" name="h_common_house" size = "50"> </td>
			</tr>
				<tr>
				<td> 숙소지역</td>
				<td> <input type="text" name="h_common_loc" size = "50"> </td>
			</tr>
		
			<tr>
				<td> 숙소명</td>
				<td> <input type="text" name="h_name" size = "50"> </td>
			</tr>
				<tr>
				<td>소개글</td>
				<td><textarea name="h_content" rows="10"></textarea></td>
			</tr>
			
			<tr>
				<td> 숙소주소 </td>
				<td> <input type="text" name="h_address" size = "50"> </td>
			</tr>
			<tr>
				<td> 객실가격 </td>
				<td> <input type="text" name="h_room" size = "50"> </td>
			</tr>
			<tr>
				<td>체크인 체크아웃시간 </td>
				<td> <input type="text" name="h_checkinout" size = "50"> </td>
			</tr>
			
			<tr>
				<td>문의전화 </td>
				<td> <input type="text" name="h_call" size = "50"> </td>
			</tr>
			
			
			<tr>
				<td>조식여부 </td>
				<td> <input type="text" name="h_eat" size = "50"> </td>
			</tr>
			
			<tr>
				<td>주차장여부 </td>
				<td> <input type="text" name="h_parking" size = "50"> </td>
			</tr>
								
		
			<tr >
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; 
				<a href="hou">목록보기</a></td>
			</tr>
		</table>
	</form>



</body>
</html>