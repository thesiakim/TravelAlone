<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<script defer src="<%=contextPath%>/js/mypageMemberProFile.js"></script>
	<link href="<%=contextPath%>/css/login.css" rel="stylesheet" type="text/css">
	<link href="<%=contextPath%>/css/list.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="profile-block">
	<div class="img-block">
	
		<table>
		<tr>
			<td>
			<c:url value='/display' var='url'><c:param name='file' value='${storedImgName}'/></c:url>
			<img class="profile-pic" alt="#" src="${url}" width="300px" height="300px">	
			</td>
		</tr>
		
		<tr>
			<td>
			<form enctype="multipart/form-data" id="form">
				<input  type="file" id="file" onchange="refresh(event)">
			</form>
			</td>
		</tr>
		
		<tr>
			<td>
			<div class="button-block">
				<button id="saveButton" onclick="saveButton()">수정</button>
				<button id="cancelButton" onclick="window.close()">취소</button>
			</div>
			</td>
		</tr>
		</table>
		
	</div>
	</div>
</body>
</html>