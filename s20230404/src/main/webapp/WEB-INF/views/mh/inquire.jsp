<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header.jsp"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--     <% String role = request.getUserPrincipal().; %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script   type="text/javascript">
function detail(gid,passwd) {

	 var goodURL = "inquireDetail?gid=" + gid;  //이곳에 인증이 되었을때 이동할 페이지  입력


alert("비밀번호를 입력하셔야 합니다.");

var password =  prompt("PASSWD 입력","");

    if (password == null)  {
        alert("비밀번호를 입력하세요");
        location  = "/inquire"      
       /*  history.back(); */
    }
    else  {
        var  combo =  password
        var  total =  combo.toLowerCase()

	    if  (total == passwd)  {                // 비밀번호
	        alert("인증완료");
	        location  =  goodURL;
	    }
	    else  {
	        alert("출입금지");
	        location  = "/inquire"   
	       /*  history.back(); */
	    }
	}
	
}	

</script>
<body>
<div id="img_benner">
		<img src="img/main-picture.png" alt="배너">
	</div>




	<!-- 본문 -->
 	<form >
 	<div>
		<h1>고객센터</h1>
			
			<div >
				 <a href="notice">공지사항</a>
				  <a href="faq">자주 묻는 질문</a>
				  <a href="inquire">문의하기</a>
			</div>			
						<h3>문의글수 : ${totalInquire }</h3>	
		</div>
	</form>	
	
	
			<!-- 검색 -->
			<form action="inquireSearch">
				<select name="search">
					<option value="s_title">제목</option>
					<option value="s_content">내용</option>
					
				</select> <input type="text" name="keyword" placeholder="keyword을 입력하세요" value="${search}">
				<button type="submit">keyword검색</button>
				<p>
			</form>
		
			<c:set var="num" value="${page.total-page.start+1 }"></c:set>
		
		<!-- 문의 분류하기   20230419 -->
			<div >  
				<table style="margin:auto;">
					<tr>
						<td>
								
							<!-- 문의글 종류 -->
						<c:forEach items="${boardList}" var="list">
							<td>
									<!-- 컨트롤러로 보내는거 -->
								<a href="inquireCodeFilter?code=${list.code}">${list.value}</a>
									
							</td>
						</c:forEach>
					</tr>
				</table>							
			
					   <c:set var="num" value="${page.total-page.start+1 }"></c:set>
			</div>				
		
		
		
		
		
		<!-- 찐본문 -->
	<div style = "text-align:center;">
			<table style="margin:auto;">
				<tr>
					<td hidden>번호</td>
					<td>제목</td>
					<td>작성자</td>					
					<td>답변여부</td>					
					<td>작성일</td>
				</tr>
				<c:forEach items="${inquireList}" var="inquire">
					<tr>
						<td hidden>${inquire.g_writing_id}</td>
						<td style="text-align: left;"> 
						<sec:authorize access="hasRole('ROLE_rol200')">
						<div> 롤 200인거 확인됨 </div>							
						</sec:authorize>
						<!-- 눌렀을때 비밀번호창나오게하기 -->
						<a href="#" onclick="detail('${inquire.g_writing_id}', '${inquire.g_passwd}')">${inquire.g_title}</a>
						</td>
						<td>${inquire.m_nickname}</td>
						<td> 
							
							<c:choose>
								<c:when test="${inquire.g_reply_yn eq '1'.charAt(0)}">
									<c:out value="답변완료"/>
								</c:when>
								<c:otherwise>
									<c:out value="답변중"/>
								</c:otherwise>
							</c:choose>
						
					     </td>     
						<td>${inquire.create_date}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5"><a href="inquireWriteForm">글작성</a></td>
				</tr>
			</table>

	</div>
	<c:if test="${page.startPage > page.pageBlock }">
		<a href="inquire?currentPage=${page.startPage-page.pageBlock}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
		<a
			href="inquire?currentPage=${i}">[${i}]</a>
	</c:forEach>
	<c:if test="${page.endPage < page.totalPage }">
		<a
			href="inquire?currentPage=${page.startPage+page.pageBlock}">[다음]</a>
	</c:if>

</body>
	<c:import url="footer.jsp"/>
</html>