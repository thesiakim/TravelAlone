<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

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
				url: "<%=context%>/deleteTraImg",
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
  <h3>글 수정</h3>
  <br>
  <form action="traUpdate" method="post" enctype="multipart/form-data">
    <input type="hidden" name="travel_id" value="${travel.travel_id}">
    <hr>
    <table style="margin:auto;">
    
      <br><br>
    
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
	    <th>여행지명</th>
	    <td><input type="text" name="t_name" required="required" value="${travel.t_name }"></td>
	  </tr>
	  
	  <tr>
	    <th>여행지 주소</th>
	    <td><input type="text" name="t_address" required="required" value="${travel.t_address }"></td>
	  </tr>
	  
	  <tr>
	    <th>입장료</th>
	    <td><input type="text" name="t_fee" required="required" value="${travel.t_fee }"></td>
	  </tr>
	  
	  <tr>
	    <th>운영 시간</th>
	    <td><input type="text" name="t_hour" required="required" value="${travel.t_hour }"></td>
	  </tr>
	  
	  <tr>
	    <th>문의 전화</th>
	    <td><input type="text" name="t_call" required="required" value="${travel.t_call }"></td>
	  </tr>
	  
	  <tr>
	    <th>주차장 여부</th>
	    <td><input type="text" name="t_parking" required="required" value="${travel.t_parking }"></td>
	  </tr>
	  
	  <tr>
	    <th>여행지 정보</th>
	    <td><input type="text" name="t_content" required="required" value="${travel.t_content }"></td>
	  </tr>
	</table>
	
	<br><br>
	<hr>
	<br><br>

	
    <table style="margin:auto;">
	  <tr>
	    <td><font size=5>사진 변경</font></td>
	  </tr>
	  <tr>
	    <td>
	      <br>
	      <font size=4>
	        <img alt="사진 추가하기" src="/images/traUpload/${savedName}">
	        <br>
	        <input type="file" name="file1" multiple="multiple">
	      </font>
	    </td>
	  </tr>
	  <tr>
	    <td>
	      <br><br>
	      
	      <table>
	        <tr>
	          <td hidden>번호</td>
	          <td>
	            <p>
	            <td>
	              <c:forEach items="${traImgList}" var="traImg" varStatus="status">
	                <input type="hidden" name="travel_id" value="${traImg.travel_id}">
	                <td hidden>${traImg.img_id}</td>
	                <td id="delImage${status.index}">
	                  <c:url value='/display' var='url'>
	                    <c:param name='file' value='${traImg.img_stored_file}'/>
	                  </c:url>
	                  <img alt="#" src="${url}" width="100" height="100">
	                  <br>
	                  <a href="#" class="button" onclick="deleteImage(${traImg.travel_id},${traImg.img_id},${status.index})">사진삭제</a>
	                </td>
	              </c:forEach>
	              <tr></tr>
	            </td>
	          </tr>
	        </table>
	        
	      </td>
	    </tr>
	  </table>
	  
	  <br><br><br>
	  <hr>
	  <br><br>
	  
	  <div style="text-align:center;">
	    <button type="submit">확인</button>
	    <a href="javascript:window.history.back();"><button type="submit">취소</button></a>
	  </div>
  </form>
  
</body>
   	<c:import url="../fragments/footer.jsp"/>
</html>