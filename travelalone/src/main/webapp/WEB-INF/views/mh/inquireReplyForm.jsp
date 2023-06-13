<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="../fragments/header.jsp"%>
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
<h2>답변하기 글작성</h2>

	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="replyInquire" method="post" name="frm">
	<input type="hidden" name = "g_writing_id" value="${inquire.g_writing_id }">
		<table style="margin:auto;">
			<tr>
				<th>글아이디</th>
				<td>${inquire.g_writing_id }</td>
			</tr>
		
							
			<tr>
				<td>답변 내용</td>
				<td><textarea name="g_reply_content" rows="10" cols="50"></textarea></td>
			</tr>
			<tr >
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; 
				<a href="inquire">목록보기</a></td>
			</tr>
		</table>
	</form>
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>