<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ include file="../fragments/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
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
	        url: "insertResFav",     
	        data: { restaurant_id : ${restaurant.restaurant_id}	  },     
	        success:function(args){   
	        	alert( "즐겨찾기가 추가되었습니다." );
	        	window.location.reload();
	        },error:function(e){  
	        	alert("다시 시도해주세요.");
	        	console.log(e.responseText);
	        }  
	    });  
	}
	
	function delete_fav(){
		$.ajax({      
		  	url: "deleteResFav",
		  	method: "POST",
		  	data: { restaurant_id : ${restaurant.restaurant_id} },
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
   <img src="img/restaurant-picture.png" alt="배너">
</div>
   <h1><a href="res"><img src="img/res.png" alt="맛집" width=250px height=250px></a></h1>
   <!-- 맛집 종류 -->
   <div style="display: inline-block; width: 700px; text-align: center;">
      <c:forEach items="${boardresList}" var="list" varStatus="status">
        <!-- 컨트롤러로 보내는거 -->
           <a href="restaurantCodeFilter?code=$list.code}">
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
      <tr><td width="200px;">맛집명</td>         <td>${restaurant.r_name}</td></tr>
      <tr><td>맛집주소</td>         <td>${restaurant.r_address}</td></tr>
      <tr><td>메뉴</td>         <td>${restaurant.r_menu}</td></tr>
      <tr><td>운영시간</td>         <td>${restaurant.r_hour}</td></tr>
         <tr><td>문의전화</td>         <td>${restaurant.r_call}</td></tr>
         <tr><td>주차장 여부</td>         <td>${restaurant.r_parking}</td></tr>
         <tr><td>맛집 정보</td>      <td style="width: 380px;">${restaurant.r_content}</td></tr>
         <tr> <th hidden>          ${restaurant.restaurant_id} </th>  </tr>
      </tr>
   </table>
   <br><br>
   
   <c:if test="${user_role == 'rol200' }">
      <a href="resUpdateForm?restaurant_id=${restaurant.restaurant_id}"><input type="submit" value="수정"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="resDelete?restaurant_id=${restaurant.restaurant_id}"><input type="submit" value="삭제"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   </c:if>
   
   <c:choose>
      <c:when test="${isfavRes eq '0'}">
         <input type="button" value="즐겨찾기 추가" onclick="insert_fav()" style="width: 110px;">
           </c:when>
           <c:when test="${isfavRes eq '1'}">
         <input type="button" value="즐겨찾기 해제" onclick="delete_fav()" style="width: 110px;">
           </c:when>
   </c:choose>
   <br><br><hr><br><br>
   
   <!-- 클릭 시 팝업창에 띄울 원본 이미지 -->
   <h3>맛집 대표 이미지</h3><br>
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
</script><br><br>

<!-- 썸네일 이미지 표시할 테이블 -->         
<table style="margin: 0 auto;">
  <tr>
    <c:forEach items="${imgResList}" var="resImg">
      <td style="text-align: center;">
        <c:url value='/display' var='url'>
          <c:param name='file' value='${resImg.img_stored_file}'/>
        </c:url>
        <a href="javascript:void(0);" onclick="openPopup('${url}', '${resImg.img_id}')">
          <img alt="#" src="${url}" width="100" height="100">
        </a>
      </td>
    </c:forEach>
  </tr>
</table><br><br>
      
      <hr>
      <h3><img src="img/Review.png" alt="리뷰" width=250px height=250px></h3>
   <table style="margin:auto;" id="review">
                  <c:forEach items="${resRevList}" var="resRev">
                  <tr>
                      <td style="width: 100px;">${resRev.m_nickname}</td>
                      <td style="width: 250px;">${resRev.r_content}</td>
                      <td width="140px;" style="color: #ffd700;">
                     <c:choose>
                        <c:when test="${resRev.r_score eq '1'}">
                           <c:out value="★☆☆☆☆"/>
                        </c:when>
                        <c:when test="${resRev.r_score eq '2'}">
                           <c:out value="★★☆☆☆"/>
                        </c:when>
                        <c:when test="${traRev.r_score eq '3'}">
                           <c:out value="★★★☆☆"/>
                        </c:when>
                        <c:when test="${resRev.r_score eq '4'}">
                           <c:out value="★★★★☆"/>
                        </c:when>
                        <c:when test="${resRev.r_score eq '5'}">
                           <c:out value="★★★★★"/>
                        </c:when>
                        <c:otherwise>
                           <c:out value="☆☆☆☆☆"/>
                        </c:otherwise>
                     </c:choose>                                    
                  </td>               
                      
                      
                      <td style="width: 110px;">${resRev.create_date}</td>
                      <c:if test="${user_id == resRev.member_id }">
               <td style=" padding-left:30px;" ><a href="resRevUpdateForm?restaurant_id=${resRev.restaurant_id}&review_id=${resRev.review_id}"><input type="submit" value="수정"></a></td>
                   <td style=" padding-left:30px;">
                    <a href="deleteResRev?review_id=${resRev.review_id}" onclick="return confirm('정말로 삭제하시겠습니까?')"><input type="submit" value="삭제"></a>
                    </td>
                  </c:if>
                                 
               </tr>
            </c:forEach>
            <tr>
               
            </tr>
         </table><br>
            
      <h6>명예훼손, 개인정보 유출, 분쟁 유발, 허위 사실 유포 등의 글은 이용약관에 의해 제제는 물론<br> 
      법률에  의해 처벌받을 수 있습니다.건전한 커뮤니티를 위해 자제 당부 드립니다.</h6><br>
      
         <form action="resRevWriteForm" name="frm" method="post" >
         
      <input type="hidden" name="restaurant_id" value="${restaurant.restaurant_id }">
      <div id="replyForm">
         <input type="text" name="r_content" size = "50" value="리뷰를 입력하세요" onfocus="clearInput(this)">
         <br>
          <div class="star-rating">평점
       <div id="rating">
    <label for="score_0" onclick="changeRating(0)" onmouseover="changeRating(0)"><c:if test="${res_Rev.r_score == null || res_Rev.r_score == 0}"></c:if></label> 
    <label for="score_1" onclick="changeRating(1)" onmouseover="changeRating(1)"><c:if test="${res_Rev.r_score == 1}">checked</c:if>★</label>
    <label for="score_2" onclick="changeRating(2)" onmouseover="changeRating(2)"><c:if test="${res_Rev.r_score == 2}">checked</c:if>★</label>
    <label for="score_3" onclick="changeRating(3)" onmouseover="changeRating(3)"><c:if test="${res_Rev.r_score == 3}">checked</c:if>★</label>
    <label for="score_4" onclick="changeRating(4)" onmouseover="changeRating(4)"><c:if test="${res_Rev.r_score == 4}">checked</c:if>★</label>
    <label for="score_5" onclick="changeRating(5)" onmouseover="changeRating(5)"><c:if test="${res_Rev.r_score == 5}">checked</c:if>★</label>
    <input type="hidden" name="r_score" id="score_input" value="0">
  </div>
</div>
    <br><br>
    <div style="text-align:center;">
      <input type="submit" value="확인">
      <a href="tra">목록 보기</a><br><br>
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
      rating[i].style.color = "205E61";
    }
  }
  input.value = score;
}


</script>

</body>
		<c:import url="../fragments/footer.jsp"/>
</html>