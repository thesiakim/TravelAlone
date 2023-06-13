<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ include file="../fragments/header.jsp"%>
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
<h3><img src="img/Review.png" alt="리뷰" width=250px height=250px></h3>
<h3>글 수정</h3>
	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="updateHouseRev" method="post" name="frm">
	<input type="hidden" id="review_id" name="review_id">
	<input type="hidden" id="house_id" name="house_id">
		<table>
	
			<tr>
				<td> 내용</td>
				<td> <input type="text" name="r_content" size=50 required="required" value="${hou_Rev.r_content }"> </td>
			</tr>
			<tr>
				<td> 평점</td>
				<td> 
				 <div class="star-rating">
          <div id="rating">
            <label for="score_0" onclick="changeRating(0)" onmouseover="changeRating(0)"></label>
            <label for="score_1" onclick="changeRating(1)" onmouseover="changeRating(1)" style="color: ${hou_Rev.r_score >= 1 ? '#ffd700' : '#205E61'}">★</label>
            <label for="score_2" onclick="changeRating(2)" onmouseover="changeRating(2)" style="color: ${hou_Rev.r_score >= 2 ? '#ffd700' : '#205E61'}">★</label>
            <label for="score_3" onclick="changeRating(3)" onmouseover="changeRating(3)" style="color: ${hou_Rev.r_score >= 3 ? '#ffd700' : '#205E61'}">★</label>
            <label for="score_4" onclick="changeRating(4)" onmouseover="changeRating(4)" style="color: ${hou_Rev.r_score >= 4 ? '#ffd700' : '#205E61'}">★</label>
            <label for="score_5" onclick="changeRating(5)" onmouseover="changeRating(5)" style="color: ${hou_Rev.r_score >= 5 ? '#ffd700' : '#205E61'}">★</label>
            <input type="hidden" name="r_score" id="score_input" value="${hou_Rev.r_score == null ? 0 : hou_Rev.r_score}">
          </div>
        </div>
      </td>
    </tr>
  </table>
  <hr>
  <div style="text-align:center;">
    <input type="submit" value="확인">
    <a href="hou">목록 보기</a><br>
  </div>
</form>
<script>
  const url = new URL(window.location.href);
  const urlParam = url.searchParams;
  review_id.setAttribute("value", urlParam.get('review_id'));
  house_id.setAttribute("value", urlParam.get('house_id'));

  function changeRating(score) {
    const rating = document.querySelectorAll("#rating label");
    const input = document.querySelector("#score_input");

    // 라벨의 색깔을 바꾸고 점수를 업데이트합니다.
    for (let i = 0; i < rating.length; i++) {
      if (i <= score) {
        rating[i].style.color = "#ffd700";
      } else {
        rating[i].style.color = "#205E61";
      }
    }
    input.value = score;
  }
</script>

</body>
	<c:import url="../fragments/footer.jsp"/>
</html>