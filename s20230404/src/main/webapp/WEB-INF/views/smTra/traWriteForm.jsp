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
	<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
	<script defer src="/js/picture.js"></script>
</head>


<body>
  <div id="img_benner">
    <img src="img/travel-picture.png" alt="배너">
  </div>
  <h3>
    <img src="img/Tra.png" alt="여행지" width=250px height=250px>
  </h3>
  <h3>글 쓰기</h3>
<c:if test="${msg!=null}">
${msg}
</c:if>

  <form action="traWriteForm" method="post" name="frm" enctype="multipart/form-data">
    <table style="margin:auto;">
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
	    <td><input type="text" name="t_name" size="50"></td>
	  </tr>
	  
	  <tr>
	    <td>여행지 정보</td>
	    <td><textarea name="t_content" rows="10" cols="50"></textarea></td>
	  </tr>
	  
	  <tr>
	    <td>여행지 주소</td>
	    <td><input type="text" name="t_address" size="50"></td>
	  </tr>
	  
	  <tr>
	    <td>입장료</td>
	    <td><input type="text" name="t_fee" size="50"></td>
	  </tr>
	  
	  <tr>
	    <td>운영 시간</td>
	    <td><input type="text" name="t_hour" size="50"></td>
	  </tr>
	  
	  <tr>
	    <td>문의 전화</td>
	    <td><input type="text" name="t_call" size="50"></td>
	  </tr>
	  
	  <tr>
	    <td>주차장 여부</td>
	    <td><input type="text" name="t_parking" size="50"></td>
	  </tr>	  
	</table>
	
	<br><br>
	<hr>
	<br><br>
	
	<table style="margin:auto;">
	  <tr>
	    <td><font size="5">사진 첨부</font></td>
	  </tr>
	  <tr>
	    <td>
	      <br>
	      <font size="4">
	        <img alt="사진 추가하기" src="/images/traUpload/${savedName}">
	        <br>
	        <input type="file" name="file1" multiple="multiple">
	      </font>
	      <br><br>
	    </td>
	  </tr>
	</table>
	
	<br><br><br>
	<hr>
	<br><br>
	
    <div style="text-align: center;">
      <button type="submit">확인</button>
      <a href="tra">목록 보기</a>
    </div>
  </form>
  
</body>
   	<c:import url="../fragments/footer.jsp"/>
</html>