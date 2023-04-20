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
		function getOrderList() {
			
			location.href= "/listBoard";
			
		}
	</script>
</head>
	<link href="css/main.css" rel="stylesheet" type="text/css">
	<link href="/css/list.css" rel="stylesheet" type="text/css">
<body>
	
	<c:import url="boardHeader.jsp"/>
	
	<form action="listBoard?b_common_board=${board.b_common_board }">
		<table>
			<tr>
				<td id="search"><select name="searchB">
						<option value="titleB">제목</option>
						<option value="contentB">내용</option>
						<option value="writerB">작성자</option>
					</select></td>
				<td id="search"><input type="text" placeholder="종합 검색"></td>
			</tr>
		</table>
	</form>
	
	<div>
		<table>
			<tr>
				<td>커뮤니티</td>
				<td><input type="checkbox">이미지 첨부글만 보기</td>
				<td><select name="orderl" onchange="getOrderList()">
					    <option>최신 순</option>
					    <option value="view">조회수 순</option>
					    <option value="like">추천 순</option>
					</select></td>
			</tr>
		</table>
	</div>
	
	<c:set var="num" value="${page.totalRow - page.startRow + 1 }"></c:set>
	
	<div>
		<table>
			<c:forEach var="board" items="${listBoard}">
				<tr>
					<td><a href="detailBoard?board_id=${board.board_id}&b_common_board=${board.b_common_board }">${board.b_title }</a></td>
					<td>${board.getFormattedCreateDate() }</td>
					<td>${board.m_nickname }</td>
					<td>${board.b_like_cnt }</td>
					<td>${board.b_view_cnt }</td>
					
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
	
	<form action="writeBoardForm" method="post">
		<input type="hidden" name="b_common_board" value="${board.b_common_board }">
		<button type="submit">글쓰기</button>
	</form>
	
</body>
	<c:import url="footer.jsp"/>
</html>