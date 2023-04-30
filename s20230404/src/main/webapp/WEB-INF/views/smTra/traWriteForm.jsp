<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  </head>
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
						<option value="tra102">서울</option>
						<option value="tra132">인천</option>
						<option value="tra131">경기</option>
						<option value="tra133">강원</option>
						<option value="tra142">대전</option>
						<option value="tra141">충남</option>
						<option value="tra143">충북</option>
						<option value="tra151">부산</option>
						<option value="tra152">울산</option>
						<option value="tra153">대구</option>
						<option value="tra155">경남</option>
						<option value="tra154">경북</option>
						<option value="tra162">광주</option>
						<option value="tra161">전남</option>
						<option value="tra163">전북</option>
						<option value="tra164">제주</option>								
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
				<td>첨부파일</td>
				<td><input type="file" name="file1" multiple="multiple"> <img alt="UpLoad Image" src="${pageContext.request.contextPath}/traUpload/${savedName}"></td>
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