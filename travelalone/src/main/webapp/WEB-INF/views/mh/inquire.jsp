<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ include file="../fragments/header.jsp"%>
   <%--  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> --%>
<%--     <% String role = request.getUserPrincipal().; %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/css/list.css" rel="stylesheet" type="text/css">
	<link href="/css/main.css" rel="stylesheet" type="text/css">

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




	<!-- 본문 -->
 	<form >
 	<div>
		<h1>고객센터</h1>
			
				<div >
				 <a href="notice">공지사항 </a>
				  <a href="faq" style=" padding-left:50px;">자주 묻는 질문 </a>
				  <a href="inquire" style=" padding-left:50px;">문의하기</a>
			</div>				
					<%-- 	<h3>문의글수 : ${totalInquire }</h3>	 --%>
		</div>
	</form>	
	
	
			<!-- 검색 -->
			<form action="inquireSearch">
				<select id="category" name="search">
					<option value="s_title">제목</option>
					<option value="s_content">내용</option>
					
				</select> 
				  <div id="serch">
				<input type="text" name="keyword" placeholder="검색어를 입력해주세요" value="${search}" id="searchId">
				<!-- <button type="submit">keyword검색</button> -->
				</div>
				<p>
			</form>
		
			<c:set var="num" value="${page.total-page.start+1 }"></c:set>
		
		<!-- 문의 분류하기   20230419 -->
			<div id="lis">  
				<table style="margin:auto;">
					<tr>
						<td>
								
							<!-- 문의글 종류 -->
						<c:forEach items="${boardList}" var="list">
							<td style=" padding-left:50px;">
									<!-- 컨트롤러로 보내는거 -->
								<a href="inquireCodeFilter?code=${list.code}">${list.value} </a>
									
							</td>
						</c:forEach>
					</tr>
				</table>							
			
					   <c:set var="num" value="${page.total-page.start+1 }"></c:set>
			</div>				
		
		
		
		
		
		<!-- 찐본문 -->
	<div style = "text-align:center;" id="list" >
			<table style="margin:auto; padding-top:10px; cellpadding:10px">
				<tr>
					<td hidden >번호</td>
					<td  style=" padding-left:120px;">제목</td>
					<td style=" padding-left:50px;">작성자</td>					
					<td style=" padding-left:50px;">답변여부</td>					
					<td style=" padding-left:50px;">작성일</td>
				</tr>
				<c:forEach items="${inquireList}" var="inquire">
				
				
				<tr>
					<td hidden >${inquire.g_writing_id}</td>
					
					
					<td style="text-align: left; , padding-left:100px;"> 
				<!-- 	<sec:authorize access="hasRole('ROLE_rol200')">
					<div> 롤 200인거 확인됨 </div>							
					</sec:authorize> -->
					<!-- 눌렀을때 비밀번호창나오게하기 -->
					
					<a href="#" onclick="detail('${inquire.g_writing_id}', '${inquire.g_passwd}')">${inquire.g_title}</a>
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
					<hr>
					
					<a  style=" padding-left:600px;"  href="inquireWriteForm">글작성</a>
					 
					<br>
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
		<c:import url="../fragments/footer.jsp"/>
</html>