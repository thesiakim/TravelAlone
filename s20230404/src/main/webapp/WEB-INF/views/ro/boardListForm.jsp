<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<c:import url="header.jsp"/>
	
<script type="text/javascript">
	function getOrderList() { location.href= "/listBoard"; }
</script>

<style>
	th, td { border-bottom: 2px solid rgb(64, 64, 64); }
</style>

</head>
	<link href="css/main.css" rel="stylesheet" type="text/css">
	<link href="/css/list.css" rel="stylesheet" type="text/css">
<body>
	
	<c:import url="boardHeader.jsp"/>
	
	<form action="listBoard?b_common_board=${board.b_common_board }">
		<!-- 검색창 -->
	 	<div id="serch">
			<input type="text" placeholder="종합 검색">
	  	</div>
		
		<hr>
	  
	</form>
	
	<form action="list" id="list">
		커뮤니티 - 자유 게시판
		<input type="checkbox">이미지 첨부글만 보기
		<select>
			<option value="s_job">최신순</option> 
			<option value="s_ename">조회수순</option> 
			<option value="s_ename">추천순</option> 
		</select>
	</form>
	<form action="writeBoardForm" method="post">
		<input type="hidden" name="b_common_board" value="${board.b_common_board }">
		<button type="submit">글쓰기</button>
	</form>
	<c:set var="num" value="${page.totalRow - page.startRow + 1 }"></c:set>
	
	<div>
		<table>
			<c:forEach var="board" items="${listBoard}">
				<tr>
					<td style="width: 300px;">
					<a href="detailBoard?board_id=${board.board_id}&b_common_board=${board.b_common_board }">${board.b_title }</a>
					</td>
					<td style="width: 100px;">${board.m_nickname }</td>
					<td style="width: 200px;">${board.getFormattedCreateDate() }</td>
					<td style="width: 50px;">${board.b_like_cnt }</td>
					<td style="width: 30px;">${board.b_view_cnt }</td>
					
					<c:set var="num" value="${num - 1 }"></c:set>
				</tr>
			</c:forEach>
		</table>
	</div>
	<c:if test="${page.startPage > page.pageBlock }">
		<a href="listBoard?currentPage=${page.startPage - page.pageBlock }&b_common_board=${board.b_common_board }">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
		<a href="listBoard?currentPage=${i }&b_common_board=${board.b_common_board }">[${i }]</a>
	</c:forEach>
	<c:if test="${page.endPage < page.totalPage }">
		<a href="listBoard?currentPage=${page.startPage + page.pageBlock }&b_common_board=${board.b_common_board }">[다음]</a>
	</c:if>
	
</body>
	<c:import url="footer.jsp"/>
</html>