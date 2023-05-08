<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href="/css/list.css" rel="stylesheet" type="text/css">	
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">
	<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
	<script defer src="/js/picture.js"></script>
<body>
	<div id="img_benner">
		<img src="img/main-picture.png" alt="배너">
	</div>
<h3><img src="../logo/Review.png" alt="리뷰" width=250px height=250px></h3>
<h3>글 작성</h3>
	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="traRevWriteForm" method="post" name="frm">
	
	<input type="hidden" name="travel_id" value="${tra_Rev.travel_id }">
		<table style="margin:auto;">
				<tr>
				<td> 내용</td>
				<td> <input type="text" name="r_content" size = "50"> </td>
			</tr>
		
			<tr>
				<td>평점</td>
				<td>
				    <label><input type="radio" name="r_score" value="0" <c:if test="${tra_Rev.r_score == 0}">checked</c:if>> 0</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <label><input type="radio" name="r_score" value="1" <c:if test="${tra_Rev.r_score == 1}">checked</c:if>> 1</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <label><input type="radio" name="r_score" value="2" <c:if test="${tra_Rev.r_score == 2}">checked</c:if>> 2</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <label><input type="radio" name="r_score" value="3" <c:if test="${tra_Rev.r_score == 3}">checked</c:if>> 3</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <label><input type="radio" name="r_score" value="4" <c:if test="${tra_Rev.r_score == 4}">checked</c:if>> 4</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <label><input type="radio" name="r_score" value="5" <c:if test="${tra_Rev.r_score == 5}">checked</c:if>> 5</label>
				</td>
		    </tr>

		</table>
		<hr>
					<div style="text-align:center;">
					<button type="submit">확인</button>
					<a href="tra">목록 보기</a><br>
					</div>
	</form>

</body>
<c:import url="footer.jsp"/>
</html>