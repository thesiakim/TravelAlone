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
	<h2>공지사항 글작성</h2>
	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="noticeWriteForm" method="post" name="frm">
		<table>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="g_notice_title" size = "50"> </td>
			</tr>
			<tr>
				<td> 게시판분류 </td>
				<td> <input type="text" name="g_common_csboard" size = "50"> </td>
			</tr>
		
		
			
			<tr>
				<td>내용</td>
				<td><textarea name="g_notice_content" rows="10"></textarea></td>
			</tr>
			<tr>
				<td>이미지첨부</td>
				<td>  
					<form id="form1" action="uploadForm" method="post" enctype="multipart/form-data" >
							<input type="file" name="file1"> <p>
							<input type="text" name="title"> <p>
							<input type="submit">
					</form>				
				</td>
			</tr>
			
			  
			
			
			
			<tr >
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; 
				<a href="notice">목록보기</a></td>
			</tr>
		</table>
	</form>
</body>
</html>