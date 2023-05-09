<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <title>Insert title here</title>
    <meta charset="UTF-8">
    <link href="css/main.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 헤더 -->
</head>
<body>
  <div id="header">
	<div class="container">
	  <div id="container-left clearfix">
		<div class="logo">
		  <a href="/"><img src="img/gosunee.png"></a>
		</div>
		<div class="headerLogin">
		  <a href="loginPage.html">로그인</a>
		</div> 
		<div class="headerLogin">
		  <a href="">회원가입</a>
		</div> 
	  </div>
	</div>
	<div class="headerForm">
	  <div class="headerBenner">
		<ul>
				<li><a href="tra">여행지</a>
		  <ul>
			<li><a href="traFilter?code=tra100">관 광</a></li>
               <li><a href="traFilter?code=tra200">자 연</a></li>
               <li><a href="traFilter?code=tra300">레 저</a></li>
               <li><a href="traFilter?code=tra400">쇼 핑</a></li>
		  </ul>
        </li>
	    <li><a href="hou">숙소</a>
      		  <ul>
              	<li><a href="houseCodeFilter?code=hou100">호 텔</a></li>
              	<li><a href="houseCodeFilter?code=hou200">모 텔</a></li>
              	<li><a href="houseCodeFilter?code=hou300">팬 션</a></li>
              	<li><a href="houseCodeFilter?code=hou400">캠 핑</a></li>
              	<li><a href="houseCodeFilter?code=hou500">게스트 하우스</a></li>
			  </ul>
			</li>
		<li><a href="res">맛집</a>
		  <ul>
			<li><a href="restaurantCodeFilter?code=res100">한 식</a></li>
			<li><a href="restaurantCodeFilter?code=res200">중 식</a></li>
			<li><a href="restaurantCodeFilter?code=res300">일 식</a></li>
			<li><a href="restaurantCodeFilter?code=res400">양 식</a></li>
			<li><a href="restaurantCodeFilter?code=res500">카 페</a></li>
			<li><a href="restaurantCodeFilter?code=res600">기 타</a></li>
          </ul>
		</li>
		<li><a href="/listAllBoard">커뮤니티</a>
		  <ul>
			<li><a href="/listBoard?b_common_board=bor100">자 유</a></li>
			<li><a href="/listBoard?b_common_board=bor200">정 보</a></li>
			<li><a href="/listBoard?b_common_board=bor300">질 문</a></li>
			<li><a href="/listBoard?b_common_board=bor400">홍 보</a></li>
			<li><a href="/listBoard?b_common_board=bor500">모 집</a></li>
		  </ul>
		</li>
		</ul>
	  </div>
	</div>
  </div> 
</body>
</html>