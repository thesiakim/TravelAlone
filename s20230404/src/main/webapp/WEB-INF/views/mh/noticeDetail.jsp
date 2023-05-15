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
			<td style="font-size: 25px;">${notice.g_notice_title}</td>
			<td>
				<c:if test="${user_role == 'rol200' }">
					<a href="noticeUpdateForm?g_notice_id=${notice.g_notice_id}"><input type="submit" value="수정"></button></a> 
					<a href="deleteNotice?g_notice_id=${notice.g_notice_id}" onclick="return confirm('정말로 삭제하시겠습니까?')"><input type="submit" value="삭제"></a>
				</c:if>
			</td>
		</tr>
		<tr>
			<c:forEach items="${imgNotList}" var="NotImg" >
				<c:url value='/display' var='url'>
					<c:param name='file' value='${NotImg.img_stored_file}'/>
				</c:url>
					<img alt="#" src="${url}"  width="500" height="300">
				<br>		
			</c:forEach>
		</tr> 
		<tr>
			<td style="width: 500px; height: 400px;">${notice.g_notice_content}</td>
		</tr>
	</table>
	
	<hr>
	<br><br>
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>