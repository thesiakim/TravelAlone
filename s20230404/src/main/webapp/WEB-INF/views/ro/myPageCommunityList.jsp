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
	function getOrderList() { location.href= "/listBoard"; }
</script>

<style>
	th, td { border-bottom: 2px solid rgb(64, 64, 64); }
</style>

</head>
	<link href="/css/main.css" rel="stylesheet" type="text/css">
	<link href="/css/list.css" rel="stylesheet" type="text/css">
<body>
	
	<div id="img_benner">
		<img src="img/community-picture.png" alt="배너">
	</div>
	
	<h1><a href="mypage"><img src="img/myPage-picture.png" alt="마이페이지" width=250px height=250px></a></h1>
   
	<h1>작성한 글</h1>
	<hr><br>
	
	<h3>작성한 커뮤니티 글 내역</h3>
	<br>
  
	<c:set var="num" value="${page.total - page.start + 1 }"></c:set>
   
	<div>
      	<table>
         	<c:forEach var="board" items="${myPageCommunityList}">
						<tr>
							<td style="width: 300px;">
								<a href="detailBoard?board_id=${board.board_id}&b_common_board=${board.b_common_board }">${board.b_title }</a>
							</td>
							<c:choose>
								<c:when test="${board.b_common_board == 'bor100'}">
									<td style="width: 100px;">자유 게시판</td>
								</c:when>
								<c:when test="${board.b_common_board == 'bor200'}">
									<td style="width: 100px;">정보 게시판</td>
								</c:when>
								<c:when test="${board.b_common_board == 'bor300'}">
									<td style="width: 100px;">질문 게시판</td>
								</c:when>
								<c:when test="${board.b_common_board == 'bor400'}">
									<td style="width: 100px;">홍보 게시판</td>
								</c:when>
								<c:when test="${board.b_common_board == 'bor500'}">
									<td style="width: 100px;">모집 게시판</td>
								</c:when>
							</c:choose>
							<td style="width: 200px;">${board.getFormattedCreateDate() }</td>
							<td style="width: 50px;">${board.b_like_cnt }</td>
							<td style="width: 30px;">${board.b_view_cnt }</td>
							
							<c:set var="num" value="${num - 1 }"></c:set>
						</tr>
         	</c:forEach>
      	</table>
   	</div>
   	<c:if test="${page.startPage > page.pageBlock }">
      	<a href="myPageCommunityList?currentPage=${page.startPage - page.pageBlock }">[이전]</a>
   	</c:if>
   	<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
      	<a href="myPageCommunityList?currentPage=${i }">[${i }]</a>
   	</c:forEach>
   	<c:if test="${page.endPage < page.totalPage }">
      	<a href="myPageCommunityList?currentPage=${page.startPage + page.pageBlock }">[다음]</a>
   	</c:if>
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>