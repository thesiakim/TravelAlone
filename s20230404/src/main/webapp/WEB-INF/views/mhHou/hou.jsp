<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- <%@ include file="header.jsp"%> --%>
    <%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
.swiper-container {
	width:1200px;
	height:600px;
	padding:30px 0;
	border:5px solid silver;
	border-radius:7px;
	box-shadow:0 0 20px #ccc inset;
}
.swiper-slide {
	text-align:center;
	display:flex; /* 내용을 중앙정렬 하기위해 flex 사용 */
	align-items:center; /* 위아래 기준 중앙정렬 */
	justify-content:center; /* 좌우 기준 중앙정렬 */
}
.swiper-slide img {
	box-shadow:0 0 5px #555;
}
  </style>

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

<body>
<div id="img_benner">
		<img src="img/house-picture.png" alt="배너">
	</div>
	
<h1><a href="hou"><img src="img/Hou.png" alt="숙소" width=250px height=250px></a></h1>

<!-- 숙소종류 -->
	<div >  																						
		<c:forEach items="${boardList}" var="list">
					<td>
							<!-- 컨트롤러로 보내는거 -->
						<a href="houseCodeFilter?code=${list.code}">${list.value}</a>									
					</td>
		</c:forEach>																
			   <c:set var="num" value="${page.total-page.start+1 }"></c:set>
	</div>				
	
	
	
	
	
<!--검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 검색창 -->	
	<form action="houseSearch">
		<select id="category" name="search">
			<option value="s_title">제목</option>
			<option value="s_content">내용</option>
			
		</select> 
		 <div id="serch">
		<input type="text" name="keyword" placeholder="검색어를 입력하세요" value="${search}" id="searchId">
		
		  <%--  <a href="houseSearch?search=${search}&amp;keyword=${keyword}">검색</a> --%>
		</div>
		<p>
	</form>

	<c:set var="num" value="${page.total-page.start+1 }"></c:set>
	
	
<!-- 지역 종류 지역 종류  지역 종류  지역 종류  지역 종류  지역 종류  -->
	<c:forEach items="${boardLocList}" var="list">
		<td>
			<!-- 컨트롤러로 보내는거 -->
			<a href="houLocCodeFilter?code=${list.code}">${list.value}</a> 
				
		</td>
	</c:forEach>
	
	



	<!-- 국내 인기 숙소  -->
		<div id="session2">
		  <h3>국내 인기 숙소</h3>    
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
		<!-- 클래스명은 변경하면 안 됨 -->
		<div class="swiper-container">
		  <div class="swiper-wrapper">
			<div class="swiper-slide"><img src="image1.png" alt="이미지 1"></div>
			<div class="swiper-slide"><img src="image2.jpg" alt="이미지 2"></div>
			<div class="swiper-slide"><img src="image3.jpg" alt="이미지 3"></div>
			<div class="swiper-slide"><img src="image4.jpg" alt="이미지 4"></div>
			<div class="swiper-slide"><img src="image5.jpg" alt="이미지 5"></div>
			<div class="swiper-slide"><img src="image6.jpg" alt="이미지 6"></div>
		  </div>
	  
		  <!-- 네비게이션 -->
		  <div class="swiper-button-next"></div> <!-- 다음 버튼 (오른쪽에 있는 버튼) -->
		  <div class="swiper-button-prev"></div> <!-- 이전 버튼 -->
		  <!-- 페이징 -->
		  <div class="swiper-pagination"></div>
		</div>
		<script>
		  new Swiper('.swiper-container', {
			slidesPerView : 3, // 동시에 보여줄 슬라이드 갯수
			spaceBetween : 20, // 슬라이드간 간격
			slidesPerGroup : 3, // 그룹으로 묶을 수, slidesPerView 와 같은 값을 지정하는게 좋음
			// 그룹수가 맞지 않을 경우 빈칸으로 메우기
			// 3개가 나와야 되는데 1개만 있다면 2개는 빈칸으로 채워서 3개를 만듬
			loopFillGroupWithBlank : true,
			loop : true, // 무한 반복
			pagination : { // 페이징
			  el : '.swiper-pagination',
			  clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
			},
			navigation : { // 네비게이션
			  nextEl : '.swiper-button-next', // 다음 버튼 클래스명
			  prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
			},
		  });
		</script>







			<hr>		
	<!-- 숙소리스트 테이블  숙소리스트 테이블  숙소리스트 테이블  숙소리스트 테이블  숙소리스트 테이블-->

	<table style="margin:auto;">
		<tr>
			<td hidden>번호</td>													
			<td>지역</td>
			<td>숙소명</td>
			<td>평점</td>
			<td>리뷰수</td>			
		</tr>
		<c:forEach items="${houseList}" var="house">
			<tr>
				<td hidden>${house.house_id}</td>
			 	<td>${house.h_common_loc}</td>
				<td style=" padding-left:50px;"> <a href="houDetail?hid=${house.house_id}">${house.h_name}</a></td>
				<td style="color: #ffd700;">
					<c:choose>
						<c:when test="${house.h_avgscore eq '1'}">
							<c:out value="★☆☆☆☆"/>
						</c:when>
						<c:when test="${house.h_avgscore eq '2'}">
							<c:out value="★★☆☆☆"/>
						</c:when>
						<c:when test="${house.h_avgscore eq '3'}">
							<c:out value="★★★☆☆"/>
						</c:when>
						<c:when test="${house.h_avgscore eq '4'}">
							<c:out value="★★★★☆"/>
						</c:when>
						<c:when test="${house.h_avgscore eq '5'}">
							<c:out value="★★★★★"/>
						</c:when>
						<c:otherwise>
							<c:out value="☆☆☆☆☆"/>
						</c:otherwise>
					</c:choose>												
				</td>																				
				<td>${house.h_revcount}</td>							
		</c:forEach>
		
	</table>
	<hr>

		<a style="text-align: right;" href="houWriteForm">글작성</a>
<!-- 페이징 처리 페이징 처리  페이징 처리  페이징 처리   -->

 <div>
	<c:if test="${page.startPage > page.pageBlock }">
		<a href="hou?currentPage=${page.startPage-page.pageBlock}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
		<a
			href="hou?currentPage=${i}">[${i}]</a>
	</c:forEach>
	<c:if test="${page.endPage < page.totalPage }">
		<a
			href="hou?currentPage=${page.startPage+page.pageBlock}">[다음]</a>
	</c:if>
</div> 






</body>
	<c:import url="footer.jsp"/>
</html>