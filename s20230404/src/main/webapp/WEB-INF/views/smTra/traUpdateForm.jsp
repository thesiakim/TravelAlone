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
	<h1>여행지 글업데이트	</h1>
		<form action="traUpdate" method="post">
		<input type="hidden" name="travel_id" value="${travel.travel_id }">
		<table>
											
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
				<th>여행지명</th>
				<td><input type="text" name="t_name"
					required="required" value="${travel.t_name }"></td>
			</tr>
			<tr>
				<th>여행지정보</th>
				<td><input type="text" name="t_content"
					required="required" value="${travel.t_content }"></td>
			</tr>
			<tr>
				<th>여행지주소</th>
				<td><input type="text" name="t_address"
					required="required" value="${travel.t_address }"></td>
			</tr>
			<tr>
				<th>입장료</th>
				<td><input type="text" name="t_fee"
					required="required" value="${travel.t_fee }"></td>
			</tr>
			<tr>
				<th>운영시간</th>
				<td><input type="text" name="t_hour"
					required="required" value="${travel.t_hour }"></td>
			</tr>
			<tr>
				<th>문의전화</th>
				<td><input type="text" name="t_call"
					required="required" value="${travel.t_call }"></td>
			</tr>
			<tr>
				<th>주차장여부</th>
				<td><input type="text" name="t_parking"
					required="required" value="${travel.t_parking }"></td>
			</tr>
				<tr>
   				<th>첨부파일</th>
    <td>
        <input type="file" name="file1" accept="image/*">
        <input type="hidden" name="traImgId" value="${traImg.img_id}">
        <input type="hidden" name="traImgStoredFile" value="${traImg.img_stored_file}">
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