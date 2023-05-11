<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../fragments/header.jsp"%>
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
	
	
	
 	<div>
		<h1>고객센터</h1>
			
			<div >
				 <a href="notice">공지사항 </a>
				  <a href="faq" style=" padding-left:50px;">자주 묻는 질문 </a>
				  <a href="inquire" style=" padding-left:50px;">문의하기</a>
			</div>			
						
		</div>

	<hr>
	

	<c:if test="${msg!=null}">${msg}</c:if>
	<form action="inquireWriteForm" method="post" name="frm"  enctype="multipart/form-data">
		<table style="margin:auto;">
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="g_title" size = "50"> </td>
			</tr>
			<tr>
				<td> 문의글 종류 </td>
				<td style="text-align: left;"> 

				<select name="g_common_csboard">
					<option value="inq100">여행지문의</option>
					<option value="inq200">숙소문의</option>
					<option value="inq300">맛집문의</option>
					<option value="inq400">기타문의</option>
				</select>
				
				</td>
			</tr>

			<tr>
				<td> 비밀번호 </td>
				
				<td style="text-align: left;"> <input type="text" name="g_passwd" size="10" pattern="[0-9]{4}" title="숫자 4자리만 입력하세요"
				 placeholder="숫자 4자리 입력"
				> </td>
			</tr>
								
			<tr>
				<td>내용</td>
				<td><textarea name="g_content" rows="10" cols="50"></textarea></td>
			</tr>
			
			<tr>
				<td> 
				   <img alt="사진추가" src="/images/inquireUpload/${savedName}">
						<input type="file" name="file1" multiple="multiple"> <p>						
				</td>						
			</tr>
			
			
			
			
			<tr >
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; 
				<a href="inquire">목록보기</a></td>
			</tr>
		</table>
	</form>
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>