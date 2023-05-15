<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<% String context = request.getContextPath(); %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/css/list.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
	<link href="/css/list.css" rel="stylesheet" type="text/css">
</head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">
<script type="text/javascript">
function deleteImage(house_id, img_id, p_index) {
	$.ajax({
	    url: "<%=context%>/deleteHouImg",
	    data: {
	    	house_id : house_id,
	    	img_id   : img_id
	    },
		dataType: 'text',
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

	<h3>
		<img src="img/Hou.png" alt="숙소" width=250px height=250px>
	</h3>
	<br>
	<hr>
	<br>
	<h1>글 수정</h1>
	<br>
		<form action="updateHouse" method="post" enctype="multipart/form-data">
		<input type="hidden" name="house_id" value="${house.house_id }">
		<hr>
		<table>
			<tr>
				<td>숙소종류</td>
				<td style="text-align: left;"> 
					<select name="h_common_house">
						<option value="hou100">호텔</option>
						<option value="hou200">모텔</option>
						<option value="hou300">민박/펜션</option>
						<option value="hou400">캠핑/글램핑</option>								
						<option value="hou500">게스트하우스</option>								
					</select>
				</td>
			</tr>
			
			<tr>
				<td> 여행지 지역</td>
				<td style="text-align: left;">
					<select name="h_common_loc">
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
				<td>숙소명</td>
				<td><input type="text" name="h_name"
					required="required" value="${house.h_name }"></td>
			</tr>
			<tr>
				<td>숙소주소</td>
				<td><input type="text" name="h_address"
					required="required" value="${house.h_address }"></td>
			</tr>
			<tr>
				<td>객실가격</td>
				<td><input type="text" name="h_room"
					required="required" value="${house.h_room }"></td>
			</tr>
			<tr>
				<td>체크인 체크아웃시간</td>
				<td><input type="text" name="h_checkinout"
					required="required" value="${house.h_checkinout }"></td>
			</tr>
			<tr>
				<td>문의전화</td>
				<td><input type="text" name="h_call"
					required="required" value="${house.h_call }"></td>
			</tr>
			<tr>
				<td> 조식여부</td>
				<td><input type="text" name="h_eat"
					required="required" value="${house.h_eat }"></td>
			</tr>
			<tr>
				<td>주차장여부</td>
				<td><input type="text" name="h_parking"
					required="required" value="${house.h_parking }"></td>
			</tr>
			<tr>
				<td>소개글</td>
				<td><input type="text" name="h_content"
					required="required" value="${house.h_content }"></td>
			</tr>
			<tr>
				<td>
					<img alt="사진 추가하기" src="/images/houseUpload/${savedName}">
				</td>
				<td>
					<input type="file" name="file1" multiple="multiple">
				</td>
			</tr>
			<tr>
				<td hidden>번호</td>													
					<c:forEach items="${imgHouList}" var="houImg" varStatus="status">
				         <input type="hidden" name="house_id" value="${houImg.house_id}">
						<td hidden>${houImg.img_id}</td>
					 	<td  id="delImage${status.index}">
								<c:url value='/display' var='url'>
									<c:param name='file' value='${houImg.img_stored_file}'/>
								</c:url>
			                <img alt="#" src="${url}"  width="100" height="100"><br>
						    <a href="#" class="button" onclick="deleteImage(${houImg.house_id},${houImg.img_id}, ${status.index})"><input type="submit" value="삭제"> </a>
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