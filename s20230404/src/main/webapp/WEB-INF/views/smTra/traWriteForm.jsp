<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>여행지 글 작성	</h1>
	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="traWriteForm" method="post" name="frm">
		<table>
			<tr>
				<td> 여행지 종류</td>
				<td> <input type="text" name="t_common_travel" size = "50"> </td>
			</tr>
				<tr>
				<td> 여행지 지역</td>
				<td> <input type="text" name="t_common_loc" size = "50"> </td>
			</tr>
		
			<tr>
				<td> 여행지명</td>
				<td> <input type="text" name="t_name" size = "50"> </td>
			</tr>
				<tr>
				<td>여행지 정보</td>
				<td><textarea name="t_content" rows="10"></textarea></td>
			</tr>
			
			<tr>
				<td>여행지 주소 </td>
				<td> <input type="text" name="t_address" size = "50"> </td>
			</tr>
			<tr>
				<td>입장료</td>
				<td> <input type="text" name="t_fee" size = "50"> </td>
			</tr>
			<tr>
				<td>운영 시간</td>
				<td> <input type="text" name="t_hour" size = "50"> </td>
			</tr>
			
			<tr>
				<td>문의 전화</td>
				<td> <input type="text" name="t_call" size = "50"> </td>
			</tr>

			<tr>
				<td>주차장 여부</td>
				<td> <input type="text" name="t_parking" size = "50"> </td>
			</tr>
								
		
			<tr >
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; 
				<a href="tra">목록보기</a></td>
			</tr>
		</table>
	</form>



</body>
</html>