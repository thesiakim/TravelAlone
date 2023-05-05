<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 
<script type="text/javascript">
    /* 검색어 입력 필드에서 Enter키 입력 시 검색 수행 */
	document.getElementById('searchId').addEventListener('keyup', function(event) {
   		 if (event.code === 'Enter')
    	{
        	event.preventDefault();
        	document.querySelector('form').submit();
    	}
	});
	
	/* 카테고리 선택에서 Enter키 입력 시 검색 수행 */
	document.getElementById('category').addEventListener('keyup', function(event) {
  		 if (event.code === 'Enter')
     	{
         	event.preventDefault();
         	document.querySelector('form').submit();
     	}
 	});
</script>
</head>
    <link href="css/main.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<body>

	<header>
		<div class="container">
	  	<div id="container-left clearfix">
		<div class="logo">
		  <a href="/"><img src="img/gosunee.png"></a>
		</div>
		<div class="headerLogin">
		  <a href="login">로그인</a>
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
			<li><a href="#">한 식</a></li>
			<li><a href="#">중 식</a></li>
			<li><a href="#">일 식</a></li>
			<li><a href="#">양 식</a></li>
			<li><a href="#">카 페</a></li>
			<li><a href="#">기 타</a></li>
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
	  
	  <div id="img_benner">
		<img src="img/main-picture.png" alt="배너">
	</div>
	
	  	  <!-- 검색창 -->
	  <form action="search">
	  	<select id="category" name="category">
  			<option value="category_total" ${category == "category_total" ? "selected" : ""}>전체</option>
  			<option value="category_travel" ${category == "category_travel" ? "selected" : ""}>여행지</option>
  			<option value="category_house" ${category == "category_house" ? "selected" : ""}>숙소</option>
  			<option value="category_res" ${category == "category_res" ? "selected" : ""}>맛집</option>
  			<option value="category_comm" ${category == "category_comm" ? "selected" : ""}>커뮤니티</option>
		</select>
	  	<div id="serch">
			<input type="text" name="searchName" value="${keyword }" id="searchId">
	  	</div>
	  </form>
	</div>
	</header>


</body>
</html>