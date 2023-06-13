<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script defer src="/js/detailBoardLoginChk.js"></script>
<script defer src="/js/userPage.js"></script>
<script type="text/javascript">
	function imgOnclickCheck() {
        var checked = $('#imgOnlyCheckbox').is(':checked');
		if(checked) {
			 $(".img_stored_file_no").css("display", "none");
		} else {
			$(".img_stored_file_no").css("display", "");
		}
	}
</script>
<script type="text/javascript">
	function getOrderList() { location.href= "/listAllBoard"; }
</script>

<style>
	th, td { border-bottom: 2px solid rgb(64, 64, 64); }
</style>

</head>
	<link href="/css/list.css" rel="stylesheet" type="text/css">
	<link href="/css/main.css" rel="stylesheet" type="text/css">
  
<body>
	<c:import url="boardHeader.jsp"/>
	<form action="listAllBoard">
		<!-- 검색창 -->
		<select id="category" name="listCategory">
			<option value="lc_total">전체</option>
			<option value="lc_title">제목</option>
			<option value="lc_content">내용</option>
			<option value="lc_writer">글쓴이</option>
		</select>
		<div id="serch">
			<input type="text" name="keyWord" placeholder="종합 검색" value="${board.keyWord != null ? board.keyWord : ''}">
		</div>
	</form>
		<hr>
		<form action="list" id="list">
        	<h2>전체 게시판</h2>
        	
        <input type="checkbox" id="imgOnlyCheckbox" onclick="imgOnclickCheck()">
		<label for="imgOnlyCheckbox">이미지 첨부글만 보기</label>
        
        <select id="orderList" style="margin-right: 500px;">
	        <option value="new" ${board.orderList == 'new' ? 'selected="selected"' : '' }>최신순</option> 
	        <option value="view" ${board.orderList == 'view' ? 'selected="selected"' : '' }>조회수순</option> 
	        <option value="like" ${board.orderList == 'like' ? 'selected="selected"' : '' }>추천순</option> 
        </select>
         
	   	<script>
            document.getElementById('orderList').onchange = function() {
                location.href="listAllBoard?orderList="+orderList.value+"&listCategory=${board.listCategory}&keyWord=${board.keyWord}";
            }
		</script>
	</form>
   
   	<c:set var="num" value="${page.total - page.start + 1 }"></c:set>
   
   	<div>
      	<table>
      	  	<!--  img_stored_file_yn  파일 유무 Check -->
         	<c:forEach var="board" items="${listAllBoard}">
            	<c:choose>
					<c:when test="${board.img_stored_file_yn == 0}">
						<tr class="img_stored_file_no" >
					</c:when>
					<c:when test="${board.img_stored_file_yn > 0}">
						<tr class="img_stored_file_yes" >
					</c:when>
				</c:choose>
							<td style="width: 300px;">
								<a href="detailBoard?board_id=${board.board_id}&b_common_board=${board.b_common_board}">${board.b_title }</a>
							</td>
							<td style="width: 100px;">
								<a onclick="openUserPage(${board.member_id})">${board.m_nickname }</a>
							</td>
							<td style="width: 200px;">${board.getFormattedCreateDate() }</td>
							<td style="width: 50px;">${board.b_like_cnt }</td>
							<td style="width: 30px;">${board.b_view_cnt }</td>
							
							<c:set var="num" value="${num - 1 }"></c:set>
		            	</tr>
         	</c:forEach>
      	</table>
   	</div>
   
	<c:if test="${page.startPage > page.pageBlock }">
		<a href="listAllBoard?currentPage=${page.startPage - page.pageBlock }&orderList=${board.orderList}&listCategory=${board.listCategory}&keyWord=${board.keyWord}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
		<a href="listAllBoard?currentPage=${i }&orderList=${board.orderList}&listCategory=${board.listCategory}&keyWord=${board.keyWord}">[${i }]</a>
	</c:forEach>
	<c:if test="${page.endPage < page.totalPage }">
		<a href="listAllBoard?currentPage=${page.startPage + page.pageBlock }&orderList=${board.orderList}&listCategory=${board.listCategory}&keyWord=${board.keyWord}">[다음]</a>
	</c:if>
	
	
   
</body>

<br><br><br><br><br><br>
	<c:import url="../fragments/footer.jsp"/>
</html>