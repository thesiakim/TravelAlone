<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%-- <%@ include file="header.jsp"%> --%>
<%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="img_benner">
		<img src="img/house-picture.png" alt="배너">
	</div>

<h1>숙소리뷰 작성	</h1>
	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="houRevWriteForm" method="post" name="frm">
	
	<input type="hidden" name="house_id" value="${hou_Rev.house_id }">
		<table style="margin:auto;">
			<!-- <tr>
				<td> 아이디</td>
				<td> <input type="text" name="member_id" size = "50"> </td>
			</tr> -->
				<tr>
				<td> 내용</td>
				<td> <input type="text" name="r_content" size = "50"> </td>
			</tr>
		
			<tr>
				<td> 평점</td>
				<td>
				    <label><input type="radio" name="r_score" value="0" <c:if test="${tra_Rev.r_score == 0}">checked</c:if>> 0</label>
				    <label><input type="radio" name="r_score" value="1" <c:if test="${tra_Rev.r_score == 1}">checked</c:if>> 1</label>
				    <label><input type="radio" name="r_score" value="2" <c:if test="${tra_Rev.r_score == 2}">checked</c:if>> 2</label>
				    <label><input type="radio" name="r_score" value="3" <c:if test="${tra_Rev.r_score == 3}">checked</c:if>> 3</label>
				    <label><input type="radio" name="r_score" value="4" <c:if test="${tra_Rev.r_score == 4}">checked</c:if>> 4</label>
				    <label><input type="radio" name="r_score" value="5" <c:if test="${tra_Rev.r_score == 5}">checked</c:if>> 5</label>
				</td>
			</tr>
			
								
		
			<tr >
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; 
				<a href="javascript:window.history.back();">취소하기</a>
				<%-- <a href="houDetail?hid=${house.house_id}">이전화면</a></td> --%>
			</tr>
		</table>
	</form>

</body>
<c:import url="footer.jsp"/>
</html>