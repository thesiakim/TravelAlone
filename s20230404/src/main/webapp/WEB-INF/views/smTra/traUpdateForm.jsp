<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../fragments/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="/css/list.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">
	
	<script  src="http://code.jquery.com/jquery-latest.min.js">
	<script type="text/javascript">
	
		function deleteImage(travel_id, img_id, p_index) {
			$.ajax({
				url: "/deleteTraImg",
				data: {
					travel_id : travel_id,
					img_id : img_id
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
	<div id="img_banner">
		<img src="img/main-picture.png" alt="배너">
	</div>
  	<h3>
    	<img src="img/Tra.png" alt="여행지" width="250px" height="250px">
  	</h3>
  	<br>
  	<hr>
  	<br>
  <h1>글 수정</h1>
  <br>
  <form action="traUpdate" method="post" enctype="multipart/form-data">
    <input type="hidden" name="travel_id" value="${travel.travel_id}">
    <hr>
    <table>
		<tr>
        	<td>여행지 종류</td>
        	<td style="text-align: left;">
	          <select name="t_common_travel">
	            <option value="tra100">관광</option>
	            <option value="tra200">자연</option>
	            <option value="tra300">레저</option>
	            <option value="tra400">쇼핑</option>
	          </select>
        	</td>
		</tr>
      
		<tr>
        	<td>여행지 지역</td>
        	<td style="text-align: left;">
		          <select name="t_common_loc">
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
	    <td>여행지명</td>
	    <td><input type="text" name="t_name" required="required" value="${travel.t_name }"></td>
	  </tr>
	  
	  <tr>
	    <td>여행지 주소</td>
	    <td><input type="text" name="t_address" required="required" value="${travel.t_address }"></td>
	  </tr>
	  
	  <tr>
	    <td>입장료</td>
	    <td><input type="text" name="t_fee" required="required" value="${travel.t_fee }"></td>
	  </tr>
	  
	  <tr>
	    <td>운영 시간</td>
	    <td><input type="text" name="t_hour" required="required" value="${travel.t_hour }"></td>
	  </tr>
	  
	  <tr>
	    <td>문의 전화</td>
	    <td><input type="text" name="t_call" required="required" value="${travel.t_call }"></td>
	  </tr>
	  
	  <tr>
	    <td>주차장 여부</td>
	    <td><input type="text" name="t_parking" required="required" value="${travel.t_parking }"></td>
	  </tr>
	  
	  <tr>
	    <td>여행지 정보</td>
	    <td><input type="text" name="t_content" required="required" value="${travel.t_content }"></td>
	  </tr>
	  <tr>
	    <td>
	        <img alt="사진 추가하기" src="/images/traUpload/${savedName}">
	       </td>
	       <td>
	        <input type="file" name="file1" multiple="multiple">
	    </td>
	  </tr>
	  <tr>
         <td hidden>번호</td>
             <c:forEach items="${traImgList}" var="traImg" varStatus="status">
               <input type="hidden" name="travel_id" value="${traImg.travel_id}">
               <td hidden>${traImg.img_id}</td>
               <td id="delImage${status.index}">
	                 <c:url value='/display' var='url'>
	                   <c:param name='file' value='${traImg.img_stored_file}'/>
	                 </c:url>
                 <img alt="#" src="${url}" width="100" height="100"><br>
                 <a href="#" style="padding-left: 20px;" class="button" onclick="deleteImage(${traImg.travel_id},${traImg.img_id},${status.index})"><input type="submit" value="삭제"> </a>
               </td>
             </c:forEach>
	    </tr>
	  </table>
	  
	  
	  <div style="text-align:center; padding-right: 70px;">
	  <br>
	    <button type="submit">확인</button>
	    <a href="javascript:window.history.back();"><button type="submit">취소</button></a>
	  </div>
  </form>
  <br><br>
</body>
   	<c:import url="../fragments/footer.jsp"/>
</html>