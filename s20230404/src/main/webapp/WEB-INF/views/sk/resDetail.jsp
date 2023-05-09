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
<style>
th, td { 
   border-bottom: 2px solid rgb(64, 64, 64); 
}
</style>
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
	            alert("다시 시도해주세요.");
	            console.log(e.responseText);
	        }  
	    });  
	}

	</script>
	<body>

	<div id="img_benner">
			<img src="img/main-picture.png" alt="배너">
		</div>
		<h3><img src="../logo/Res.png" alt="맛집" width=250px height=250px></h3>
		<h3>상세 보기</h3><br>
		<hr>
		<br>	
	
<!-- SQL 테이블 불러오기 -->
	<table style="margin:auto;">
		<tr><th>맛집명</th>			<td>${restaurant.r_name}</td></tr>
		<tr><th>맛집주소</th>			<td>${restaurant.r_address}</td></tr>
		<tr><th>메뉴</th>				<td>${restaurant.r_menu}</td></tr>
		<tr><th>운영시간</th>			<td>${restaurant.r_hour}</td></tr>
		<tr><th>문의 전화</th>			<td>${restaurant.r_call}</td></tr>
		<tr><th>주차장여부</th>		<td>${restaurant.r_parking}</td></tr>
		<tr><th>비고</th>				<td>${restaurant.r_content}</td></tr>
		<tr> <th hidden>			    ${restaurant.restaurant_id} </th></tr>
		
				<tr><td colspan="2">
		</tr>
	</table>
	<br><br>


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
          <img alt="UpLoad Image" src="${url}" width="100" height="100">
        </a>
      </td>
    </c:forEach>
  </tr>
</table>
		
	
	<br><br><hr>
			<a href="res" class="button">목록</a>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="resUpdateForm?restaurant_id=${restaurant.restaurant_id}" class="button">수정</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="deleteRestaurant?restaurant_id=${restaurant.restaurant_id}" class="button">삭제</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:choose>
				<c:when test="${isfavRes eq '0'}">
					<a href="" onclick="insert_fav()">즐겨찾기</a>
				</c:when>
				<c:when test="${isfavRes eq '1'}">
					<a href="" onclick="delete_fav()">즐겨찾기 해제</a>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		
		<hr>
		<h3><img src="../logo/Review.png" alt="리뷰" width=250px height=250px></h3>
	<table style="margin:auto;">
				<tr>
					<td>리뷰번호</td>													
					<td>작성자</td>
					<td>내용</td>
					<td>평점</td>
					<td>작성일</td>
					
					

				</tr>
					<c:forEach items="${resRevList}" var="resRev">
					<tr>
						<td>${resRev.review_id}</td>
						<td>${resRev.m_nickname}</td>
					 	<td>${resRev.r_content}</td>
					 	<td>${resRev.r_score}</td>
					 	<td>${resRev.create_date}</td>
					 	<td  style=" padding-left:30px;" ><a href="resRevUpdateForm?restaurant_id=${resRev.restaurant_id}&review_id=${resRev.review_id}">수정</a></td>
					 	<td style=" padding-left:30px;">

					 	 <a href="deleteResRev?review_id=${resRev.review_id}" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</a>
					 	 </td>
						
											
					</tr>
				</c:forEach>
				<tr>
					
				</tr>
			</table>
				<br><br>
					<a href="resRevWriteForm?restaurant_id=${restaurant.restaurant_id}"><button type="submit" style="margin-left: 664px; margin-bottom: 10px">리뷰 작성</button></a>
	
	
	
	
</body>
	<c:import url="footer.jsp"/>
</html>