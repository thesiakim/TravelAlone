<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<form action=traUpdate" method="post">
		<input type="hidden" name="travel_id" value="${travel.travel_id }">
		<table>
											
			<tr>
				<th>여행지종류</th>
				<td><input type="text" name="t_common_travel"
					required="required" value="${travel.t_common_travel }"></td>
			</tr>	
			<tr>
				<th>여행지지역</th>
				<td><input type="text" name="t_common_loc"
					required="required" value="${travel.t_common_loc }"></td>
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
<%-- 			<tr>
				<th>첨부파일</th>
				<td> 
				   <img alt="UpLoad Image" src="${pageContext.request.contextPath}/traUpload/${savedName}">
	
						<input type="file" name="file1"> <p>						
				
				</td>
			
			
			</tr>	
			<tr>
				<th>첨부파일 1</th>
				<td><input type="file" name="file1"></td>
			</tr>
			<tr>
				<th>첨부파일 2</th>
				<td><input type="file" name="file2"></td>
			</tr>
			<tr>
				<th>첨부파일 3</th>
				<td><input type="file" name="file3"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="확인"></td>
			</tr> --%>
		</table>
		</form>
<!--실험이지로오오롤오롱로오로롱--!>		
	<h1>File Upload (파일 업로드)</h1>
<!-- action의 값이 endpoint를 바라봅니다., enctype은 멀티파트 옵션을 줌 -->
<!-- form 태그의 enctype 속성은 multipart/form-data로 세팅하여 브라우져가 파일 업로드 방식으로 동작하도록 설정 -->
<form action="<%= application.getContextPath() %>/fileUpload"  method="post" enctype="multipart/form-data">>
	<!-- input 태그 file 속성으로 작성하여 form 방식으로 파일 업로드를 할 수 있습니다.-->
	<input type="file"  name="file1" placeholder="파일 선택" /><br /> 
	<input type="file"  name="file2" placeholder="파일 선택" /><br /> 
	<input type="file"  name="file3" placeholder="파일 선택" /><br /> 
	<input type="submit" value="upload">
	</form>
	  
         <!--    파일 설명 : <input type="text" name="description"><br>
            파일1 : <input type="file" name="file1"><br>
            파일2 : <input type="file" name="file2"><br>
            <input type="submit" value="전송">
      </form> -->

<!-- <h1>Multi File Upload (다중 파일 업로드)</h1>
<form action="multi_fileUpload"  method="post" enctype="multipart/form-data">
	multiple 속성추가
    <input type="file" name="uploadfiles" placeholder="파일 선택" multiple/><br/>
    <input type="submit" value="upload">
</form> -->
	
<!-- 	  <form action="<%= application.getContextPath() %>/fileUpload"  method="post" enctype="multipart/form-data">
            파일 설명 : <input type="text" name="description"><br>
            파일1 : <input type="file" name="file1"><br>
            파일2 : <input type="file" name="file2"><br>
            <input type="submit" value="전송">
      </form> -->
	
</body>
</html>