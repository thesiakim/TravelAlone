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

	<h1>문의글 조회</h1>
	




	
	
	<table  style="margin:auto;">
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
	</table>
	
		<table  style="margin:auto;"">
			<tr>
				<td hidden>번호</td>													
				<td>사진</td>
				
			</tr>
			<c:forEach items="${imgInqList}" var="InqImg">
				<tr>
					<td hidden>${InqImg.img_id}</td>
				 	<td >
			<img  alt="UpLoad Image" src="${pageContext.request.contextPath}/inquireUpload/${InqImg.img_stored_file}" width="500" height="300"> 
				 	
				 	</td>
									
			</c:forEach>
			
	</table>	
	
	
	

	<hr>
	<table  style="margin:auto;">
		<tr>
			<td colspan="2">
	
			<a href="inquire">목록</a>
			<a href="inquireUpdateForm?g_writing_id=${inquire.g_writing_id}">수정</a>
			<a href="inquireReplyForm?g_writing_id=${inquire.g_writing_id}">답변</a>
			<a href="deleteInquire?g_writing_id=${inquire.g_writing_id}">삭제</a>
			</td>
		</tr>
		</table>
	
	<hr>
	
	<table  style="margin:auto;">
		
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

<c:import url="footer.jsp"/>
</html>