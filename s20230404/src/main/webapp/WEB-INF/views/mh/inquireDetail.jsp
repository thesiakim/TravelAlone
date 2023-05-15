<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	    
    <%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	
	<table>
		<tr>
			<td style="font-size: 25px;">${inquire.g_title}</td>
			<td>${inquire.m_nickname}</td>
			<td style="padding-left: 200px;">
				<c:if test="${user_id == inquire.member_id }">
					<a href="inquireUpdateForm?g_writing_id=${inquire.g_writing_id}"><input type="submit" value="수정"></a>
					<a href="deleteInquire?g_writing_id=${inquire.g_writing_id}"  onclick="return confirm('정말로 삭제하시겠습니까?')"><input type="submit" value="삭제"></a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" style="width: 500px; height: 400px;">${inquire.g_content}</td>
		</tr>
		<tr>
			<c:forEach items="${imgInqList}" var="inqImg">
			 	<td>
	 				<c:url value='/display' var='url'>
						<c:param name='file' value='${inqImg.img_stored_file}'/>
					</c:url>
                    <img alt="#" src="${url}"  width="500" height="300">
				</td>
			</c:forEach>
		</tr>
		<tr>
			
			
		</tr>
	</table>
	
	<hr>
	
	<table>
		<tr>
			<td style="font-size: 25px; height: 100px;">
			<c:choose>
				<c:when test="${inquire.g_reply_yn eq '1'.charAt(0)}">
					<c:out value="답변드립니다"/>
				</c:when>
				<c:otherwise>
					<c:out value="답변중입니다"/>
					<c:if test="${user_role == 'rol200' }">
						<a href="inquireReplyForm?g_writing_id=${inquire.g_writing_id}" style="padding-left: 400px;"><button type="submit">답변하기</button></a>
					</c:if>
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td colspan="3" style="width: 500px;">${inquire.g_reply_content}</td>
		</tr>
	</table>
	<br><br>
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>