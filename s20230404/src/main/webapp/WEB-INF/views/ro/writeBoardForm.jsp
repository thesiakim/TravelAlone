<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:import url="header.jsp"/>
</head>
	<link href="css/main.css" rel="stylesheet" type="text/css">
	<link href="/css/list.css" rel="stylesheet" type="text/css">
<body>
	
	<h2>글 작성하기</h2>
		<c:if test="${msg != null }">${msg }</c:if>
		<form action="writeBoard" method="post">
			<input type="hidden" name="b_common_board" value="${board.b_common_board }">
			<input type="hidden" name="member_id" value="3">
			<table>
		        <tr><th>제목</th>
		        	<td><input type="text" name="b_title" required="required"></td>
		        </tr>
		        <tr><th>내용</th>
		        	<td><textarea id="content" name="b_content" rows="10" cols="30" required="required"></textarea></td>
		        </tr>
		        <tr><td colspan="2"><input type="submit" value="확인"></td></tr>
		    </table>
		</form>
	
</body>
	<c:import url="footer.jsp"/>
</html>