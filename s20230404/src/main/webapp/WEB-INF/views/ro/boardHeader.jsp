<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<link href="/css/list.css" rel="stylesheet" type="text/css">
	<!-- 이미지배너 -->
	<div id="img_benner">
		<img src="img/main-picture.png" alt="배너">
	</div>
	
	<h1><a href="listAllBoard">커뮤니티</a></h1>
	
	<div id="comunity">
		<ul>
			<li><a href="/listBoard?b_common_board=bor100">자유게시판</a></li>
			<li><a href="/listBoard?b_common_board=bor200">정보게시판</a></li>
			<li><a href="/listBoard?b_common_board=bor300">질문게시판</a></li>
			<li><a href="/listBoard?b_common_board=bor400">홍보게시판</a></li>			
			<li><a href="/listBoard?b_common_board=bor500">모집게시판</a></li>	
		</ul>
	</div>
</body>
</html>