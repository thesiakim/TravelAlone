<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
  <!-- 헤더 -->
  <div class="main">
	<div id="header">
	  <div class="container">
		<div id="container-left clearfix">
		  <div class="logo">
			<a href="/"><img src="/img/gosunee.png"></a>
		  </div>
		  <div class="headerLogin">
			<a href="/login">로그인</a>
		  </div> 
		  <div class="headerLogin">
			<a href="/join">회원가입</a>
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
 	<!-- 본문 -->
	<div id="content">
	  <!-- 이미지배너 -->
	  <div id="img_benner">
		<img src="img/main-picture.png" alt="배너">
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
	  	<div id="search-autocomplete">
	  		<ul id="autocomplete-results"></ul>
	  	</div>
	  </form>
	  
	  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	  <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
	  <script>
	  	/* 자동 완성 */
		  $(function(){
			  var $searchId = $("#searchId");
			  var $autocompleteResults = $("#autocomplete-results");
	
			  $searchId.autocomplete({
			    source: function(request, response){
			      $.ajax({
			        url: "/autocomplete",
			        dataType: "json",
			        data:{
			          keyword: request.term,
			          category: $("#category").val()
			        },
			        success: function(data){
			          $autocompleteResults.empty();
			          $.each(data, function(index, value) {
			            $autocompleteResults.append("<li>" + value + "</li>");
			          });
			          $autocompleteResults.show();
			        }
			      });
			    },
			    minLength: 1
			  });
	
			  //검색창에서 키보드를 떼는 순간마다 자동완성 결과 숨김
			  $searchId.on("keyup", function() {
			    if ($searchId.val() === '') {
			      $autocompleteResults.hide();
			    }
			  });
			});
	  </script>
	  
	  
	  <hr>
	  <!-- 인기명소 -->
	  <div id="session1">
		<h2>인기명소</h2>
	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
		<!-- 클래스명은 변경하면 안 됨 -->
		<div class="swiper-container">
		  <div class="swiper-wrapper">
		  	<c:forEach var="travel" items="${popularTravel}">
            	<div class="swiper-slide">
                	<a href="/traDetail?tid=${travel.travel_id}">
                	<img src="images1.png" alt="<c:out value='${travel.t_name}'/>"></a>
                </div>
            </c:forEach>
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
		<!-- 인기 숙소  -->
		<div id="session2">
		  <h2>인기숙소</h2>      
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
		<!-- 클래스명은 변경하면 안 됨 -->
		<div class="swiper-container">
		  <div class="swiper-wrapper">
		  	<c:forEach var="house" items="${popularHouse}">
            	<div class="swiper-slide">
                	<a href="/houDetail?hid=${house.house_id}">
                	<img src="images1.png" alt="<c:out value='${house.h_name}'/>"></a>
                </div>
            </c:forEach>
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
		<!-- 인기맛집 -->
		<div id="session3">
		  <h2>인기맛집</h2>      
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
		<!-- 클래스명은 변경하면 안 됨 -->
		<div class="swiper-container">
		  <div class="swiper-wrapper">
		  	<c:forEach var="res" items="${popularRes}">
            	<div class="swiper-slide">
                	<a href="/resDetail?rid=${res.restaurant_id}">
                	<img src="images1.png" alt="<c:out value='${res.r_name}'/>"></a>
                </div>
            </c:forEach>
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
		<form>
		  <div id="container-left clearfix">
			<div class="footerForm">
			  <a href="notice">고객센터</a>
			</div>
		  <div class="footerInfo">
			<p>서울시 이대 중앙학원 501호</p><br>
			<p>모든 저작권은 중앙정보처리 회사에 있습니다</p><br>
			<p>010-1234-5678</p>
		  </div>
		</div>
	  </form>
	</div>
		</div>
	  </div>
	</div>
  </div>
</body>
</html>