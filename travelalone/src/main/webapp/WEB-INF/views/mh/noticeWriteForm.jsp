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

	
	 	<div>
		<h1>고객센터</h1>
			
			<div >
				 <a href="notice">공지사항 </a>
				  <a href="faq" style=" padding-left:50px;">자주 묻는 질문 </a>
				  <a href="inquire" style=" padding-left:50px;">문의하기</a>
			</div>			
						
		</div>
	
	<hr>
	
	
	
	
	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="noticeWriteForm" method="post" name="frm" enctype="multipart/form-data">
		<table style="margin:auto;">
			<tr>
				<td style="width:30px;"> 제목 </td>
				<td> <input type="text" name="g_notice_title" size = "48" placeholder="제목 "> </td>
			</tr>
			<tr>
				<td> 게시판분류 </td>
				<td style="text-align: left;">
					<select name="g_common_csboard">
					<option value="not100">공지사항</option>
					<option value="not200">자주묻는질문</option>										
				</select>
				
				
				</td>
			</tr>
							
			<tr>
				<td>내용</td>
				<td style="text-align: left;"><textarea name="g_notice_content" rows="10" cols="50"></textarea></td>
			</tr>
			<tr>
				<td> 
				   <img alt="사진 추가" src="${pageContext.request.contextPath}/noticeUpload/${savedName}">
						<input type="file" name="file1" multiple="multiple"> <p>						
				</td>						
			</tr>
			
			  
			
			
			
			<tr >
				<td colspan="2"> 
				<input type="submit" value="입력"> &nbsp;&nbsp; 
				<a href="notice">목록보기</a></td>
			</tr>
		</table>
	</form>
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>