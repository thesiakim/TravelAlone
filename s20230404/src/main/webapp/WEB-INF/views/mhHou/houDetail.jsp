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
	        url: "insertHouFav",     
	        data: { house_id : ${house.house_id}	  },     
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
		  	url: "deleteHouFav",
		  	method: "POST",
		  	data: { house_id : ${house.house_id} },
	        success:function(args){   
	        	alert( "즐겨찾기가 해제되었습니다." );
	        	window.location.reload();
	        },error:function(e){  
	            alert("다시 시도해주세요.");
	            console.log(e.responseText);
	        }  
	    });  
	}
	
//int isfavHou = 0;
//버튼 요소를 가져옵니다.
const btn = document.getElementById("insert-delete-houFav");

// 클릭 횟수를 저장할 변수를 선언합니다.
let clickCount = 0;
// 버튼 클릭 시 실행할 함수를 정의합니다.
/* btn.addEventListener("click", function(member_id,house_id) {
  // AJAX 요청을 생성합니다.
  const xhr = new XMLHttpRequest();

  // 요청이 완료되면 실행될 함수를 정의합니다.
  xhr.onreadystatechange = function(member_id,house_id) {
    if (xhr.readyState === 4 && xhr.status === 200) {
      console.log(xhr.responseText);
    }
  };

  // 클릭 횟수에 따라 실행할 요청을 결정합니다.
  if (clickCount % 2 === 0) {
    // insert 요청을 생성합니다.
    xhr.open("POST", "/insertHouFav", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify({data: "insert"}));
    btn.innerHTML = "즐겨찾기해제";
  } else {
    // delete 요청을 생성합니다.
    xhr.open("POST", "/deleteHouFav", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify({data: "delete"}));
    btn.innerHTML = "즐겨찾기";
  }

  // 클릭 횟수를 증가합니다.
  clickCount++;
}); */





</script>


<body>
	<div id="img_benner">
		<img src="img/house-picture.png" alt="배너">
	</div>
	<h3><img src="../logo/Hou.png" alt="숙소" width=250px height=250px></h3>
	<h3>상세 보기</h3><br>
	<hr>
	<br>

<!-- SQL 테이블 불러오기 -->
	<table style="margin:auto;">
			<tr><th>숙소명</th>			<td>${house.h_name}</td></tr>
			<tr><th>숙소주소</th>			<td>${house.h_address}</td></tr>
			<tr><th>객실 가격</th>			<td>${house.h_room}</td></tr>
			<tr><th>체크인 체크아웃시간</th>	<td>${house.h_checkinout}</td></tr>
			<tr><th>문의전화</th>			<td>${house.h_call}</td></tr>
			<tr><th>조식여부</th>			<td>${house.h_eat}</td></tr>
			<tr><th>주차장여부</th>			<td>${house.h_parking}</td></tr>
			<tr><th>비고</th>				<td>${house.h_content}</td></tr>
			<tr> <th hidden>			 ${house.house_id} </th>  </tr>
			<tr><td colspan="2">
			</tr>
		</table>
		<br><br>

		<tr><td colspan="2">
		</tr>
	</table>
	<br><br>

<!-- 클릭 시 팝업창에 띄울 원본 이미지 -->
<h3>숙소 대표 이미지</h3><br>
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
    <c:forEach items="${traHouList}" var="traImg">
      <td style="text-align: center;">
        <c:url value='/display' var='url'>
          <c:param name='file' value='${houImg.img_stored_file}'/>
        </c:url>
        <a href="javascript:void(0);" onclick="openPopup('${url}', '${traImg.img_id}')">
          <img alt="#" src="${url}" width="100" height="100">
        </a>
      </td>
    </c:forEach>
  </tr>
</table>


		
	
	<br><br><hr>
			<a href="hou" class="button">목록</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="houUpdateForm?house_id=${house.house_id}" class="button">수정</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="deleteHouse?house_id=${house.house_id}" class="button">삭제</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:choose>
				<c:when test="${isfavHou eq '0'}">
					<a href="" onclick="insert_fav()">즐겨찾기</a>
				</c:when>
				<c:when test="${isfavHou eq '1'}">
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
					<c:forEach items="${houRevList}" var="houRev">
					<tr>
						<td>${houRev.review_id}</td>
					 	<td>${houRev.m_nickname}</td>
					 	<td>${houRev.r_content}</td>
					 	<td>${houRev.r_score}</td>
					 	<td>${houRev.create_date}</td>
					 <td  style=" padding-left:30px;" ><a href="houRevUpdateForm?house_id=${houRev.house_id}&review_id=${houRev.review_id}">수정</a></td>
					 	<td style=" padding-left:30px;">
					 	 <a href="deleteHouRev?review_id=${houRev.review_id}" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</a>
					 	 </td>
						
											
					</tr>
				</c:forEach>
			<tr>
			</tr>
		</table>
		<br><br>
				<a href="houRevWriteForm?house_id=${house.house_id}"><button type="submit" style="margin-left: 664px; margin-bottom: 10px">리뷰 작성</button></a>

	
	
</body>
	<c:import url="footer.jsp"/>
</html>