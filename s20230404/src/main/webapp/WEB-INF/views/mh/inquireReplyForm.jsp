<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<link href="/css/write.css" rel="stylesheet" type="text/css">
	<link href="/css/list.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="img_benner">
	<img src="img/main-picture.png" alt="배너">
</div>

	<h2>답변하기 글작성</h2>

	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="replyInquire" method="post" name="frm">
		<input type="hidden" name = "g_writing_id" value="${inquire.g_writing_id }">
		<table>
			<tr>
				<td hidden="">${inquire.g_writing_id }</td>
			</tr>
							
			<tr>
				<td><textarea id="inquireReply" name="g_reply_content" rows="10" cols="50"></textarea></td>
			</tr>
		</table>
		<br>
			<button type="submit">입 력</button> &nbsp;&nbsp; 
	</form>
	<br><br>
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>