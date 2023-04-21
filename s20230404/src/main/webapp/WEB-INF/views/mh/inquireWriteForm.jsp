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
<h2>문의하기 글작성</h2>

	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="inquireWriteForm" method="post" name="frm">
		<table>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="g_title" size = "50"> </td>
			</tr>
			<tr>
				<td> 문의글 종류 </td>
				<td> <input type="text" name="g_common_csboard" size = "50"> </td>
			</tr>
			<tr>
				<td> 작성자 </td>
				<td> <input type="text" name="member_id" size = "50"> </td>
			</tr>
			<tr>
				<td> 비밀번호 </td>
				<td> <input type="text" name="g_passwd" size = "50"> </td>
			</tr>
			
		
			
			<tr>
				<td>내용</td>
				<td><textarea name="g_content" rows="10"></textarea></td>
			</tr>
			<tr >
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; 
				<a href="inquire">목록보기</a></td>
			</tr>
		</table>
	</form>
</body>
</html>