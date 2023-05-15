<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../fragments/header.jsp"%>

<!DOCTYPE html>
<%
	String context = request.getContextPath();
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/list.css" rel="stylesheet" type="text/css">
</head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">

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
		<img src="img/restaurant-picture.png" alt="배너">
	</div>

	<h3>
		<img src="img/res.png" alt="맛집" width=250px height=250px>
	</h3>
	<br>
	<hr>
	<br>
	<h1>글 수정</h1>
	<br>
	<form action="updateRestaurant" method="post" enctype="multipart/form-data">
		<input type="hidden" name="restaurant_id" value="${restaurant.restaurant_id }">
		<hr>
		<table>
			<tr>
				<td>맛집 종류</td>
				<td style="text-align: left;"> 
					<select name="t_common_travel">
						<option value="res100">한식</option>
						<option value="res200">중식</option>
						<option value="res300">일식</option>
						<option value="res400">양식</option>								
						<option value="res500">카페</option>								
						<option value="res600">기타</option>								
					</select>								
				</td>																						
			</tr>
			<tr>
				<td>맛집 지역</td>
				<td style="text-align: left;">
					<select name="r_common_loc">
						<option value="loc102">서울</option>
						<option value="loc132">인천</option>
						<option value="loc131">경기</option>
						<option value="loc133">강원</option>
						<option value="loc142">대전</option>
						<option value="loc141">충남</option>
						<option value="loc143">충북</option>
						<option value="loc151">부산</option>
						<option value="loc152">울산</option>
						<option value="loc153">대구</option>
						<option value="loc155">경남</option>
						<option value="loc154">경북</option>
						<option value="loc162">광주</option>
						<option value="loc161">전남</option>
						<option value="loc163">전북</option>
						<option value="loc164">제주</option>								
					</select>									
				 </td>				
			</tr>
		
			<tr>
				<td> 맛집명</td>
				<td> <input type="text" name="r_name"
					required="required" value="${restaurant.r_name}"> </td>
			</tr>
				<tr>
				<td>소개글</td>
				<td> <input type="text" name ="r_content"
					required="required" value="${restaurant.r_content}"> </td>
			</tr>
			
			<tr>
				<td>맛집주소 </td>
				<td> <input type="text" name="r_address"
					required="required" value="${restaurant.r_address}"> </td>
			</tr>
			<tr>
				<td>메뉴</td>
				<td> <input type="text" name="r_menu"
					required="required" value="${restaurant.r_menu}"> </td>
			</tr>
			<tr>
				<td>운영시간 </td>
				<td> <input type="text" name="r_hour"
					required="required" value="${restaurant.r_hour}"></td>
			</tr>
			
			<tr>
				<td>문의전화</td>
				<td> <input type="text" name="r_call"
					required="required" value="${restaurant.r_call}"></td>
			</tr>
			
			<tr>
				<td>주차장 여부</td>
				<td> <input type="text" name="r_parking"
					required="required" value="${restaurant.r_parking}"></td>
			</tr>
			<tr>
				<td>
					<img alt="사진추가 " src="/images/restaurantUpload/${savedName}"><br>
				</td>
				<td>
					<input type="file" name="file1" multiple="multiple">
				</td>
			</tr>
			<tr>
				<td hidden>번호</td>													
					<c:forEach items="${imgResList}" var="resImg" varStatus="status">
						<td hidden>${resImg.img_id}</td>
					 	<td  id="delImage${status.index}">
							<c:url value='/display' var='url'>
								<c:param name='file' value='${resImg.img_stored_file}'/>
							</c:url>
	                    <img alt="#" src="${url}"  width="100" height="100">
				 			<a href="#"  class="button"  onclick="deleteImage(${resImg.restaurant_id},${resImg.img_id}, ${status.index})"><input type="submit" value="삭제"></a>
					 	</td>									
					</c:forEach>				
				</tr>
			</table>
			
		<div style="text-align:center;">
			<br>
			<button type="submit">확인</button>
			<a href="javascript:window.history.back();"><button type="submit">취소</button></a>
		</div>
	</form>
	<br><br>	
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>