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
	
	<c:set value="${listBoardS }" var="contents"/>
	
	<div>
		<c:forEach items="${contents}" var="content" >
			<c:choose>
				<c:when test="${content.b_re_level == 0}">
					<div>
						<table>
							<tr>
								<td>${content.b_title}</td>
								<td>${content.m_nickname}</td>
								<td>${content.getFormattedCreateDate()}</td>
							</tr>
						</table>
					</div>
					<div>${content.b_content}</div>
				</c:when>
				<c:when test="${content.b_re_level > 0}">
					<div>
						<table>
							<tr>
								<td>${content.m_nickname}</td>
								<td>${content.b_content}</td>
								<td>${content.getFormattedCreateDate()}</td>
								<td>답글</td>
								<td>신고</td>
							</tr>
						</table>
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
	</div>
	
	<c:if test="${msg != null }">${msg }</c:if>
	<form action="writeBoardRe" method="post">
		<input type="hidden" name="b_common_board" value="bor200">
		<input type="hidden" name="b_ref" value="34">
		<input type="hidden" name="member_id" value="4">
		<textarea rows="3" cols="50" name="b_content" required="required"></textarea>
		<input type="submit" value="등록">
	</form>
	
</body>
	<c:import url="footer.jsp"/>
</html>