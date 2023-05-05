<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">

<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>


<style type="text/css">

	/* 결과 안내 문장의 폰트 크기 조정  */
	h2 {
	  font-size: 80%;
	  font-family: 'IBM Plex Sans KR', sans-serif;
	}
	
	/* 결과 테이블 테두리 조정 */
	#search-table {
		border-collapse: collapse;
		font-family: 'IBM Plex Sans KR', sans-serif;
		
	}
	
	#search-table tr:not(:first-of-type) {
		border-top: 1px solid #ddd;
		border-bottom: 1px solid #ddd;
	}
	
	#search-table tr:not(:first-of-type) td:first-of-type,
	#search-table tr:not(:first-of-type) td:nth-of-type(2) {
		border-left: 1px solid #ddd;
	}
	
	#search-table tr:not(:first-of-type) td:last-of-type {
		border-right: 1px solid #ddd;
	}
	
	/* 결과 테이블 두 번째 tr 크기 조정 */
	#search-table tr:nth-child(2) {
		height: 70px;
	}
	
	/* 결과 테이블 두 번째 tr의 padding 조정 */
	#search-table tr:not(:first-of-type) {
		border-top: 1px solid #ddd;
		border-bottom: 1px solid #ddd;
	}
	
	#search-table tr:not(:first-of-type) td:first-of-type,
	#search-table tr:not(:first-of-type) td:nth-of-type(2) {
		border-left: 1px solid #ddd;
	}
	
	#search-table tr:not(:first-of-type) td:last-of-type {
		border-right: 1px solid #ddd;
	}
	
	#search-table tr:nth-of-type(2) td {
		padding: 20px;
	}
	
	/* 검색 결과 padding */
	#search-table tr:nth-of-type(2) td:first-of-type {
  		padding: 20.5px;
	}
	 	 
	/* 통합 검색 글씨 */
	#search-table tr:first-child td:first-child {
		font-size: 22px;
		color: #333333;
		font-family: 'IBM Plex Sans KR', sans-serif;
	}
	
	/* 총 ㅇㅇ건 */
	#search-td:last-of-type {
		font-size: 14px;
    	color: #666666;
    	font-family: 'IBM Plex Sans KR', sans-serif;
	}
	
	/* 검색 결과 없는 경우 안내문 */
	#no-search-result li {
		margin-left: 50;
		font-size: 16px; 
		color: #474747;
		text-align: left;
	}
	
	#no-search-result {
	    display: flex;
	    flex-direction: column;
	    align-items: center;
	}

	ul.no-search-list li {
  		margin-bottom: 5px;
	}



	
	
	
	/* #search-table td {
		list-style-image: url("../img/list.png");
	} */
	
	/* 인기 검색어  */
	 	
.popular-container {
  margin-bottom: 20px;
  margin-top: 10px;
  min-width: 320px; 
  max-width: 500px;
  
}
.popular-container p {
  margin: 0 0 20px; 
  line-height: 1.5;
  font-weight: 300;
}
.popular-container .content {
  background: #fff; 
  color: #373737;
   backface-visibility: hidden;
  overflow: hidden;
 
}
.popular-container .content > div {
  display: none;
  padding: 20px 25px 5px;
}
.popular-container input {
  display: none;
}
.popular-container label {
  display: inline-block; 
  padding: 15px 25px; 
  font-weight: 600; 
  text-align: center;
  border-bottom: 1px solid transparent;
  transition: all .3s ease-out 0.1s;
}
.popular-container label:hover {
  color: #fff; 
  cursor: pointer;
}
.popular-container input:checked + label {
  background: #ffffff;
  color: #1FAB89;
  border-bottom: 1px solid;
}

.popular-container #tab-1:checked ~ .content #content-1,
.popular-container #tab-2:checked ~ .content #content-2,
.popular-container #tab-3:checked ~ .content #content-3,
.popular-container #tab-4:checked ~ .content #content-4 {
  display: block;
  
}

@media screen and (max-width: 400px) { label {padding: 15px 10px;} }

@keyframes inUp {
  0%{
    opacity: 0;
    transform: translateY(100px)
  }
    90%{
    transform: translateY(-10px)
  }
  100% {
    opacity: 1;
    transform: translateY(0)
  }
}

.content li a {
  color: #333333;
  font-size: 14px; /* 글자 크기를 14px로 지정 */
  text-align: left; /* 왼쪽 정렬로 지정 */
}


.row div {
        line-height: 2.5em;
    }	 
	 
</style>


 <link href="/css/list.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="header.jsp" %>

	<section>
	
	
	<!-- 검색 결과 박스 -->
	<article id="container-search">
		<h2>검색어 <span style="color: rgb(31,171,137)">"<c:out value="${keyword}" />"</span>에 대해 <span style="color: rgb(31,171,137)">총 <c:out value="${count}" />건</span>의 결과를 찾았습니다.</h2>
			
		<c:choose>
			<c:when test="${not empty resultList.getTravelList() or not empty resultList.getHouseList() or not empty resultList.getRestaurantList() or not empty resultList.getBoardList()}">
				<div class="integration">
				<table id="search-table">
						<tr id="search-tr">
							<td id="search-td">통합 검색</td>
							<td colspan="3" id="search-td">총 <c:out value="${count}" />건 </td>
						</tr>
						<tr id="search-tr">
							<td id="search-td" style="background-color: #BCEAD5; padding: 20px; color: #F6F6F6;">검색 결과</td>
						
							<td id="search-td">
								<c:if test="${not empty resultList.getTravelList()}">
									<c:choose>
									    <c:when test="${category eq 'category_total'}">
									               여행지 (${travelCount})
									    </c:when>
									    <c:when test="${category eq 'category_travel'}">
									               여행지 (${count})
									    </c:when>
									</c:choose>
		 						</c:if>
		 						<c:if test="${empty resultList.getTravelList()}">
		    						<input type="hidden">
		  						</c:if>
		 					</td>
							<td id="search-td">
								<c:if test="${not empty resultList.getHouseList()}">
		 							<c:choose>
									    <c:when test="${category eq 'category_total'}">
									               숙소 (${houseCount})
									    </c:when>
									    <c:when test="${category eq 'category_house'}">
									               숙소 (${count})
									    </c:when>
									</c:choose>
		 						</c:if>
		 						<c:if test="${empty resultList.getHouseList()}">
							    	<input type="hidden">
							  	</c:if>
							</td>
							<td id="search-td">
								<c:if test="${not empty resultList.getRestaurantList()}">
		 							<c:choose>
									    <c:when test="${category eq 'category_total'}">
									               맛집 (${resCount})
									    </c:when>
									    <c:when test="${category eq 'category_res'}">
									               맛집 (${count})
									    </c:when>
									</c:choose>
		 						</c:if>
		 						<c:if test="${empty resultList.getRestaurantList()}">
							    	<input type="hidden">
							  	</c:if>
							</td>
							<td id="search-td">
								<c:if test="${not empty resultList.getBoardList()}">
		 							<c:choose>
									    <c:when test="${category eq 'category_total'}">
									               커뮤니티 (${boardCount})
									    </c:when>
									    <c:when test="${category eq 'category_comm'}">
									               커뮤니티 (${count})
									    </c:when>
									</c:choose>
		 						</c:if>
		 						<c:if test="${empty resultList.getBoardList()}">
							    	<input type="hidden">
							  	</c:if>
							</td>
							<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
						</tr>
		 		</table>	
				</div>
			</c:when>
		</c:choose>
	</article>
	
	<!-- 검색 결과 없음 -->
	<article id="no-search-result">
		<c:if test="${empty resultList.getTravelList() and empty resultList.getHouseList() and empty resultList.getRestaurantList() and empty resultList.getBoardList()}">
			<ul class="no-search-list">
				<li>단어의 철자가 단어의 철자가 정확한지 확인해 주시기 바랍니다.</li>
                <li>검색어의 단어 수를 줄이거나, 다른 검색어(유사어)로 검색해 보시기 바랍니다.</li>
                <li>일반적으로 많이 사용하는 검색어로 다시 검색해 주시기 바랍니다.</li>
			</ul>
	</c:if>
	</article>
	
	
	  <!-- 검색 결과 존재 -->
	  <!-- 카테고리를 전체로 설정하고 검색한 경우 -->
	  <c:if test="${category eq 'category_total'}">
	  
		  <article id="search-content-total">
			     <!-- 여행지 검색 결과 리스트 -->
			    <div class="travel-result">
			    	<ul><c:if test="${not empty resultList.getTravelList()}">
			    	<h1>여행지</h1>
			    	<br>
			    	
		  <div class="row" style=" padding-left:300px;">
				   
				        <c:forEach items="${resultList.travelList}" var="travel" varStatus="status">
				            <c:if test="${status.index < 5}">
				                <div class="col-md-3">
				                    <a href="/traDetail?tid=${travel.travel_id}">
				                        <c:url value="/display" var="url">
				                            <c:param name="file" value="${travel.img_stored_file}"></c:param>
				                        </c:url>
				                        <img src="${url}" alt="#" width="300" height="200">
				                    </a>
				                    <br>
				                    <c:out value="${travel.t_name}" />
				                </div>
				                <c:if test="${status.index % 3 == 2}"><div class="clearfix"></div></c:if>
				            </c:if>
				        </c:forEach>
				  
				</div>


			    	
			    	
			    	
			    	
			    	
			    <%-- 	<div class="row">
					    <c:forEach items="${resultList.travelList}" var="travel" varStatus="status">
					        <c:if test="${status.index < 5}">
					            <div class="col-md-4">
					                <a href="/traDetail?tid=${travel.travel_id}">
					                    <c:url value="/display" var="url" >
					                        <c:param name="file" value="${travel.img_stored_file }"></c:param>
					                    </c:url>
					                    <img src="${url }" alt="#" width="300" height="200">
					                </a>
					                <br>
					                <c:out value="${travel.t_name}" />
					               
					            </div>
					            <c:if test="${status.index % 3 == 2}"><div class="clearfix"></div></c:if>
					        </c:if>
					    </c:forEach>
					</div> --%>
			    	

			
			        <c:if test="${fn:length(resultList.travelList) > 5}">
			        	<a href="search?searchName=${keyword }&amp;category=category_travel">더보기</a>
			        </c:if>
				</c:if></ul>
				</div>
				
				<!-- 숙소 검색 결과 리스트 -->
				<div class="house-result">
			    	<ul><c:if test="${not empty resultList.getHouseList()}">
			    	<h1>숙소</h1>
			    	<c:forEach items="${resultList.houseList}" var="house" varStatus="status">
			        	<c:if test="${status.index < 5}">
			            	<a href="/houDetail?hid=${house.house_id}">
                			<c:url value="/display" var="url">
                				<c:param name="file" value="${house.img_stored_file }"></c:param>
                			</c:url>
                			<img src="${url }" alt="#" width="300" height="200"></a>
                		
                			<tr>
			                	<td><c:out value="${house.h_name}" /></td>
			                	<c:set var="content" value="${fn:substring(house.h_content, 0, 10)}"/>
			                		<c:if test="${fn:length(house.h_content) > 10}">
			                    		<c:set var="content" value="${content }..."/>
			             			</c:if>
			                	<td><c:out value="${content }"></c:out></td>
			            	</tr>
			            <c:if test="${not status.last }"><br/></c:if>
			        	</c:if>
			    	</c:forEach>
			  
			        <c:if test="${fn:length(resultList.houseList) > 5}">
			        	<a href="search?searchName=${keyword }&amp;category=category_house">더보기</a>
			        </c:if>
				</c:if></ul>
				</div>
				
				<!-- 맛집 검색 결과 리스트 -->
				<div class="res-result">
			    	<ul><c:if test="${not empty resultList.getRestaurantList()}">
			    	<h1>맛집</h1>
			    	<c:forEach items="${resultList.restaurantList}" var="res" varStatus="status">
			        	<c:if test="${status.index < 5}">
			            	<a href="/resDetail?rid=${res.restaurant_id}">
                			<c:url value="/display" var="url">
                				<c:param name="file" value="${res.img_stored_file }"></c:param>
                			</c:url>
                			<img src="${url }" alt="#" width="300" height="200"></a>
                		
                			<tr>
			                	<td><c:out value="${res.r_name}" /></td>
			                	<c:set var="content" value="${fn:substring(res.r_content, 0, 10)}"/>
			                		<c:if test="${fn:length(res.r_content) > 10}">
			                    		<c:set var="content" value="${content }..."/>
			             			</c:if>
			                	<td><c:out value="${content }"></c:out></td>
			            	</tr>
			            <c:if test="${not status.last }"><br/></c:if>
			        	</c:if>
			    	</c:forEach>
			  
			        <c:if test="${fn:length(resultList.restaurantList) > 5}">
			        	<a href="search?searchName=${keyword }&amp;category=category_res">더보기</a>
			        </c:if>
				</c:if></ul>
				</div>
				
				<!-- 커뮤니티 검색 결과 리스트 -->
				<div class="board-result">
					<ul><c:if test="${not empty resultList.getBoardList()}">
			    	<h1>커뮤니티</h1>
				    	<c:forEach items="${resultList.boardList}" var="board" varStatus="status">
				        	<c:if test="${status.index < 5}">
				            	<tr>
				                	<td><a href="detailBoard?board_id=${board.board_id}&b_common_board=${board.b_common_board}"><c:out value="${board.b_title}" /></a></td>
				                	<td>${board.formattedCreateDateSearch }</td>
				                	<c:set var="content" value="${fn:substring(board.b_content, 0, 10)}"/>
				                	<c:if test="${fn:length(board.b_content) > 10}">
				                    	<c:set var="content" value="${content }..."/>
				                	</c:if>
				                	<td><c:out value="${content }"></c:out></td>
				            	</tr>
				            	<c:if test="${not status.last }"><br/></c:if>
				        	</c:if>
				    	</c:forEach>
				        	<c:if test="${fn:length(resultList.boardList) > 5}">
				            	<a href="search?searchName=${keyword }&amp;category=category_comm">더보기</a>
				        	</c:if>
					</c:if></ul>
			  	</div>
		  </article>
	  </c:if> <!-- 카테고리를 전체로 설정하고 검색한 경우 끝 -->
	  
	  
	  <!-- 카테고리를 여행지로 설정하고 검색한 경우 -->
	  <c:if test="${category eq 'category_travel'}">
	  
		  <article id="search-content-travel">
			    <!-- 여행지 검색 결과 리스트 -->
			    <div class="onlytravel-result">
			    	<ul><c:if test="${not empty resultList.getTravelList()}">
			    	<h1>여행지</h1>
			    	<c:forEach items="${resultList.travelList}" var="travel" varStatus="status">
			        	<c:if test="${status.index < 12}">
			            	<a href="/TraDetail?tid=${travel.travel_id}">
                			<c:url value="/display" var="url">
                				<c:param name="file" value="${travel.img_stored_file }"></c:param>
                			</c:url>
                			<img src="${url }" alt="#" width="300" height="200"></a>
                		
                			<tr>
			                	<td><c:out value="${travel.t_name}" /></td>
			                	<c:set var="content" value="${fn:substring(travel.t_content, 0, 10)}"/>
			                		<c:if test="${fn:length(travel.t_content) > 10}">
			                    		<c:set var="content" value="${content }..."/>
			             			</c:if>
			                	<td><c:out value="${content }"></c:out></td>
			            	</tr>
			            <c:if test="${not status.last }"><br/></c:if>
			        	</c:if>
			    	</c:forEach>

			       <div>
						<c:if test="${paging.startPage > paging.pageBlock }">
							<a href="search?searchName=${keyword }&amp;currentPage=${paging.startPage-paging.pageBlock}&amp;category=${category}&amp;">[이전]</a>
						</c:if>
						<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
							<a href="search?currentPage=${i}&amp;category=${category}&amp;searchName=${keyword }">[${i}]</a>
						</c:forEach>
						<c:if test="${paging.endPage < paging.totalPage }">
							<a href="search?currentPage=${page.startPage+page.pageBlock}&amp;category=${category}&amp;searchName=${keyword }">[다음]</a>
						</c:if>
					</div> 
				</c:if></ul>
				</div>
	  </article>
	  </c:if>
	  
	  <!-- 카테고리를 숙소로 설정하고 검색한 경우 -->
	  <c:if test="${category eq 'category_house'}">
	  
		  <article id="search-content-house">
			    <!-- 숙소 검색 결과 리스트 -->
			    <div class="onlyhouse-result">
			    	<ul><c:if test="${not empty resultList.getHouseList()}">
			    	<h1>숙소</h1>
			    	<c:forEach items="${resultList.houseList}" var="house" varStatus="status">
			        	<c:if test="${status.index < 12}">
			            	<a href="/houDetail?hid=${house.house_id}">
                			<c:url value="/display" var="url">
                				<c:param name="file" value="${house.img_stored_file }"></c:param>
                			</c:url>
                			<img src="${url }" alt="#" width="300" height="200"></a>
                		
                			<tr>
			                	<td><c:out value="${house.h_name}" /></td>
			                	<c:set var="content" value="${fn:substring(house.h_content, 0, 10)}"/>
			                		<c:if test="${fn:length(house.h_content) > 10}">
			                    		<c:set var="content" value="${content }..."/>
			             			</c:if>
			                	<td><c:out value="${content }"></c:out></td>
			            	</tr>
			            <c:if test="${not status.last }"><br/></c:if>
			        	</c:if>
			    	</c:forEach>

			       <div>
						<c:if test="${paging.startPage > paging.pageBlock }">
							<a href="search?searchName=${keyword }&amp;currentPage=${paging.startPage-paging.pageBlock}&amp;category=${category}&amp;">[이전]</a>
						</c:if>
						<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
							<a href="search?currentPage=${i}&amp;category=${category}&amp;searchName=${keyword }">[${i}]</a>
						</c:forEach>
						<c:if test="${paging.endPage < paging.totalPage }">
							<a href="search?currentPage=${page.startPage+page.pageBlock}&amp;category=${category}&amp;searchName=${keyword }">[다음]</a>
						</c:if>
					</div> 
				</c:if></ul>
				</div>
	  </article>
	  </c:if>
	  
	  <!-- 카테고리를 맛집로 설정하고 검색한 경우 -->
	  <c:if test="${category eq 'category_res'}">
	  
		  <article id="search-content-res">
			    <!-- 맛집 검색 결과 리스트 -->
			    <div class="onlyres-result">
			    	<ul><c:if test="${not empty resultList.getRestaurantList()}">
			    	<h1>맛집</h1>
			    	<c:forEach items="${resultList.restaurantList}" var="res" varStatus="status">
			        	<c:if test="${status.index < 12}">
			            	<a href="/resDetail?rid=${res.restaurant_id}">
                			<c:url value="/display" var="url">
                				<c:param name="file" value="${res.img_stored_file }"></c:param>
                			</c:url>
                			<img src="${url }" alt="#" width="300" height="200"></a>
                		
                			<tr>
			                	<td><c:out value="${res.r_name}" /></td>
			                	<c:set var="content" value="${fn:substring(res.r_content, 0, 10)}"/>
			                		<c:if test="${fn:length(res.r_content) > 10}">
			                    		<c:set var="content" value="${content }..."/>
			             			</c:if>
			                	<td><c:out value="${content }"></c:out></td>
			            	</tr>
			            <c:if test="${not status.last }"><br/></c:if>
			        	</c:if>
			    	</c:forEach>

			       <div>
						<c:if test="${paging.startPage > paging.pageBlock }">
							<a href="search?searchName=${keyword }&amp;currentPage=${paging.startPage-paging.pageBlock}&amp;category=${category}&amp;">[이전]</a>
						</c:if>
						<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
							<a href="search?currentPage=${i}&amp;category=${category}&amp;searchName=${keyword }">[${i}]</a>
						</c:forEach>
						<c:if test="${paging.endPage < paging.totalPage }">
							<a href="search?currentPage=${page.startPage+page.pageBlock}&amp;category=${category}&amp;searchName=${keyword }">[다음]</a>
						</c:if>
					</div> 
				</c:if></ul>
				</div>
	  </article>
	  </c:if>
	  
	  
	  <!-- 카테고리를 커뮤니티로 설정하고 검색한 경우 -->
	  <c:if test="${category eq 'category_comm'}">
	  
		  <article id="search-content-board">
			    <!-- 커뮤니티 검색 결과 리스트 -->
			    <div class="onlyboard-result">
			    	<ul><c:if test="${not empty resultList.getBoardList()}">
			    	<h1>커뮤니티</h1>
			    	<c:forEach items="${resultList.boardList}" var="board" varStatus="status">
			        	<c:if test="${status.index < 12}">
			            	<tr>
				                <td><a href="detailBoard?board_id=${board.board_id}&b_common_board=${board.b_common_board}"><c:out value="${board.b_title}" /></a></td>
				                <td>${board.formattedCreateDateSearch }</td>
				                <c:set var="content" value="${fn:substring(board.b_content, 0, 10)}"/>
				                <c:if test="${fn:length(board.b_content) > 10}">
				                    <c:set var="content" value="${content }..."/>
				                </c:if>
				                <td><c:out value="${content }"></c:out></td>
				            </tr>
				            	<c:if test="${not status.last }"><br/></c:if>
			        	</c:if>
			    	</c:forEach>

			       <div>
						<c:if test="${paging.startPage > paging.pageBlock }">
							<a href="search?searchName=${keyword }&amp;currentPage=${paging.startPage-paging.pageBlock}&amp;category=${category}&amp;">[이전]</a>
						</c:if>
						<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
							<a href="search?currentPage=${i}&amp;category=${category}&amp;searchName=${keyword }">[${i}]</a>
						</c:forEach>
						<c:if test="${paging.endPage < paging.totalPage }">
							<a href="search?currentPage=${page.startPage+page.pageBlock}&amp;category=${category}&amp;searchName=${keyword }">[다음]</a>
						</c:if>
					</div> 
				</c:if></ul>
				</div>
	  </article>
	  </c:if>
		
	</section>
	
	
	  
	<hr>
<c:import url="footer.jsp"/>
</body>
</html>