<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ include file="../fragments/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/css/write.css" rel="stylesheet" type="text/css">
	<link href="/css/list.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="img_benner">
	<img src="img/main-picture.png" alt="배너">
</div>
	<h1>고객센터</h1>
		<a href="notice">공지사항 </a>
		<a href="faq" style=" padding-left:50px;">자주 묻는 질문 </a>
		<a href="inquire" style=" padding-left:50px;">문의하기</a>
	<hr>
	
	<c:if test="${msg!=null}">${msg}</c:if>
	
	<form action="noticeWriteForm" method="post" name="frm" enctype="multipart/form-data">
		<table>
			<tr>
				<td colspan="2">
					<input type="text" name="g_notice_title" style="width: 600px; height: 25px;" placeholder="제목을 작성해 주세요">
				</td>
			</tr>
			<tr>
				<td style="width: 120px;">게시판 분류</td>
				<td style="width: 320px;"> 
					<select name="g_common_csboard">
						<option value="not100">공지사항</option>
						<option value="not200">자주묻는질문</option>										
					</select>
				</td>
			</tr>
							
			<tr>
				<td colspan="3">
					<textarea name="g_notice_content" style="width: 600px; height: 500px; font-size: 15px;
					text-align:left;" placeholder="내용을 작성해 주세요"></textarea>
				</td>
			</tr>
			
			<tr>
				<td> 
				   <img alt="사진 추가" src="${pageContext.request.contextPath}/noticeUpload/${savedName}">
				</td>
				<td>
					<input type="file" name="file1" multiple="multiple"> <p>						
				</td>						
			</tr>
			
		</table>
			<br>
			<input type="submit" value="입력"> &nbsp;&nbsp; 
	</form>
	<br><br>
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>