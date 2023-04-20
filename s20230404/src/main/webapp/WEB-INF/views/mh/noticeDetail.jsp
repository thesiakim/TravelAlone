<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>공지사항 글조회 </h1>
<table>
		<tr><th>제목</th><td>${notice.g_notice_title}</td></tr>
		<tr><th>작성자</th><td>${notice.member_id}</td></tr>
		<tr><th>내용</th><td>${notice.g_notice_content}</td></tr>
		<tr> <th> ${notice.g_notice_id} </th>  </tr>
		<tr><td colspan="2">
		    <input type="button" value="목록" 
				onclick="location.href='notice'">
			<input type="button" value="수정" 
				onclick="location.href='noticeUpdateForm?g_notice_id=${notice.g_notice_id}'">
			<input type="button" value="삭제" 
				onclick="location.href='deleteNotice?g_notice_id=${notice.g_notice_id}'"></td>
		</tr>
	</table>
</body>
</html>