<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
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
<h1>숙소글 작성	</h1>
	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="houWriteForm" method="post" name="frm" encType = "multipart/form-data">
		<table style="margin:auto;">
			<tr>
				<td > 숙소종류</td>
				<td style="text-align: left;"> 
				<select name="h_common_house">
					<option value="hou100">호텔</option>
					<option value="hou100">모텔</option>
					<option value="hou300">민박/펜션</option>
					<option value="hou400">캠핑/글램핑</option>
					<option value="hou500">게스트하우스</option>									
				</select>
				
				
				
				</td>
				
				
													
				
			</tr>
				<tr>
				<td> 숙소지역</td>	
				<td style="text-align: left;">
					<select name="h_common_loc">
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
						<option value="loc163">전죽</option>
						<option value="loc164">제주</option>
											
				</select>
				
				
				 </td>			
	
				
				
				
			</tr>
		
			<tr>
				<td> 숙소명</td>
				<td> <input type="text" name="h_name" size = "50"> </td>
			</tr>
				<tr>
				<td>소개글</td>
				<td><textarea name="h_content" rows="10" cols="50"></textarea></td>
			</tr>
			
			<tr>
				<td> 숙소주소 </td>
				<td> <input type="text" name="h_address" size = "50"> </td>
			</tr>
			<tr>
				<td> 객실가격 </td>
				<td> <input type="text" name="h_room" size = "50"> </td>
			</tr>
			<tr>
				<td>체크인 체크아웃시간 </td>
				<td> <input type="text" name="h_checkinout" size = "50"> </td>
			</tr>
			
			<tr>
				<td>문의전화 </td>
				<td> <input type="text" name="h_call" size = "50"> </td>
			</tr>
			
			
			<tr>
				<td>조식여부 </td>
				<td> <input type="text" name="h_eat" size = "50"> </td>
			</tr>
			
			<tr>
				<td>주차장여부 </td>
				<td> <input type="text" name="h_parking" size = "50"> </td>
			</tr>
				
				<tr>
				<td>
					<label for="title">첨부파일</label>
				</td>
				<td>
					<td><input type="file" name="fileName"></td>

			</tr>			
				
								
		
			<tr >
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; 
				<a href="hou">목록보기</a></td>
			</tr>
		</table>
	</form>



</body>
<c:import url="footer.jsp"/>
</html>