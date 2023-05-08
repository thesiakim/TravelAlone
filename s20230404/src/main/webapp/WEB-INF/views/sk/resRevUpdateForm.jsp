<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
    <%@ include file="../fragments/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>맛집 리뷰 수정	</h1>
	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="updateRestaurantRev" method="post" name="frm">
	<input type="hidden" id="review_id" name="review_id">
	<input type="hidden" id="restaurant_id" name="restaurant_id">
		<table>
	
			<tr>
				<td> 내용</td>
				<td> <input type="text" name="r_content" 
				required="required" value="${res_Rev.r_content }"> </td>
			</tr>
		
			<tr>
            <td>평점</td>
            <td>
                <label><input type="radio" name="r_score" value="0" <c:if test="${res_Rev.r_score == 0}">checked</c:if>> 0</label>
                <label><input type="radio" name="r_score" value="1" <c:if test="${res_Rev.r_score == 1}">checked</c:if>> 1</label>
                <label><input type="radio" name="r_score" value="2" <c:if test="${res_Rev.r_score == 2}">checked</c:if>> 2</label>
                <label><input type="radio" name="r_score" value="3" <c:if test="${res_Rev.r_score == 3}">checked</c:if>> 3</label>
                <label><input type="radio" name="r_score" value="4" <c:if test="${res_Rev.r_score == 4}">checked</c:if>> 4</label>
                <label><input type="radio" name="r_score" value="5" <c:if test="${res_Rev.r_score == 5}">checked</c:if>> 5</label>
            </td>
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
	restaurant_id.setAttribute("value",urlParam.get('restaurant_id'));
	
</script>

</body>
</html>