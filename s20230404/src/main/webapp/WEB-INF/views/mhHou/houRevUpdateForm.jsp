<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>숙소리뷰 수정	</h1>
	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="updateHouseRev" method="post" name="frm">
	<input type="hidden" id="review_id" name="review_id">
	<input type="hidden" id="house_id" name="house_id">
		<table>
	
			<tr>
				<td> 내용</td>
				<td> <input type="text" name="r_content" 
				required="required" value="${hou_Rev.r_content }"> </td>
			</tr>
		
			<tr>
				<td> 평점</td>
				<td> <input type="text" name="r_score" 
				required="required" value="${hou_Rev.r_score }"> </td>
			</tr>
			<tr >
				<td colspan="2"> <input type="submit" value="확인"> &nbsp;&nbsp; 
					<a href="javascript:window.history.back();">취소하기</a>
			</tr>
		</table>
	</form>
<script>
	const url = new URL(window.location.href);
	const urlParam = url.searchParams;
	review_id.setAttribute("value",urlParam.get('review_id'));
	house_id.setAttribute("value",urlParam.get('house_id'));
	
</script>

</body>
</html>