<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script defer src="/js/detailBoardDelChk.js"></script>
<script defer src="/js/detailBoardWarning.js"></script>
<script defer src="/js/detailBoardRe.js"></script>
<script defer src="/js/detailBoardLoginChk.js"></script>
<link href="/css/list.css" rel="stylesheet" type="text/css">
</head>

<script type="text/javascript">
	function insert_fav(){
		$.ajax({      
	        type:"POST",  
	        url: "insertHouFav",     
	        data: { house_id : ${house.house_id}	  },     
	        success:function(args){   
	        	alert( "즐겨찾기가 추가되었습니다." );
	        	window.location.reload();
	        },error:function(e){  
	        	alert("로그인이 필요합니다.");
	        	console.log(e.responseText);
	        }  
	    });  
	}
	
	function delete_fav(){
		$.ajax({      
		  	url: "deleteHouFav",
		  	method: "POST",
		  	data: { house_id : ${house.house_id} },
	        success:function(args){   
	        	alert( "즐겨찾기가 해제되었습니다." );
	        	window.location.reload();
	        },error:function(e){  
	            alert("로그인이 필요합니다.");
	            console.log(e.responseText);
	        }  
	    });  
	}
	</script>

<body>
	<div id="img_benner">
		<img src="img/house-picture.png" alt="배너">
	</div>
	<h1>
		<a href="hou"><img src="img/Hou.png" alt="숙소" width=250px
			height=250px></a>
	</h1>
	<!-- 숙소 종류 -->
	<div style="display: inline-block; width: 700px; text-align: center;">
		<c:forEach items="${boardList}" var="list" varStatus="status">
			<!-- 컨트롤러로 보내는거 -->
			<a href="houseCodeFilter?code=${list.code}"> <font size="5">${list.value}</font>
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
		<tr>
			<th hidden></th>
		</tr>
		<tr>
			<td width="200px;">숙소명
			</th>
			<td>${house.h_name}</td>
		</tr>
		<tr>
			<td>숙소주소</td>
			<td>${house.h_address}</td>
		</tr>
		<tr>
			<td>객실 가격</td>
			<td>${house.h_room}</td>
		</tr>
		<tr>
			<td>체크인 체크아웃시간</td>
			<td>${house.h_checkinout}</td>
		</tr>
		<tr>
			<td>문의전화</td>
			<td>${house.h_call}</td>
		</tr>
		<tr>
			<td>조식여부</td>
			<td>${house.h_eat}</td>
		</tr>
		<tr>
			<td>주차장여부</td>
			<td>${house.h_parking}</td>
		</tr>
		<tr>
			<td>비고</td>
			<td style="width: 380px;">${house.h_content}</td>
		</tr>
		<tr>
			<th hidden>${house.house_id}</th>
		</tr>
	</table>
	<br>
	<br>

	<c:if test="${user_role == 'rol200' }">
		<a href="houUpdateForm?house_id=${house.house_id}"><input
			type="submit" value="수정"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="deleteHouse?house_id=${house.house_id}"><input
			type="submit" value="삭제"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   </c:if>

	<c:choose>
		<c:when test="${isfavHou eq '0'}">
			<input type="button" value="즐겨찾기 추가" onclick="insert_fav()"
				style="width: 110px;">
		</c:when>
		<c:when test="${isfavHou eq '1'}">
			<input type="button" value="즐겨찾기 해제" onclick="delete_fav()"
				style="width: 110px;">
		</c:when>
	</c:choose>
	<br><br><hr><br><br>

	<!-- 클릭 시 팝업창에 띄울 원본 이미지 -->
	<h3>숙소 대표 이미지</h3>
	<br>
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
	<br>
	<br>

	<!-- 썸네일 이미지 표시할 테이블 -->
	<table style="margin: 0 auto;">
		<tr>
			<c:forEach items="${imgHouList}" var="houImg">
				<td style="text-align: center;"><c:url value='/display'
						var='url'>
						<c:param name='file' value='${houImg.img_stored_file}' />
					</c:url> <a href="javascript:void(0);"
					onclick="openPopup('${url}', '${houImg.img_id}')"> <img alt="#"
						src="${url}" width="100" height="100">
				</a></td>
			</c:forEach>
		</tr>
	</table>
	<br>
	<br>

	<hr>
	<h3>
		<img src="img/Review.png" alt="리뷰" width=250px height=250px>
	</h3>
	<table style="margin: auto;" id="review">
		<c:forEach items="${houRevList}" var="houRev">
			<tr>
				<td style="width: 100px;">${houRev.m_nickname}</td>
				<td style="width: 250px;">${houRev.r_content}</td>
				<td width="140px;" style="color: #ffd700;"><c:choose>
						<c:when test="${houRev.r_score eq '1'}">
							<c:out value="★☆☆☆☆" />
						</c:when>
						<c:when test="${houRev.r_score eq '2'}">
							<c:out value="★★☆☆☆" />
						</c:when>
						<c:when test="${houRev.r_score eq '3'}">
							<c:out value="★★★☆☆" />
						</c:when>
						<c:when test="${houRev.r_score eq '4'}">
							<c:out value="★★★★☆" />
						</c:when>
						<c:when test="${houRev.r_score eq '5'}">
							<c:out value="★★★★★" />
						</c:when>
						<c:otherwise>
							<c:out value="☆☆☆☆☆" />
						</c:otherwise>
					</c:choose></td>

				<td style="width: 110px;">${houRev.create_date}</td>
				<c:if test="${user_id == houRev.member_id }">
					<td style="padding-left: 30px;"><a
						href="houRevUpdateForm?house_id=${houRev.house_id}&review_id=${houRev.review_id}"><input
							type="submit" value="수정"></a></td>
					<td style="padding-left: 30px;"><a
						href="deleteHouRev?review_id=${houRev.review_id}"
						onclick="return confirm('정말로 삭제하시겠습니까?')"><input type="submit"
							value="삭제"></a></td>
				</c:if>

			</tr>
		</c:forEach>
		<tr>

		</tr>
	</table>
	<br>
	
 <div>
	
	<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
		<a
			href="houDetail?hid=${house.house_id}&currentPage=${i}">[${i}]</a>
	</c:forEach>

</div> 
	<h6>
		명예훼손, 개인정보 유출, 분쟁 유발, 허위 사실 유포 등의 글은 이용약관에 의해 제제는 물론<br> 법률에 의해
		처벌받을 수 있습니다. 건전한 커뮤니티를 위해 자제 당부 드립니다.
	</h6>
	<br>

	<form action="houRevWriteForm" name="frm" method="post">
		<input type="hidden" name="house_id" value="${house.house_id}">
		<div id="replyForm">
			<input type="text" name="r_content" size="50" value="리뷰를 입력하세요"
				onfocus="clearInput(this)"> <br>
			<br>
			<div class="star-rating">
				평점
				<div id="rating">
					<label for="score_0" onclick="changeRating(0)"
						onmouseover="changeRating(0)"></label> <label for="score_1"
						onclick="changeRating(1)" onmouseover="changeRating(1)"
						style="color: #205E61">★</label> <label for="score_2"
						onclick="changeRating(2)" onmouseover="changeRating(2)"
						style="color: #205E61">★</label> <label for="score_3"
						onclick="changeRating(3)" onmouseover="changeRating(3)"
						style="color: #205E61">★</label> <label for="score_4"
						onclick="changeRating(4)" onmouseover="changeRating(4)"
						style="color: #205E61">★</label> <label for="score_5"
						onclick="changeRating(5)" onmouseover="changeRating(5)"
						style="color: #205E61">★</label> <input type="hidden"
						name="r_score" id="score_input" value="0">
				</div>
			</div>
			<br>
			<br>
			<div style="text-align: center;">
				<input type="submit" value="확인"> <a href="hou">목록 보기</a><br>
				<br>
				<hr>
				<br>
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
<c:import url="../fragments/footer.jsp" />
</html>