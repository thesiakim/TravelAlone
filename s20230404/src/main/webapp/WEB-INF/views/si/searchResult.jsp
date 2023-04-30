<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

.container-search {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-top: 20px;
}
 
.search-content {
  flex: 1;
} 

 .search-rank {
  display: flex;
  flex-direction: column;
  width: 20%;
} 

.popular-searches,
.latest-searches {
  width: 100%;
}
 
.search-content h2 {
  text-align: center;
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 20px;
}

.container-search {
  display: flex;
  justify-content: center;
  margin: 20px 0;
  flex-wrap: wrap;
}


.sidebar {
  margin: 2cm 0 2cm 3cm;
  width: 200px;
  background-color: #f8f8f8;
  border: 1px solid #ddd;
  padding: 10px;
  
}

.sidebar ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
}

.sidebar li {
  margin-bottom: 10px;
  padding-left: 10px;
}

.sidebar li a {
  display: block;
  font-size: 15px;
  padding: 5px;
  color: #333;
  text-decoration: none;
  border-radius: 3px;
}

.sidebar li a:hover {
  background-color: #e6e6e6;
}

.sidebar ul li a {
  font-size: 15px;
  text-align: left;
}










</style>
</head>
<body>
<%@include file="header.jsp" %>

<div class="container-search">

  <div class="sidebar">
    <ul>
      <li><a href="#">전체</a></li>
      <li><a href="#">여행지</a></li>
      <li><a href="#">숙소</a></li>
      <li><a href="#">맛집</a></li>
      <li><a href="#">커뮤니티</a></li>
    </ul>
  </div> 
  
  <div class="integration">
  	<ul class="menu_title">
  		<li>통합 검색</li>
  		<li>총 <c:out value="${resultCount}" />건</li>
  	</ul>
  	<div class="integration_box">
  		<div class="integration_text">
  			<div class="integration_title">
  				<strong>검색 결과</strong>
  			</div>
  			<div class="integration_list">
  				<ul>
  					<li>
  						<c:if test="${not empty resultList.getTravelList()}">
  							여행지(${resultList.getTravelList().size() })
  						</c:if>
  					</li>
  					<li>
  						<c:if test="${not empty resultList.getHouseList()}">
  							숙소(${resultList.getHouseList().size() })
  						</c:if>
  					</li>
  					<li>
  						<c:if test="${not empty resultList.getRestaurantList()}">
  							맛집(${resultList.getRestaurantList().size() })
  						</c:if>
  					</li>
  					<li>
  						<c:if test="${not empty resultList.getBoardList()}">
  							커뮤니티(${resultList.getBoardList().size() })
  						</c:if>
  					</li>
  				</ul>
  			</div>
  		</div>
  	</div>
  	
  </div>
  
  

  
  
  <div class="search-content">
    <h2>검색어 <span style="color: rgb(31,171,137)">"<c:out value="${keyword}" />"</span>에 대해 <span style="color: rgb(31,171,137)">총 <c:out value="${resultCount}" />건</span>의 결과를 찾았습니다.</h2>
    <ul><c:if test="${not empty resultList.getTravelList()}">
    <h1>여행지</h1>
    <c:forEach items="${resultList.travelList}" var="travel" varStatus="status">
        <c:if test="${status.index < 5}">
            <tr>
                <td><c:out value="${travel.t_name}" /></td>
                <td>${travel.formattedCreateDate }</td>
                <c:set var="content" value="${fn:substring(travel.t_content, 0, 10)}"/>
                <c:if test="${fn:length(travel.t_content) > 10}">
                    <c:set var="content" value="${content }..."/>
                </c:if>
                <td><c:out value="${content }"></c:out></td>
            </tr>
            <c:if test="${not status.last }"><br/></c:if>
        </c:if>
    </c:forEach>
        <c:if test="${fn:length(resultList.travelList) > 5}">
            <a href="nextPage.jsp">더보기</a>
        </c:if>
	</c:if></ul>
	
	<ul><c:if test="${not empty resultList.getHouseList()}">
    <h1>숙소</h1>
    <c:forEach items="${resultList.houseList}" var="house" varStatus="status">
        <c:if test="${status.index < 5}">
            <tr>
                <td><c:out value="${house.h_name}" /></td>
                <td>${house.formattedCreateDate }</td>
                <c:set var="content" value="${fn:substring(travel.t_content, 0, 10)}"/>
                <c:if test="${fn:length(house.h_content) > 10}">
                    <c:set var="content" value="${content }..."/>
                </c:if>
                <td><c:out value="${content }"></c:out></td>
            </tr>
            <c:if test="${not status.last }"><br/></c:if>
        </c:if>
    </c:forEach>
        <c:if test="${fn:length(resultList.houseList) > 5}">
            <a href="nextPage.jsp">더보기</a>
        </c:if>
	</c:if></ul>
	
	<ul><c:if test="${not empty resultList.getRestaurantList()}">
    <h1>맛집</h1>
    <c:forEach items="${resultList.restaurantList}" var="res" varStatus="status">
        <c:if test="${status.index < 5}">
            <tr>
                <td><c:out value="${res.r_name}" /></td>
                <td>${res.formattedCreateDate }</td>
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
            <a href="nextPage.jsp">더보기</a>
        </c:if>
	</c:if></ul>
	
	<ul><c:if test="${not empty resultList.getBoardList()}">
    <h1>커뮤니티</h1>
    <c:forEach items="${resultList.boardList}" var="board" varStatus="status">
        <c:if test="${status.index < 5}">
            <tr>
                <td><c:out value="${board.b_title}" /></td>
                <td>${board.formattedCreateDate_search }</td>
                <c:set var="content" value="${fn:substring(res.r_content, 0, 10)}"/>
                <c:if test="${fn:length(board.b_content) > 10}">
                    <c:set var="content" value="${content }..."/>
                </c:if>
                <td><c:out value="${content }"></c:out></td>
            </tr>
            <c:if test="${not status.last }"><br/></c:if>
        </c:if>
    </c:forEach>
        <c:if test="${fn:length(resultList.boardList) > 5}">
            <a href="nextPage.jsp">더보기</a>
        </c:if>
	</c:if></ul>
  </div>
  
  
  <!-- 인기 검색어 -->
  <div class="search-rank">
    <div class="popular-searches">
     <h2>일간 검색어</h2>
    	<ul>
    		<c:forEach var="daily" items="${dailyPopularKeywords}">
				<li><a href="/search?category=category_total&amp;searchName=${daily}">
			    	<c:out value="${daily}" /></a>
			    </li>
			</c:forEach>
    	</ul>   
    	
     <h2>주간 검색어</h2> 
     	<ul>
    		<c:forEach var="weekly" items="${weeklyPopularKeywords}">
				<li><a href="/search?category=category_total&amp;searchName=${weekly}">
			    	<c:out value="${weekly}" /></a>
			    </li>
			</c:forEach>
    	</ul>   
    	
     <h2>월간 검색어</h2>
     	<ul>
    		<c:forEach var="monthly" items="${monthlyPopularKeywords}">
				<li><a href="/search?category=category_total&amp;searchName=${monthly}">
			    	<c:out value="${monthly}" /></a>
			    </li>
			</c:forEach>
    	</ul>   
         	
    </div>
    <div class="latest-searches">
      <h2>최근 검색어</h2>
    <ul>
      <li><a href="#">대전 여행지</a></li>
      <li><a href="#">제주 숙소</a></li>
      <li><a href="#">서울 맛집</a></li>
    </ul>
    </div>
  </div>
  
  
</div>
<hr>
<c:import url="footer.jsp"/>
</body>
</html>