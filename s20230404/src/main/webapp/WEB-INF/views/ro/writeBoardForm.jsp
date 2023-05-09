<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ include file="../fragments/header.jsp"%>
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
	<c:import url="boardHeader.jsp"/>
	
	<hr>
	
	<c:if test="${msg != null }">${msg }</c:if>

		<input type="hidden" name="b_common_board" id="common_board" value="${board.b_common_board}">
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
				<td>
					<label for="title">첨부파일</label>
				</td>
				<td>
					<form enctype="multipart/form-data" id="form">
						<input type="file" multiple="multiple" accept="image/*" class="multipleImage" id="files"/>
					</form>
				</td>
			</tr>

	        <tr>
				<td colspan="2" id="writeFormSubmit">
					<input type="submit" id="btn-save" value="작성">
					<input type="reset" value="취소">
				</td>
			</tr>
	    </table>
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>