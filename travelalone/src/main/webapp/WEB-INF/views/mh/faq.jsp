<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/css/list.css" rel="stylesheet" type="text/css">
	<link href="/css/main.css" rel="stylesheet" type="text/css">
	
	
	
	
</head>
	<link href="css/main.css" rel="stylesheet" type="text/css">

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>



<script type="text/javascript"> 
$(document).ready(function() {
	  $(".accordion").click(function(event) {
	    event.preventDefault(); // 기본 이벤트 방지
	    var content = $(this).closest("tr").find(".accordion-content");
	    if (content.is(":hidden")) { // 펼쳐진 상태가 아니면
	      content.slideDown(); // 내용을 펼침
	    } else {
	      content.slideUp(); // 내용을 닫음
	    }
	  });
	});

/* 검색어 입력 필드에서 Enter키 입력 시 검색 수행 */
document.getElementById('searchId').addEventListener('keyup', function(event) {
		 if (event.code === 'Enter')
	{
    	event.preventDefault();
    	document.querySelector('form').submit();
	}
});

/* 카테고리 선택에서 Enter키 입력 시 검색 수행 */
document.getElementById('category').addEventListener('keyup', function(event) {
		 if (event.code === 'Enter')
 	{
     	event.preventDefault();
     	document.querySelector('form').submit();
 	}
	});
</script>

<style>
	th, td { border-bottom: 2px solid rgb(64, 64, 64); }
</style>

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

	
	
			
			<form action="noticeSearch">
			    <select id="category" name="search">
			        <option value="s_title">제목</option>
			        <option value="s_content">내용</option>
			    </select> 
			    <div id="serch">
			
			  	    <input type="text" name="keyword" placeholder="검색어를 입력해주세요 " value="${search}" id="searchId">
			  
			    <%-- <a href="noticeSearch?search=${search}&amp;keyword=${keyword}">keyword검색</a> --%>
			    </div>
			    <p>
			</form>
		
			<c:set var="num" value="${page.total-page.start+1 }"></c:set>
		
		<hr>
		<!-- 찐본문 -->
		
		
	<div id="list" >	
		<table style="margin:auto;">
				<tr>
					<td hidden>번호</td>
					<td style=" padding-left:180px;">제목</td>
					<td style=" padding-left:50px;">작성일</td>
				</tr>
				<c:forEach items="${noticeList}" var="notice">
					<tr>
						<td hidden>${notice.g_notice_id}</td>

						<td style="text-align: left;">
						
						
						<a href="noticeDetail?gid=${notice.g_notice_id}" class="accordion">${notice.g_notice_title}</a>
						  <div class="accordion-content" style="display:none;">
						    <p>${notice.g_notice_content}</p>
						  </div>
						
						</td>
						
						
						
						
						<td style=" padding-left:40px;">${notice.create_date}</td>
					</tr>
				</c:forEach>
		</table>
	</div>
<hr>
					<c:if test="${user_role == 'rol200' }">
					<a style=" padding-left:600px;" href="noticeWriteForm">글작성</a> <br>
					</c:if>
					

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

		<c:import url="../fragments/footer.jsp"/>
</html>