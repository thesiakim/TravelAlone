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
	function deleteImage(house_id, img_id, p_index) {
	/* 	alert("house_id:"   + house_id);
		alert("img_id:"	    + img_id);
		alert("p_index:"	+ p_index); */
		
		$.ajax({
		    url: "<%=context%>/deleteHouImg",
		    data: {   house_id : house_id
		    	    , img_id   : img_id 
		          },
			dataType:'text',
		    success: function(result) {
		   /*  	alert(".ajax deleteImage success ..."); 
				alert(".ajax deleteImage data->"+result);  */
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
	<h1>숙소글 글업데이트	</h1>
		<form action="updateHouse" method="post" enctype="multipart/form-data">
		<input type="hidden" name="house_id" value="${house.house_id }">
		<table style="margin:auto;">
											
			<tr>
				<th>숙소종류</th>
				<td><input type="text" name="h_common_house"
					required="required" value="${house.h_common_house }"></td>
			</tr>	
			<tr>
				<th>숙소지역</th>
				<td><input type="text" name="h_common_loc"
					required="required" value="${house.h_common_loc }"></td>
			</tr>	
								
			<tr>
				<th>숙소명</th>
				<td><input type="text" name="h_name"
					required="required" value="${house.h_name }"></td>
			</tr>
			<tr>
				<th>소개글</th>
				<td><input type="text" name="h_content"
					required="required" value="${house.h_content }"></td>
			</tr>
			<tr>
				<th>숙소주소</th>
				<td><input type="text" name="h_address"
					required="required" value="${house.h_address }"></td>
			</tr>
			<tr>
				<th>객실가격</th>
				<td><input type="text" name="h_room"
					required="required" value="${house.h_room }"></td>
			</tr>
			<tr>
				<th>체크인 체크아웃시간</th>
				<td><input type="text" name="h_checkinout"
					required="required" value="${house.h_checkinout }"></td>
			</tr>
			<tr>
				<th>문의전화</th>
				<td><input type="text" name="h_call"
					required="required" value="${house.h_call }"></td>
			</tr>
			<tr>
				<th> 조식여부</th>
				<td><input type="text" name="h_eat"
					required="required" value="${house.h_eat }"></td>
			</tr>
			<tr>
				<th>주차장여부</th>
				<td><input type="text" name="h_parking"
					required="required" value="${house.h_parking }"></td>
			</tr>


	



		</table>
		<table style="margin:auto;">
			<tr> 사진 변경</tr>
			<tr>
				<td hidden>번호</td>													
				<td>
				<br>
				 
				<img alt="사진추가 " src="/images/houseUpload/${savedName}">
				<input type="file" name="file1" multiple="multiple"> <p>
				</td>
				
				
				<td>
				
						<c:forEach items="${imgHouList}" var="houImg" varStatus="status">
					         <input type="text" name="house_id" value="${houImg.house_id}">
							<td hidden>${houImg.img_id}</td>
						 	<td  id="delImage${status.index}">
								<c:url value='/display' var='url'>
									<c:param name='file' value='${houImg.img_stored_file}'/>
								</c:url>
				                     <img alt="#" src="${url}"  width="500" height="300">
								
								
						 		<br>
		 				 		<a href="#" 
		 				 		   class="button" 
		 				 		   onclick="deleteImage(${houImg.house_id},${houImg.img_id}, ${status.index})">
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