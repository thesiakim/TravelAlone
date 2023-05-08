<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<script defer src="/js/mypageMemberProFile.js"></script>
	<link href="/css/list.css" rel="stylesheet" type="text/css">
	<link href="/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="profile-block">
<div class="img-block">
	<div>
		<img
				class="profile-pic"
				src="/display?file=${storedImgName}"
				alt="#"
		/>
	</div>
	<form enctype="multipart/form-data" id="form">
		<input  type="file" id="file" onchange="refresh(event)">
	</form>
	<div class="button-block">
		<button id="saveButton" onclick="saveButton()">수정</button>
		<button id="cancelButton" onclick="window.close()">취소</button>
	</div>
</div>
</div>
</body>

</html>