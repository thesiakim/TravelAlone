<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="img_benner">
		<img src="img/main-picture.png" alt="배너">
	</div>
<h1>맛집글 작성	</h1>
	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="resWriteForm" method="post" name="frm" enctype="multipart/form-data">
		<table style="margin:auto;">
			<tr>
				<td > 맛집 종류</td>
				<td style="text-align: left;"> 
				<select name="r_common_restaurant">
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
				<td> 맛집 지역</td>	
				<td style="text-align: left;">
					<select name="r_common_loc">
						<option value="loc102">서울</option>
						<option value="loc131">경기</option>
						<option value="loc132">인천</option>
						<option value="loc133">강원</option>
						<option value="loc141">충남</option>
						<option value="loc142">대전</option>
						<option value="loc143">충북</option>
						<option value="loc151">부산</option>
						<option value="loc152">울산</option>
						<option value="loc153">대구</option>
						<option value="loc154">경북</option>
						<option value="loc155">경남</option>
						<option value="loc161">전남</option>
						<option value="loc162">광주</option>
						<option value="loc163">전북</option>
						<option value="loc164">제주</option>										
					</select>									
				 </td>															
		  	</tr>
		
			<tr>
				<td> 맛집명</td>
				<td> <input type="text" name="r_name" size = "50"> </td>
			</tr>
				<tr>
				<td>소개글</td>
				<td><textarea name="r_content" rows="10" cols="50"></textarea></td>
			</tr>
			
			<tr>
				<td> 맛집주소 </td>
				<td> <input type="text" name="r_address" size = "50"> </td>
			</tr>
			<tr>
				<td> 메뉴 </td>
				<td> <input type="text" name="r_menu" size = "50"> </td>
			</tr>
			<tr>
				<td> 운영시간 </td>
				<td> <input type="text" name="r_hour" size = "50"> </td>
			</tr>
			
			<tr>
				<td>문의전화 </td>
				<td> <input type="text" name="r_call" size = "50"> </td>
			</tr>
			
			<tr>
				<td>주차장여부 </td>
				<td> <input type="text" name="r_parking" size = "50"> </td>
			</tr>
			
			<tr>
				<td> 
 				   UpLoad Image : <img alt="UpLoad Image" src="${pageContext.request.contextPath}/restaurantUpload/${savedName}">
						<input type="file" name="file1" multiple="multiple"> <p>						
				</td>						
			</tr>				
		
			<tr >
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; 
				<a href="res">목록보기</a></td>
			</tr>
		</table>
	</form>
</body>
<c:import url="footer.jsp"/>
</html>