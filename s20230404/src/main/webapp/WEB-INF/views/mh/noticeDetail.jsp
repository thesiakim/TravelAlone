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

<h1> 글조회 </h1>

		<table  style="margin:auto;"">
			<tr>
				<td hidden>번호</td>													
				<td>사진</td>
				
			</tr>
			<c:forEach items="${imgNotList}" var="NotImg">
				<tr>
					<td hidden>${NotImg.img_id}</td>
				  	<td >
			
						<c:url value='/display' var='url'>
							<c:param name='file' value='${NotImg.img_stored_file}'/>
						</c:url>
		                     <img alt="#" src="${url}"  width="500" height="300">

			 	
			 	</td>
									
			</c:forEach>
			
	</table>	

<table  style="margin:auto;">

		<tr><th>제목</th><td>${notice.g_notice_title}</td></tr>
		<tr><th>작성자</th><td>${notice.member_id}</td></tr>
		<tr><th>내용</th><td>${notice.g_notice_content}</td></tr>
		<tr> <th> ${notice.g_notice_id} </th>  </tr>
		<tr>
		
		<td colspan="2">

			<a href="notice">목록</a>
			<a href="noticeUpdateForm?g_notice_id=${notice.g_notice_id}">수정</a>
			<a href="deleteNotice?g_notice_id=${notice.g_notice_id}">삭제</a>	
			</td>
		
		</tr>
	</table>
</body>
	<c:import url="footer.jsp"/>
</html>