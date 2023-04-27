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
	<h1>공지사항 글수정</h1>

	<form action="updateNotice" method="post">
		<input type="hidden" name="g_notice_id" value="${notice.g_notice_id }">
		<table style="margin:auto;">
			<tr>
				<th>글아이디</th>
				<td>${notice.g_notice_id }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="g_notice_title"
					required="required" value="${notice.g_notice_title }"></td>
			</tr>
			<tr>
				<th>회원ID</th>
				<td><input type="text" name="member_id" required="required"
					value="${notice.member_id }"></td>
			</tr>
			<tr>
				<th>게시판종류</th>
				<td><input type="text" name="g_common_csboard"
					required="required" value="${notice.g_common_csboard }"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><input type="text" name="g_notice_content"
					id="g_notice_content" value="${notice.g_notice_content }">
				</td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="확인"></td>
			</tr>
		</table>
	</form>
</body>
<c:import url="footer.jsp"/>
</html>