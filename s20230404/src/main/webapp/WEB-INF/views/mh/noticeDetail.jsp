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
		<tr>
		
			<td>${notice.g_notice_title}</td>
		</tr>

</table>
		<hr>
<table  style="margin:auto;">	
		   <tr style="margin:auto;">
		<c:forEach items="${imgNotList}" var="NotImg" >
			<%-- <td hidden>${NotImg.img_id}</td> --%>
	
				<c:url value='/display' var='url'>
					<c:param name='file' value='${NotImg.img_stored_file}'/>
				</c:url>
                     <img alt="#" src="${url}"  width="500" height="300">
				<br>		
		</c:forEach>
	  		</tr> 
</table>	
	
		
<table  style="margin:auto;">		
		<tr>
			
			<td style="width: 800px;">${notice.g_notice_content}</td>
		</tr>
		<tr>
			<th hidden>${notice.g_notice_id}</th>
		</tr>
	
</table>
<hr>

			<a href="notice">목록</a>
			
	
	
		<c:if test="${user_role == 'rol200' }">
			<a href="noticeUpdateForm?g_notice_id=${notice.g_notice_id}">수정</a> 
			<a	href="deleteNotice?g_notice_id=${notice.g_notice_id} "  onclick="return confirm('정말로 삭제하시겠습니까?')" >삭제</a>
		 </c:if>
		 
		 
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>