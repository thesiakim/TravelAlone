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

	
	<table  style="margin:auto;">
	<%-- 	<tr>
			<th>글번호</th>
			<td>${inquire.g_writing_id}</td>
		</tr> --%>
		<tr>
			<th>제목</th>
			<td>${inquire.g_title}</td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td>${inquire.m_nickname}</td>
		</tr>
		<tr>
			<th></th>
			<td colspan="3" height="400px">${inquire.g_content}</td>
		</tr>
<%-- 		<tr>
			<th>답변여부</th>
			<td>${inquire.g_reply_yn}</td>
		</tr> --%>
	</table>
	
		<table  style="margin:auto;"">
			<tr>
				<td hidden>번호</td>													
			
				
			</tr>
			<c:forEach items="${imgInqList}" var="inqImg">
				<tr>
					<td hidden>${inqImg.img_id}</td>
				 	<td >
				 	
				 	
 				<c:url value='/display' var='url'>
						<c:param name='file' value='${inqImg.img_stored_file}'/>
				</c:url>
                     <img alt="#" src="${url}"  width="500" height="300">
				 	
				 	
				 	</td>
									
			</c:forEach>
			
	</table>	
	
	
	

	<hr>
	<table  style="margin:auto;">
		<tr>
			<td colspan="2">
	
			<a href="inquire">목록</a>

			
		<c:if test="${user_id == inquire.member_id }">
			<a href="inquireUpdateForm?g_writing_id=${inquire.g_writing_id}">수정</a>
			<a href="deleteInquire?g_writing_id=${inquire.g_writing_id}"  onclick="return confirm('정말로 삭제하시겠습니까?')" >삭제</a>
		</c:if>
			 
			 
			 <c:if test="${user_role == 'rol200' }">
			<a href="inquireReplyForm?g_writing_id=${inquire.g_writing_id}">답변하기</a>
				</c:if>
			</td>
		</tr>
	</table>
	
	<hr>
	
	<table  style="margin:auto;">
		
		<tr>
			<th></th>
			<td>
			
			<c:choose>
			<c:when test="${inquire.g_reply_yn eq '1'.charAt(0)}">
				<c:out value="답변드립니다"/>
			</c:when>
			<c:otherwise>
				<c:out value="답변중입니다"/>
			</c:otherwise>
		</c:choose>
			
			
			
			</td>
		</tr>
		
		<tr>
	<%-- 		<th>
			<c:choose>
			<c:when test="${inquire.g_reply_yn eq '1'.charAt(0)}">
				<c:out value="답변내용"/>
			</c:when>
			<c:otherwise>
				<c:out value="  "/>
			</c:otherwise>
		</c:choose>
			
			</th> --%>
			<td colspan="3" width="500px" height="200px">${inquire.g_reply_content}</td>
		</tr>
	
	</table>


</body>

	<c:import url="../fragments/footer.jsp"/>
</html>