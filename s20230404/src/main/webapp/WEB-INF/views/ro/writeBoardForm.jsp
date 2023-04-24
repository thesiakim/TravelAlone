<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:import url="header.jsp"/>
</head>
	<link href="/css/write.css" rel="stylesheet" type="text/css">
<body>
	<c:import url="boardHeader.jsp"/>
	
	<hr>
	
	<c:if test="${msg != null }">${msg }</c:if>
		
		<form action="writeBoard" method="post" onsubmit="return chkWrite()">
			<input type="hidden" name="b_common_board" id="common_board" value="${board.b_common_board }">
			<input type="hidden" name="member_id" value="3">
			<table>
		        <tr>
		        	<td colspan="2">
		        		<input type="text" id="title" name="b_title" required="required" placeholder="제목">
		        	</td>
		        </tr>
		        <tr>
		        	<td colspan="2">
		        		<textarea id="content" name="b_content" rows="10" cols="30" required="required">내용을 작성해 주세요</textarea>
		        	</td>
		        </tr>
		        <tr>
					<td style="width: 100px;">
						<label for="title">첨부파일</label>
					</td>
					<td>
						<input type="file" name="files" value="파일 선택">
					</td>
				</tr>
				<tr>
					<td>
						<label for="title">첨부파일</label>
					</td>
					<td>
						<input type="reset" value="파일 선택">
					</td>
				</tr>
		        <tr>
					<td colspan="2" id="writeFormSubmit">
						<input type="submit" value="작성">
						<input type="reset" value="취소">
					</td>
				</tr>
		    </table>
		</form>
		
		<hr>
		
</body>
	<c:import url="footer.jsp"/>
</html>