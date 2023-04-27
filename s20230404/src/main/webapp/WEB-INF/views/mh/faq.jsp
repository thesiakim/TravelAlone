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





	<div>
		
		<h1>고객센터</h1>
			
			<div >
				 <a href="notice">공지사항</a>
				  <a href="faq">자주 묻는 질문</a>
				  <a href="inquire">문의하기</a>
			</div>			
						
		</div>

	
	
			
			<form action="noticeSearch">
			    <select name="search">
			        <option value="s_title">제목</option>
			        <option value="s_content">내용</option>
			    </select> 
			    <input type="text" name="keyword" placeholder="keyword을 입력하세요" value="${search}">
			    <a href="noticeSearch?search=${search}&amp;keyword=${keyword}">keyword검색</a>
			    <p>
			</form>
		
			<c:set var="num" value="${page.total-page.start+1 }"></c:set>
		
		
		<!-- 찐본문 -->
		<table style="margin:auto;">
				<tr>
					<td hidden>번호</td>
					<td>제목</td>
					<td>날짜</td>
				</tr>
				<c:forEach items="${noticeList}" var="notice">
					<tr>
						<td hidden>${notice.g_notice_id}</td>
						<td><a href="noticeDetail?gid=${notice.g_notice_id}">${notice.g_notice_title}</a>
							<%-- 		<a href="noticeDetail/${notice.g_notice_id}">${notice.g_notice_title}</a> --%>
						</td>
						<td>${notice.create_date}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5"><a href="noticeWriteForm">글작성</a></td>
				</tr>
			</table>


	<c:if test="${page.startPage > page.pageBlock }">
		<a href="notice?currentPage=${page.startPage-page.pageBlock}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
		<a
			href="notice?currentPage=${i}">[${i}]</a>
	</c:forEach>
	<c:if test="${page.endPage < page.totalPage }">
		<a
			href="notice?currentPage=${page.startPage+page.pageBlock}">[다음]</a>
	</c:if>

</body>

	<c:import url="footer.jsp"/>
</html>