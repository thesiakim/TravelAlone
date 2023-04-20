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


  <!-- 헤더 -->
  <div class="main">
		<div id="header">
		  <div class="container">
			<div id="container-left clearfix">
			  <div class="logo">
				<a href="mainPage"><img src="img/로고.png"></a>
			  </div>
			  <div class="headerLogin">
				<a href="loginPage.html">로그인</a>
			  </div> 
			  <div class="headerLogin">
				<a href="">회원가입</a>
			  </div> 
			</div>
		  </div>
		  <div class="headerForm">
			<div class="headerBenner">
			  <ul>
				<li><a href="#">여행지</a>
				  <ul>
					<li><a href="#">관 광</a></li>
	                <li><a href="#">자 연</a></li>
	                <li><a href="#">레 저</a></li>
	                <li><a href="#">쇼 핑</a></li>
				  </ul>
	            </li>
	            <li><a href="#">숙소</a>
	      		  <ul>
	              	<li><a href="#">호 텔</a></li>
	              	<li><a href="#">모 텔</a></li>
	              	<li><a href="#">팬 션</a></li>
	              	<li><a href="#">캠 핑</a></li>
	              	<li><a href="#">게스트 하우스</a></li>
				  </ul>
				</li>
				<li><a href="#">맛집</a>
				  <ul>
				   <li><a href="#">한 식</a></li>
				   <li><a href="#">중 식</a></li>
				   <li><a href="#">일 식</a></li>
				   <li><a href="#">양 식</a></li>
				   <li><a href="#">카 페</a></li>
				   <li><a href="#">기 타</a></li>
				  </ul>
				</li>
	            <li><a href="#">커뮤니티</a>
				  <ul>
				   <li><a href="#">자 유</a></li>
				   <li><a href="#">정 보</a></li>
				   <li><a href="#">질 문</a></li>
				   <li><a href="#">홍 보</a></li>
				   <li><a href="#">모 집</a></li>
				  </ul>
				</li>
			  </ul>
			</div>
		  </div>
		</div>
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
					
				</select> <input type="text" name="keyword" placeholder="keyword을 입력하세요" value="${search}">
				<button type="submit">keyword검색</button>
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
</html>