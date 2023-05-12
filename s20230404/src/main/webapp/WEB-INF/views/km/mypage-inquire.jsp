<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="<%=contextPath%>/css/list.css" rel="stylesheet" type="text/css">
	<link href="<%=contextPath%>/css/main.css" rel="stylesheet" type="text/css">
</head>

<style>
	th, td { border-bottom: 2px solid rgb(64, 64, 64); }
</style>

<body>
	
	<!-- 본문 -->
 	<form >
 	<div>
		<h1>고객센터</h1>		
	</div>
	</form>	
	
			<c:set var="num" value="${page.total-page.start+1 }"></c:set>
	
	
	<div style = "text-align:center;" id="list" >
			<table style="margin:auto; padding-top:10px; cellpadding:10px">
				<tr>
					<td hidden >번호</td>
					<td  style=" padding-left:120px;">제목</td>
					<td style=" padding-left:50px;">작성자</td>					
					<td style=" padding-left:50px;">답변여부</td>					
					<td style=" padding-left:50px;">작성일</td>
				</tr>
				<c:forEach items="${myPageInquireList}" var="inquire">
				
				
				<tr>
					<td hidden >${inquire.g_writing_id}</td>
					
					
					<td style="text-align: left; , padding-left:100px;"> 
				<!-- 	<sec:authorize access="hasRole('ROLE_rol200')">
					<div> 롤 200인거 확인됨 </div>							
					</sec:authorize> -->
					<!-- 눌렀을때 비밀번호창나오게하기 -->
					
					<a href="<%=contextPath%>/inquireDetail?gid=${inquire.g_writing_id }" onclick="detail('${inquire.g_writing_id}', '${inquire.g_passwd}')">${inquire.g_title}</a>
					</td>
					<td style=" padding-left:50px;">${inquire.m_nickname}</td>
					<td style=" padding-left:50px;"> 
						
						<c:choose>
							<c:when test="${inquire.g_reply_yn eq '1'.charAt(0)}">
								<c:out value="답변완료"/>
							</c:when>
							<c:otherwise>
								<c:out value="답변중"/>
							</c:otherwise>
						</c:choose>
					
				     </td>     
					<td style=" padding-left:50px;">${inquire.create_date}</td>
				</tr>
				</c:forEach>
				<tr>
				</tr>
			</table>

	</div>
	
	<c:if test="${page.startPage > page.pageBlock }">
		<a href="<%=contextPath%>/inquire?currentPage=${page.startPage-page.pageBlock}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
		<a
			href="<%=contextPath%>/inquire?currentPage=${i}">[${i}]</a>
	</c:forEach>
	<c:if test="${page.endPage < page.totalPage }">
		<a
			href="<%=contextPath%>/inquire?currentPage=${page.startPage+page.pageBlock}">[다음]</a>
	</c:if>
	
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>