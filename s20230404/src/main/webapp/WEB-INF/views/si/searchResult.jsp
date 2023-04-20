<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="css/main.css" rel="stylesheet" type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
  <div class="main">
	<div id="header">
	  <div class="container">
		<div id="container-left clearfix">
		  <div class="logo">
			<a href="mainPage"><img src="img/로고.png"></a>
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
			<li><a href="#">여행지</a>
			  <ul>
				<li><a href="#">관 광</a></li>
                <li><a href="#">자 연</a></li>
                <li><a href="#">레 저</a></li>
                <li><a href="#">쇼 핑</a></li>
			  </ul>
            </li>
            <li><a href="#">숙소</a>
      		  <ul>
              	<li><a href="#">호 텔</a></li>
              	<li><a href="#">모 텔</a></li>
              	<li><a href="#">팬 션</a></li>
              	<li><a href="#">캠 핑</a></li>
              	<li><a href="#">게스트 하우스</a></li>
			  </ul>
			</li>
			<li><a href="#">맛집</a>
			  <ul>
			   <li><a href="#">한 식</a></li>
			   <li><a href="#">중 식</a></li>
			   <li><a href="#">일 식</a></li>
			   <li><a href="#">양 식</a></li>
			   <li><a href="#">카 페</a></li>
			   <li><a href="#">기 타</a></li>
			  </ul>
			</li>
            <li><a href="#">커뮤니티</a>
			  <ul>
			   <li><a href="#">자 유</a></li>
			   <li><a href="#">정 보</a></li>
			   <li><a href="#">질 문</a></li>
			   <li><a href="#">홍 보</a></li>
			   <li><a href="#">모 집</a></li>
			  </ul>
			</li>
		  </ul>
		</div>
	  </div>
	</div>
 	<!-- 본문 -->
	<div id="content">
	  <!-- 이미지배너 -->
	  <div id="img_benner">
		<img src="img/메인배경.png" alt="배너">
	  </div>
	  <!-- 검색창 -->
	  <form action="search">
	  	<select id="category" name="category">
	  		<option value="category_total">전체</option>
	  		<option value="category_travel">여행지</option>
	  		<option value="category_house">숙소</option>
	  		<option value="category_res">맛집</option>
	  		<option value="category_comm">커뮤니티</option> 
	  	</select>
	  	<div id="serch">
			<input type="text" placeholder="검색어를 입력해주세요" name="searchName" id="searchId">
	  	</div>
	  </form>
	  <hr>	
	  
	  <c:if test="${resultList.getHouseList().size()>=1}">
	  	<c:forEach items="${resultList.houseList}" var="house">
      <tr>
        <td><c:out value="${house.h_name}" /></td>
        <td><c:out value="${house.h_content}" /></td>
      </tr>
    </c:forEach>
	  </c:if>
	  
	   <c:if test="${resultList.getTravelList().size()>=1}">
	  	<c:set var="travelList" value="${resultList.getTravelList()}"></c:set>
	  </c:if>
	  
	   <c:if test="${resultList.getRestaurantlList().size()>=1}">
	  	<c:set var="resList" value="${resultList.getRestaurantlList()}"></c:set>
	  </c:if>
	  
	  <c:if test="${resultList.getBoardlList().size()>=1}">
	  	<c:set var="boardList" value="${resultList.getBoardlList()}"></c:set>
	  </c:if>
	  
	  
	  
	  
	  
</body>
</html>