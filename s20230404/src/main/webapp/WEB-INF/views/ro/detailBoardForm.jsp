<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:import url="header.jsp"/>
</head>
	<link href="css/main.css" rel="stylesheet" type="text/css">
	<link href="/css/list.css" rel="stylesheet" type="text/css">
<body>
	
	<c:import url="boardHeader.jsp"/>
	
	<hr>
	
	<c:set value="${listBoardS }" var="contents"/>
	
	<c:forEach items="${contents}" var="content" >
		<c:choose>
			<c:when test="${content.b_re_level == 0}">
				<table>
					<tr>
						<td style="width: 500px; font-size: 25px;">${content.b_title}</td>
						<td style="width: 100px; font-size: 16px;">${content.m_nickname}</td>
						<td style="width:150px; font-size: 16px;">${content.getFormattedCreateDate()}</td>
					</tr>
					<tr>
						<td colspan="3" height="400px">${content.b_content}</td>
					</tr>
					<tr>
						<td colspan="3" style="padding-left: 395px; color:red;">${content.b_like_cnt}</td>
					</tr>
					<tr>
						<td colspan="3" style="padding-left: 380px;">
							<input type="button" value="추천">
							<input type="button" value="신고">
						</td>
					</tr>
				</table>
				
				<hr>
				
			</c:when>
							
			<c:when test="${content.b_re_level > 0}">
				<form id="reply" action="#" class="reply-form">
					<table>
						<tr>
							<td style="width: 100px;">${content.m_nickname}</td>
							<td style="width: 400px;">${content.b_content}</td>
							<td style="width:180px;">${content.getFormattedCreateDate()}</td>
							<td><input type="button" value="답글" onclick="showMembers(this)"></td>
							<td><input type="submit" value="신고"></td>
						</tr>
						<tr class="members" style="display: none;">
							<td colspan="5">
								<input type="text" name="b_content" placeholder="댓글을 입력하세요">
								<input type="submit" value="등 록">
							</td>
						</tr>
					</table>
				</form>
			</c:when>
			
		</c:choose>
	</c:forEach>
	
	<script>
		function showMembers(button) {
		var membersRow = button.parentNode.parentNode.nextElementSibling;
			if (membersRow.classList.contains("members")) {
				if (membersRow.style.display === "none") {
		    		membersRow.style.display = "table-row";
				} else {
					membersRow.style.display = "none";
				}
			}
		}
	</script>
	
	<c:if test="${msg != null }">${msg }</c:if>
	
	<h6>명예훼손, 개인정보 유출, 분쟁 유발, 허위 사실 유포 등의 글은 이용약관에 의해 제제는 물론<br> 
	법률에  의해 처벌받을 수 있습니다.건전한 커뮤니티를 위해 자제 당부 드립니다.</h6>
	<form action="writeBoardRe" method="post">
		<div id="replyForm">
			<input type="hidden" name="board_id" value="${board_id }">
	         <input type="hidden" name="member_id" value="5">
	         <input type="hidden" name="b_common_board" value="${b_common_board }">
	         <input type="text" name="b_content" placeholder="댓글을 입력하세요">
	         <input type="submit" value="등 록">
		</div>
	</form>
	
</body>
	<c:import url="footer.jsp"/>
</html>