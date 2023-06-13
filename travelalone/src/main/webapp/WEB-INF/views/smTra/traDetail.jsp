<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../fragments/header.jsp"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="/css/list.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script defer src="/js/detailBoardDelChk.js"></script>
    <script defer src="/js/detailBoardWarning.js"></script>
    <script defer src="/js/detailBoardRe.js"></script>
    <script defer src="/js/detailBoardLoginChk.js"></script>
</head>

<script type="text/javascript">
    function insert_fav(){
        $.ajax({
            type:"POST",
            url: "insertTraFav",
            data: { travel_id : ${travel.travel_id} },
            success:function(args){
                alert( "즐겨찾기가 추가되었습니다." );
                window.location.reload();
            },
            error:function(e){
                alert("로그인이 필요합니다.");
                console.log(e.responseText);
            }
        });
    }

    function delete_fav(){
        $.ajax({
            url: "deleteTraFav",
            method: "POST",
            data: { travel_id : ${travel.travel_id} },
            success:function(args){
                alert( "즐겨찾기가 해제되었습니다." );
                window.location.reload();
            },
            error:function(e){
                alert("로그인이 필요합니다.");
                console.log(e.responseText);
            }
        });
    }
</script>

 
<body>
   <div id="img_benner">
      <img src="img/travel-picture.png" alt="배너">
   </div>
   <h1>
      <a href="tra">
         <img src="img/Tra.png" alt="여행지" width=250px height=250px>
      </a>
   </h1>
   <!-- 여행지 종류 -->
   <div style="display: inline-block; width: 700px; text-align: center;">
      <c:forEach items="${boardTraList}" var="list" varStatus="status">
         <!-- 컨트롤러로 보내는거 -->
         <a href="traCodeFilter?code=${list.code}">
            <font size="5">${list.value}</font>
         </a>
         <c:if test="${not status.last}">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         </c:if>
      </c:forEach>
   </div>
   
   <hr>
   <br>

<!-- SQL 테이블 불러오기 -->
   <table>
      <tr><th hidden></th></tr>
      <tr><td width="200px;">여행지명</td>         <td>${travel.t_name}</td></tr>
      <tr><td>여행지주소</td>         				<td>${travel.t_address}</td></tr>
      <tr><td>입장료</td>        					<td>${travel.t_fee}</td></tr>
      <tr><td>운영시간</td>         				<td>${travel.t_hour}</td></tr>
      <tr><td>문의전화</td>         				<td>${travel.t_call}</td></tr>
      <tr><td>주차장여부</td>         				<td>${travel.t_parking}</td></tr>
      <tr><td>여행지 정보</td>      				<td style="width: 380px;">${travel.t_content}</td></tr>
      <tr><th hidden>          					${travel.travel_id} </th></tr>
   </table>
   <br><br>
   
   <c:if test="${user_role == 'rol200' }">
    <a href="traUpdateForm?travel_id=${travel.travel_id}">
    <input type="submit" value="수정">
	</a>      
	<a href="traDelete?travel_id=${travel.travel_id}">
	<input type="submit" value="삭제">
	</a>      
   </c:if>
	
	<c:choose>
	<c:when test="${isfavTra eq '0'}">
	<input type="button" value="즐겨찾기 추가" onclick="insert_fav()" style="width: 110px;">
	</c:when>
	
	<c:when test="${isfavTra eq '1'}">
	<input type="button" value="즐겨찾기 해제" onclick="delete_fav()" style="width: 110px;">
	</c:when>
	</c:choose>
	
	<br><br><hr><br><br>
	
	<h3>여행지 대표 이미지</h3><br>
	<!-- 클릭 시 팝업창에 띄울 원본 이미지 -->
	<div id="popup" style="display: none;">
	  <img id="popupImage" src="" style="width: 600px; height: 350px;">
	</div>
	<script>
	  // 이미지 클릭 시 팝업창 띄우기
	  function openPopup(url, id) {
	    // 이미지 썸네일 클릭 시 팝업창에 원본 이미지 띄우기
	    document.getElementById("popupImage").src = url;
	
	    // 팝업창 띄우기
	    var popup = document.getElementById("popup");
	    popup.style.display = "block";
	    popup.onclick = function() { 
	      // 팝업창 클릭 시 닫기
	      popup.style.display = "none";
	    }
	
	    // 원본 이미지 정보 전달
	    popupImage.setAttribute("data-id", id);
	  }
	</script>
	<br><br>

<!-- 썸네일 이미지 표시할 테이블 -->
<table style="margin: 0 auto;">
  <tr>
    <c:forEach items="${traImgList}" var="traImg">
      <td style="text-align: center;">
        <c:url value='/display' var='url'>
          <c:param name='file' value='${traImg.img_stored_file}'/>
        </c:url>
        <a href="javascript:void(0);" onclick="openPopup('${url}', '${traImg.img_id}')">
          <img alt="#" src="${url}" width="100" height="100">
        </a>
      </td>
    </c:forEach>
  </tr>
</table>

<br><br>
<hr>

<h3>
  <img src="img/Review.png" alt="리뷰" width="250px" height="250px">
</h3>
<table style="margin:auto;" id="review">
  <c:forEach items="${traRevList}" var="traRev">
    <tr>
      <td style="width: 100px;">${traRev.m_nickname}</td>
      <td style="width: 250px;">${traRev.r_content}</td>
      <td width="140px;" style="color: #ffd700;">
      
        <c:choose>
          <c:when test="${traRev.r_score eq '1'}">
            <c:out value="★☆☆☆☆"/>
          </c:when>
          <c:when test="${traRev.r_score eq '2'}">
            <c:out value="★★☆☆☆"/>
          </c:when>
          <c:when test="${traRev.r_score eq '3'}">
            <c:out value="★★★☆☆"/>
          </c:when>
          <c:when test="${traRev.r_score eq '4'}">
            <c:out value="★★★★☆"/>
          </c:when>
          <c:when test="${traRev.r_score eq '5'}">
            <c:out value="★★★★★"/>
          </c:when>
          <c:otherwise>
            <c:out value="☆☆☆☆☆"/>
          </c:otherwise>
        </c:choose>
      </td>
      
      <td style="width: 110px;">${traRev.create_date}</td>
      <c:if test="${user_id == traRev.member_id }">
        <td style=" padding-left:30px;" >
          <a href="traRevUpdateForm?travel_id=${traRev.travel_id}&review_id=${traRev.review_id}">
            <input type="submit" value="수정">
          </a>
        </td>
        <td style=" padding-left:30px;">
          <a href="traRevDelete?review_id=${traRev.review_id}" onclick="return confirm('정말로 삭제하시겠습니까?')">
            <input type="submit" value="삭제">
          </a>
        </td>
      </c:if>
    </tr>
  </c:forEach>
  
  <tr>
  </tr>
</table>
<br>

<!-- 페이징 -->
<div>
	<c:if test="${page.startPage > page.pageBlock }">
	      <a href="traDetail?tid=${travel.travel_id}&currentPage=${page.startPage-page.pageBlock}">[이전]</a>
	   </c:if>
	<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
		<a href="traDetail?tid=${travel.travel_id}&currentPage=${i}">[${i}]</a>
	<c:if test="${page.endPage < page.totalPage }">
	      <a href="traDetail?tid=${travel.travel_id}&currentPage=${page.startPage+page.pageBlock}">[다음]</a>
	   </c:if>	
	</c:forEach>
</div> 

<!-- 리뷰 -->            
<h6>
  명예훼손, 개인정보 유출, 분쟁 유발, 허위 사실 유포 등의 글은 이용약관에 의해 제제는 물론 
  법률에 의해 처벌받을 수 있습니다. 건전한 커뮤니티를 위해 자제 당부 드립니다.
</h6>
<br>
<form action="traRevWriteForm" name="frm" method="post">      
  <input type="hidden" name="travel_id" value="${travel.travel_id}">
  <div id="replyForm">
    <input type="text" name="r_content" size="50" value="리뷰를 입력하세요" onfocus="clearInput(this)">
    <br><br>
    <div class="star-rating"> 평점
      <div id="rating">
        <label for="score_0" onclick="changeRating(0)" onmouseover="changeRating(0)"></label>
        <label for="score_1" onclick="changeRating(1)" onmouseover="changeRating(1)" style="color: #205E61">★</label>
        <label for="score_2" onclick="changeRating(2)" onmouseover="changeRating(2)" style="color: #205E61">★</label>
        <label for="score_3" onclick="changeRating(3)" onmouseover="changeRating(3)" style="color: #205E61">★</label>
        <label for="score_4" onclick="changeRating(4)" onmouseover="changeRating(4)" style="color: #205E61">★</label>
        <label for="score_5" onclick="changeRating(5)" onmouseover="changeRating(5)" style="color: #205E61">★</label>
        <input type="hidden" name="r_score" id="score_input" value="0">
      </div>
    </div>
    <br><br>
    <div style="text-align:center;">
      <input type="submit" value="확인">
      <a href="tra">목록 보기</a>
      <br><br>
      <hr>
    </div>
  </div>
</form>

<script>
  function clearInput(input) {
	  input.value = "";
	  }

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