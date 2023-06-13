<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script defer src="/js/detailBoardLoginChk.js"></script>
<script defer src="/js/userPage.js"></script>
<style>
	th, td { border-bottom: 2px solid rgb(64, 64, 64); }
</style>

</head>
	<link href="/css/main.css" rel="stylesheet" type="text/css">
	<link href="/css/list.css" rel="stylesheet" type="text/css">
<body>
	<c:import url="boardHeader.jsp"/>
	<form action="listBoard">
		<!-- 검색창 -->
		<select id="category" name="listCategory">
			<option value="lc_total">전체</option>
			<option value="lc_title">제목</option>
			<option value="lc_content">내용</option>
			<option value="lc_writer">글쓴이</option>
		</select>
		<div id="serch">
			<input type="hidden" name="b_common_board" value="${board.b_common_board }">
			<input type="text" name="keyWord" placeholder="종합 검색" value="${board.keyWord != null ? board.keyWord : ''}">
		</div>
	</form>
		<hr>
		<form action="list" id="list">
			<c:choose>
				<c:when test="${board.b_common_board == 'bor100'}">
           			<h2>자유 게시판</h2>
	        	</c:when>
				<c:when test="${board.b_common_board == 'bor200'}">
					<h2>정보 게시판</h2>
				</c:when>
				<c:when test="${board.b_common_board == 'bor300'}">
					<h2>질문 게시판</h2>
				</c:when>
				<c:when test="${board.b_common_board == 'bor400'}">
					<h2>홍보 게시판</h2>
				</c:when>
				<c:when test="${board.b_common_board == 'bor500'}">
					<h2>모집 게시판</h2>
				</c:when>
			</c:choose>
			
	    	<input type="checkbox" id="imgOnlyCheckbox" onclick="imgOnclickCheck()">
			<label for="imgOnlyCheckbox">이미지 첨부글만 보기</label>
         	<select id="orderList" style="margin-right: 500px;">
	            <option value="new" ${board.orderList == 'new' ? 'selected="selected"' : '' }>최신순</option> 
	            <option value="view" ${board.orderList == 'view' ? 'selected="selected"' : '' }>조회수순</option> 
	            <option value="like" ${board.orderList == 'like' ? 'selected="selected"' : '' }>추천순</option> 
         	</select>
			<script>
               document.getElementById('orderList').onchange = function() {
                  location.href="listBoard?orderList="+orderList.value+"&b_common_board=${board.b_common_board}&listCategory=${board.listCategory}&keyWord=${board.keyWord}";
               }
            </script>    
		</form>
		
		
		<form action="writeBoardForm" method="post" onsubmit="return chkId();">
			<input type="hidden" name="b_common_board" value="${board.b_common_board }">
			<button type="submit" style="margin-left: 664px; margin-bottom: 10px">글 쓰기</button>
		</form>
   
	<c:set var="num" value="${page.total - page.start + 1 }"></c:set>
   
	<div>
      	<table>
      	  	<!--  img_stored_file_yn  파일 유무 Check -->
         	<c:forEach var="board" items="${listBoard}">
         		<c:choose>
					<c:when test="${board.img_stored_file_yn == 0}">
						<tr class="img_stored_file_no" >
					</c:when>
					<c:when test="${board.img_stored_file_yn > 0}">
						<tr class="img_stored_file_yes" >
					</c:when>
				</c:choose>
							<td style="width: 300px;">
								<a href="detailBoard?board_id=${board.board_id}&b_common_board=${board.b_common_board }">${board.b_title }</a>
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
		<a href="listBoard?currentPage=${page.startPage - page.pageBlock }&b_common_board=${board.b_common_board }&orderList=${board.orderList}&listCategory=${board.listCategory}&keyWord=${board.keyWord}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
		<a href="listBoard?currentPage=${i }&b_common_board=${board.b_common_board }&orderList=${board.orderList}&listCategory=${board.listCategory}&keyWord=${board.keyWord}">[${i }]</a>
	</c:forEach>
	<c:if test="${page.endPage < page.totalPage }">
		<a href="listBoard?currentPage=${page.startPage + page.pageBlock }&b_common_board=${board.b_common_board }&orderList=${board.orderList}&listCategory=${board.listCategory}&keyWord=${board.keyWord}">[다음]</a>
	</c:if>
</body>

<br><br><br><br><br><br>
	<c:import url="../fragments/footer.jsp"></c:import>
</html>