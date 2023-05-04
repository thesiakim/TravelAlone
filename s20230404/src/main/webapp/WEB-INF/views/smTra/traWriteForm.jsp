<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  </head>
	<link href="/css/write.css" rel="stylesheet" type="text/css">
	<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
	<script defer src="/js/picture.js"></script>
<body>
<div id="img_banner">
		<img src="img/main-picture.png" alt="배너">
	</div>
<h1>여행지글 작성	</h1>
	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="traWriteForm" method="post" name="frm" enctype="multipart/form-data">
		<table style="margin:auto;">
			<tr>
				<td> 여행지종류</td>
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
				<td> 여행지지역</td>
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
				<td> 여행지명</td>
				<td> <input type="text" name="t_name" size = "50"> </td>
			</tr>
				<tr>
				<td> 여행지정보</td>
				<td><textarea name="t_content" rows="10" cols="50"></textarea></td>
			</tr>
			
			<tr>
				<td> 여행지주소 </td>
				<td> <input type="text" name="t_address" size = "50"> </td>
			</tr>
			<tr>
				<td> 입장료 </td>
				<td> <input type="text" name="t_fee" size = "50"> </td>
			</tr>
			<tr>
				<td> 운영시간 </td>
				<td> <input type="text" name="t_hour" size = "50"> </td>
			</tr>
			
			<tr>
				<td>문의전화 </td>
				<td> <input type="text" name="t_call" size = "50"> </td>
			</tr>
			
			<tr>
				<td>주차장여부 </td>
				<td> <input type="text" name="t_parking" size = "50"> </td>
			</tr>
				
			
			<tr>
				<td> 
				   <img alt="사진추가 " src="/images/traUpload/${savedName}">
						<input type="file" name="file1" multiple="multiple"> <p>						
				</td>						
			</tr>			
				
								
		
			<tr >
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; 
				<a href="tra">목록보기</a></td>
			</tr>
		</table>
	</form>



</body>
<c:import url="footer.jsp"/>
</html>