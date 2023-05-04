<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


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
		<img src="img/main-picture.png" alt="배너">
	</div>
	<h1>숙소글 상세		</h1>
	
	<div style="display:flex; justify-content:center;">	
		<!-- 사진 올리기 -->
		<table style="margin:left;">
				<tr>
					<td hidden>번호</td>													
					<td>사진</td>
				</tr>
				<tr> 
				<c:forEach items="${imgHouList}" var="houImg">
						<td hidden>${houImg.img_id}</td>
					 	<td >
					
						<c:url value='/display' var='url'>
							<c:param name='file' value='${houImg.img_stored_file}'/>
						</c:url>
		                     <img alt="#" src="${url}"  width="500" height="300">
					 	</td>
										
				</c:forEach>
				</tr>
		</table>
	
		<table style="margin:auto;">
			<tr><th>숙소명</th>			<td style=" padding-left:50px;">${house.h_name}</td></tr>
			<tr><th>숙소주소</th>			<td style=" padding-left:50px;">${house.h_address}</td></tr>
			<tr><th>객실 가격</th>			<td style=" padding-left:50px;">${house.h_room}</td></tr>
			<tr><th>체크인 체크아웃시간</th>	<td style=" padding-left:50px;">${house.h_checkinout}</td></tr>
			<tr><th>문의전화</th>			<td style=" padding-left:50px;">${house.h_call}</td></tr>
			<tr><th>조식여부</th>			<td style=" padding-left:50px;">${house.h_eat}</td></tr>
			<tr><th>주차장여부</th>		<td style=" padding-left:50px;">${house.h_parking}</td></tr>
			<tr><th>비고</th>				<td style=" padding-left:50px;">${house.h_content}</td></tr>
			<tr> <th hidden> ${house.house_id} </th>  </tr>
			<tr></tr>
		</table>
	
	</div>
		<hr>
		<div>
			<a href="hou" class="button">목록</a>
			<a href="houUpdateForm?house_id=${house.house_id}" class="button">수정</a>
			<a href="deleteHouse?house_id=${house.house_id}" class="button">삭제</a>
			<c:choose>
				<c:when test="${isfavHou eq '0'}">
					<a href="" onclick="insert_fav()">즐겨찾기</a>
				</c:when>
				<c:when test="${isfavHou eq '1'}">
					<a href="" onclick="delete_fav()">즐겨찾기 해제</a>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</div>
		<%-- <form action="insertHouFav?house_id=${house.house_id}" method="post" >
		<input type="submit" value="즐겨찾기">
		</form> 
		<form action="deleteHouFav?house_id=${house.house_id}" method="post" >
		<input type="submit" value="즐겨찾기해제">
		</form>  --%>
		
  <%-- <c:forEach items="${favHouList}" var="houFav"  varStatus="status">
			<a href="#"  class="button" 
		 		   onclick="insert-delete-houFav(${houFav.member_id},${houFav.house_id}, ${status.index})">
		 	아작스즐겨찾기</a>	
 </c:forEach> --%>
  
 
		<hr>
		<h3>리뷰		</h3>
	<table style="margin:auto;">
				<tr>
					<td>리뷰번호</td>													
					<td  style=" padding-left:30px;">작성자</td>
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
			</table>
	
				
				<a  style=" padding-left:600px;" href="houRevWriteForm?house_id=${house.house_id}">리뷰작성</a>
			
	
	
</body>
	<c:import url="footer.jsp"/>
</html>