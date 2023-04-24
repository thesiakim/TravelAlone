<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>리뷰글 상세		</h1>
	<table>
		<tr><th>카테고리</th>			<td>${review.r_common_tsr_category}</td></tr>
		<tr><th>내용</th>				<td>${review.r_content}</td></tr>
		<tr><th>점수</th>				<td>${review.r_score}</td></tr>
		<tr><th>생성일</th>			<td>${review.create_date}</td></tr>
		<tr><th>수정일</th>			<td>${review.modified_date}</td></tr>
		<tr> <th hidden> ${review.review_id} </th>  </tr>
		

		<tr><td colspan="2">
		    <input type="button" value="목록" 
				onclick="location.href='rev'">	
			<input type="button" value="수정" 
				onclick="location.href='revUpdateForm?review_id=${review.review_id}'">
			<input type="button" value="삭제" 
				onclick="location.href='deleteReview?review_id=${review.review_id}'">
				</td>
		</tr>
	</table>
	
	
</body>
</html>