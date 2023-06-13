<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<head>
    <title>Insert title here</title>
    <meta charset="UTF-8">
    <link href="<%=contextPath%>/css/main.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 헤더 -->
</head>

<body>
  <div id="header">
	<div class="container">
	  <div id="container-left clearfix">
		<div class="logo">
		  <a href="/"><img src="<%=contextPath%>/img/Logo.png"></a>
		</div>
		<c:choose>
	    	<c:when test="${empty user}">
		        <div class="headerLogin">
		            <a href="<%=contextPath%>/login">로그인</a>
		        </div> 
		        <div class="headerLogin">
		            <a href="<%=contextPath%>/join">회원가입</a>
		        </div>
				<div class="headerLogin">Join Us</div>
		    </c:when>
		    <c:otherwise>
		        <div class="headerLogin">
		            <a href="<%=contextPath%>/mypage">마이페이지</a>
		        </div> 
		        <div class="headerLogin">
		            <a href="<%=contextPath%>/logout" onclick="logout()">로그아웃</a>
		        </div>
				<div class="headerLogin">${user.nickname}님 안녕하세요!</div>
		    </c:otherwise>
		</c:choose>
		
	  </div>
	</div>
	<div class="headerForm">
	  <div class="headerBenner">
		<ul>
		<li><a href="<%=contextPath%>/tra">여행지</a>
		  <ul>
			<li><a href="<%=contextPath%>/traCodeFilter?code=tra100">관 광</a></li>
			<li><a href="<%=contextPath%>/traCodeFilter?code=tra200">자 연</a></li>
			<li><a href="<%=contextPath%>/traCodeFilter?code=tra300">레 저</a></li>
			<li><a href="<%=contextPath%>/traCodeFilter?code=tra400">쇼 핑</a></li>
		  </ul>
		</li>
		<li><a href="<%=contextPath%>/hou">숙소</a>
		  <ul>
			<li><a href="<%=contextPath%>/houseCodeFilter?code=hou100">호 텔</a></li>
          	<li><a href="<%=contextPath%>/houseCodeFilter?code=hou200">모 텔</a></li>
          	<li><a href="<%=contextPath%>/houseCodeFilter?code=hou300">민 박 / 팬 션</a></li>
          	<li><a href="<%=contextPath%>/houseCodeFilter?code=hou400">캠 핑 / 글랭핑</a></li>
          	<li><a href="<%=contextPath%>/houseCodeFilter?code=hou500">게스트 하우스</a></li>
          </ul>
		</li>
		<li><a href="<%=contextPath%>/res">맛집</a>
		  <ul>
			  <li><a href="<%=contextPath%>/restaurantCodeFilter?code=res100">한 식</a></li>
			  <li><a href="<%=contextPath%>/restaurantCodeFilter?code=res200">중 식</a></li>
			  <li><a href="<%=contextPath%>/restaurantCodeFilter?code=res300">일 식</a></li>
			  <li><a href="<%=contextPath%>/restaurantCodeFilter?code=res400">양 식</a></li>
			  <li><a href="<%=contextPath%>/restaurantCodeFilter?code=res500">카 페</a></li>
			  <li><a href="<%=contextPath%>/restaurantCodeFilter?code=res600">기 타</a></li>
          </ul>
		</li>
		<li><a href="<%=contextPath%>/listAllBoard">커뮤니티</a>
		  <ul>
			<li><a href="<%=contextPath%>/listBoard?b_common_board=bor100">자 유</a></li>
			<li><a href="<%=contextPath%>/listBoard?b_common_board=bor200">정 보</a></li>
			<li><a href="<%=contextPath%>/listBoard?b_common_board=bor300">질 문</a></li>
			<li><a href="<%=contextPath%>/listBoard?b_common_board=bor400">홍 보</a></li>
			<li><a href="<%=contextPath%>/listBoard?b_common_board=bor500">모 집</a></li>
		  </ul>
		</li>
		</ul>
	  </div>
	</div>
  </div> 
</body>
</html>