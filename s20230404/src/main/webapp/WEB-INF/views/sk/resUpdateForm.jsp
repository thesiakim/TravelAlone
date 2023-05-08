<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<%
	String context = request.getContextPath();
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
	function deleteImage(restaurant_id, img_id, p_index) {		
		$.ajax({
		    url: "<%=context%>/deleteResImg",
		    data: {   restaurant_id : restaurant_id
		    	    , img_id   : img_id 
		          },
			dataType:'text',
		    success: function(result) {
				if (result == '1') {
					alert("사진삭제성공 "+result); 
					// 성공하면 아래라인 수행 
					$('#delImage'+p_index).remove();     /*  Delete Tag */
				} else {
			    	alert(".ajax deleteImage 실패하였습니다..."); 
				}
		      
		    }
		});
	 }
</script>
<body>
<div id="img_benner">
		<img src="img/main-picture.png" alt="배너">
	</div>
	<h1>맛집글 업데이트	</h1>
	<form action="updateRestaurant" method="post" enctype="multipart/form-data">>
		<input type="hidden" name="restaurant_id" value="${restaurant.restaurant_id }">
		<table style="margin:auto;">
		
			<tr>
				<td> 맛집종류</td>
				<td> <input type="text" name="r_common_restaurant"
					required="required" value="${restaurant.r_common_restaurant }"> </td>
			</tr>
			<tr>
				<td> 맛집지역</td>
				<td> <input type="text" name="r_common_loc" 
					required="required" value="${restaurant.r_common_loc }"> </td>
			</tr>
		
			<tr>
				<td> 맛집명</td>
				<td> <input type="text" name="r_name"
					required="required" value="${restaurant.r_name}"> </td>
			</tr>
				<tr>
				<td> 소개글</td>
				<td> <input type="text" name ="r_content"
					required="required" value="${restaurant.r_content}"> </td>
			</tr>
			
			<tr>
				<td> 맛집주소 </td>
				<td> <input type="text" name="r_address"
					required="required" value="${restaurant.r_address}"> </td>
			</tr>
			<tr>
				<td> 메뉴 </td>
				<td> <input type="text" name="r_menu"
					required="required" value="${restaurant.r_menu}"> </td>
			</tr>
			<tr>
				<td> 운영시간 </td>
				<td> <input type="text" name="r_hour"
					required="required" value="${restaurant.r_hour}"></td>
			</tr>
			
			<tr>
				<td>문의전화 </td>
				<td> <input type="text" name="r_call"
					required="required" value="${restaurant.r_call}"></td>
			</tr>
			
			<tr>
				<td>주차장여부 </td>
				<td> <input type="text" name="r_parking"
					required="required" value="${restaurant.r_parking}"></td>
			</tr>
								
		
			</table>
		<table style="margin:auto;">
			<tr> 사진 변경</tr>
			<tr>
				<td hidden>번호</td>													
				<td>
				<br>
				 
				<img alt="사진추가 " src="/images/restaurantUpload/${savedName}">
				<input type="file" name="file1" multiple="multiple"> <p>
				</td>
				
				
				<td>
				
						<c:forEach items="${imgResList}" var="resImg" varStatus="status">
					         <input type="text" name="restaurant_id" value="${resImg.restaurant_id}">
							<td hidden>${resImg.img_id}</td>
						 	<td  id="delImage${status.index}">
								<c:url value='/display' var='url'>
									<c:param name='file' value='${resImg.img_stored_file}'/>
								</c:url>
				                     <img alt="#" src="${url}"  width="500" height="300">
								
								
						 		<br>
		 				 		<a href="#" 
		 				 		   class="button" 
		 				 		   onclick="deleteImage(${resImg.restaurant_id},${resImg.img_id}, ${status.index})">
		 				 		   	사진삭제
		 				 		</a>
                             											 	
						 	</td>									
					</c:forEach>				
				 </td>		
				</tr>

			</table>
					<input type="submit" value="확인">
					<a href="javascript:window.history.back();">수정취소</a>
	</form>
	
	
	
</body>

	<c:import url="footer.jsp"/>
</html>