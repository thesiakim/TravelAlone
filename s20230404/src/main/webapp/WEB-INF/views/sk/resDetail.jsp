<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- <%@ include file="../fragments/header.jsp"%> --%>
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

	const btn = document.getElementById("insert-delete-resFav");

	let clickCount = 0;
</script>

<body>
<div id="img_benner">
		<img src="img/restaurant-picture.png" alt="배너">
	</div>
	<h1>맛집글 상세		</h1>
	
<div style="display:flex; justify-content:center;">	
	<!-- 사진 올리기 -->
<table style="margin:left;">
		<tr>
			<td hidden>번호</td>													
			<td>사진</td>
			
		</tr>
		<tr>
		<c:forEach items="${imgResList}" var="resImg">
				<td hidden>${resImg.img_id}</td>
			 	<td >
			 	<c:url value='/display' var='url'><c:param name='file' value='${resImg.img_stored_file}'/></c:url>
						<img alt="UpLoad Image" src="${url}" width="500" height="300">
			 	
			 	</td>
								
		</c:forEach>
		</tr>
</table>	
	

	<table style="margin:auto;">
		<tr><th>맛집명</th>			<td>${restaurant.r_name}</td></tr>
		<tr><th>맛집주소</th>			<td>${restaurant.r_address}</td></tr>
		<tr><th>메뉴</th>				<td>${restaurant.r_menu}</td></tr>
		<tr><th>운영시간</th>			<td>${restaurant.r_hour}</td></tr>
		<tr><th>문의 전화</th>			<td>${restaurant.r_call}</td></tr>
		<tr><th>주차장여부</th>		<td>${restaurant.r_parking}</td></tr>
		<tr><th>비고</th>				<td>${restaurant.r_content}</td></tr>
		<tr> <th hidden>			    ${restaurant.restaurant_id} </th></tr>
		<tr></tr>
		</table>
	
	</div>
		<hr>
		<div>
			<a href="res" class="button">목록</a>	
			
				<c:if test="${user_role == 'rol200' }">
			<a href="resUpdateForm?restaurant_id=${restaurant.restaurant_id}" class="button">수정</a>
			<a href="deleteRestaurant?restaurant_id=${restaurant.restaurant_id}" onclick="return confirm('정말로 삭제하시겠습니까?')" class="button">삭제</a>
				</c:if>
			
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
		<h3>리뷰		</h3>
	<table style="margin:auto;">
				<tr>
					<td>리뷰번호</td>													
					<td  style=" padding-left:30px;">작성자</td>
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
					 	
					 	<td  style=" padding-left:30px;" >
					 		<c:if test="${user_id == resRev.member_id }">
					 	<a href="resRevUpdateForm?restaurant_id=${resRev.restaurant_id}&review_id=${resRev.review_id}">수정</a>
					 		 	</c:if>
					 	</td>
					 	
					 	<td style=" padding-left:30px;">
								<c:if test="${user_id == resRev.member_id }">
					 	 <a href="deleteResRev?review_id=${resRev.review_id}" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</a>
					 			</c:if>
					 	 </td>
						
											
					</tr>
				</c:forEach>
				<tr>
					<a  style=" padding-left:600px;" href="resRevWriteForm?restaurant_id=${restaurant.restaurant_id}">리뷰작성</a>
	
	
	
	
</body>
	<c:import url="footer.jsp"/>
</html>