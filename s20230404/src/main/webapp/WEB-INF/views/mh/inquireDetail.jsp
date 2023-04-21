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

	<h1>문의글 조회</h1>
	<table>
		<tr>
			<th>글번호</th>
			<td>${inquire.g_writing_id}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${inquire.g_title}</td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td>${inquire.member_id}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${inquire.g_content}</td>
		</tr>
		<tr>
			<th>답변여부</th>
			<td>${inquire.g_reply_yn}</td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="button" 	value="목록"				onclick="location.href='inquire'"> 
			<input type="button"	value="수정"				onclick="location.href='inquireUpdateForm?g_writing_id=${inquire.g_writing_id}'">
			<input type="button"	value="답변"				onclick="location.href='inquireReplyForm?g_writing_id=${inquire.g_writing_id}'">
			<input type="button" 	value="삭제"				onclick="location.href='deleteInquire?g_writing_id=${inquire.g_writing_id}'"></td>
		</tr>
	</table>
	<table>
		
		<tr>
			<th>제목</th>
			<td>답변드립니다</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>관리자</td>
		</tr>
		<tr>
			<th>답변내용</th>
			<td>${inquire.g_reply_content}</td>
		</tr>
	
	</table>


</body>
</html>