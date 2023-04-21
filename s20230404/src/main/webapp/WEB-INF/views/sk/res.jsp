<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
  <!-- 헤더 -->
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
	</div>

	<!-- 본문 -->

	<div>

		<h1>맛집 메인화면</h1>
		<div>맛집갯수 ${totalRes}</div>

		<div>
			<a href="kfood"> 한식 </a> <a href="cfood"> 중식 </a>
			<a href="jfood"> 일식 </a> <a href="wfood">양식 </a>
			<a href="cafe"> 카페 </a> <a href="etc">기타 </a>
		</div>

	</div>
	
	<!-- 찐본문 -->

			<table style="margin:auto;">
				<tr>
					<td hidden>번호</td>													
					<td>지역</td>
					<td>맛집명</td>
					<td>평점</td>
					<td>리뷰수</td>
					<td>조회수</td>

				</tr>
				<c:forEach items="${resList}" var="res">
					<tr>
						<td hidden>${res.restaurant_id}</td>
						
						<td>${res.r_common_loc}</td>
						<td> <a href="resDetail?rid=${res.restaurant_id}">${res.r_name}</a></td>
						<td>★테스트★</td>
						<td>테스트1</td>
						<td>테스트11</td>						

				</c:forEach>
				<tr>
					<td colspan="5"><a href="resWriteForm">글작성</a></td>
				</tr>
			</table>
</body>
</html>