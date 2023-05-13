<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">

<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>


<script type="text/javascript">
  function scrollToElement(id) {
    var element = document.getElementById(id);
    element.scrollIntoView({behavior: "smooth", block: "start", inline: "nearest"});
  }

</script>

<link href="/css/list.css" rel="stylesheet" type="text/css">
<link href="/css/search.css" rel="stylesheet" type="text/css">



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
                  
                     <td id="search-td" style=" padding-left:80px;">
                        <c:if test="${not empty resultList.getTravelList()}">
                           <c:choose>
                               <c:when test="${category eq 'category_total'}">
                                          <a href="#travel-places" onclick="scrollToElement('travel-places')">여행지(${travelCount})</a>
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
                     <td id="search-td" style=" padding-left:80px;">
                        <c:if test="${not empty resultList.getHouseList()}">
                            <c:choose>
                               <c:when test="${category eq 'category_total'}">
                                   <a href="#house-places" onclick="scrollToElement('house-places')">숙소 (${houseCount})</a>
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
                     <td id="search-td" style=" padding-left:80px;">
                        <c:if test="${not empty resultList.getRestaurantList()}">
                            <c:choose>
                               <c:when test="${category eq 'category_total'}">
                                  <a href="#res-places" onclick="scrollToElement('res-places')">맛집 (${resCount})</a>
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
                     <td id="search-td" style=" padding-left:80px;">
                        <c:if test="${not empty resultList.getBoardList()}">
                            <c:choose>
                               <c:when test="${category eq 'category_total'}">
                                  <a href="#board-places" onclick="scrollToElement('board-places')">커뮤니티 (${boardCount})</a>
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
                     <td></td><td></td><td></td><td></td><td></td>
                  </tr>
             </table>   
            </div>
         </c:when>
      </c:choose>
   </article>
   
   <!-- 검색 결과 없음 -->
   <article id="no-search-result">
      <c:if test="${empty resultList.getTravelList() and empty resultList.getHouseList() and empty resultList.getRestaurantList() and empty resultList.getBoardList()}">
      <br>
      <br>
         <ul class="no-search-list">
            <li>단어의 철자가 단어의 철자가 정확한지 확인해 주시기 바랍니다.</li>
                <li>검색어의 단어 수를 줄이거나, 다른 검색어(유사어)로 검색해 보시기 바랍니다.</li>
                <li>일반적으로 많이 사용하는 검색어로 다시 검색해 주시기 바랍니다.</li>
         </ul>
         <br>
         <div>
            <img src="../img/crying.png" alt="#" style="width: 100%; height: auto; max-width: 200px;"></a>
            <br><br><br><br><br><br><br>
         </div>
   </c:if>
   </article>
   




   <!-- 검색 결과 존재 -->
   <div class="mh"  style="display:flex; justify-content:center;">
        <!-- 카테고리를 전체로 설정하고 검색한 경우 -->
        <c:if test="${category eq 'category_total'}">

           <article id="search-content-total">
                <!-- 여행지 검색 결과 리스트 -->
                <div class="travel-result">
                   <ul><c:if test="${not empty resultList.getTravelList()}">
                   <hr id="line" >
                   <h1 id="travel-places" class="title">여행지</h1>
                   <br>
           <!--      <div style="display: flex; flex-wrap: wrap; justify-content: space-between; gap: 10px;"> -->
                <div style="display: flex; flex-wrap: wrap; justify-content: flex-start; gap: 10px;">
                 <c:forEach items="${resultList.travelList}" var="travel" varStatus="status">
                   <c:if test="${status.index < 7}">
                     <div style="width: calc(33.33% - 10px); margin-bottom: 20px;" id="each-name">
                       <a href="/traDetail?tid=${travel.travel_id}">
                         <c:url value="/display" var="url">
                           <c:param name="file" value="${travel.img_stored_file}"></c:param>
                         </c:url>
                         <img src="${url}" alt="#" style="width: 100%; height: auto; max-width: 380px;    max-height: 200px">
                       </a>
                       <br>
                       <div id="each-name"> 
                         <a href="/traDetail?tid=${travel.travel_id}" class="each-name"><c:out value="${travel.t_name}" /></a>
                       </div>
                     </div>
                   </c:if>
                 </c:forEach>
               </div>
                          <c:if test="${fn:length(resultList.travelList) > 7}">
                             <a href="search?searchName=${keyword }&amp;category=category_travel"><button type="submit" style="margin-left: auto; margin-right: -30cm; margin-bottom: 10px">더보기</button></a>
                          </c:if>
                   </c:if></ul>
                </div>
            
        
            
            <!-- 숙소 검색 결과 리스트 -->
            <div class="house-result">
               <ul>
                <c:if test="${not empty resultList.getHouseList()}">
                <hr id="line" >
                   <h1 id="house-places" class="title">숙소</h1>
                   <br>
                   <div style="display: flex; flex-wrap: wrap; justify-content: space-between; gap: 10px;">
                          <c:forEach items="${resultList.houseList}" var="house" varStatus="status">
                            <c:if test="${status.index < 7}">
                              <div style="width: calc(33.33% - 10px); margin-bottom: 20px;" id="each-name">
                                <a href="/houDetail?hid=${house.house_id}"></a>
                                  <c:url value="/display" var="url">
                                    <c:param name="file" value="${house.img_stored_file }"></c:param>
                                  </c:url>
                                  <img src="${url}" alt="#" style="width: 100%; height: auto; max-width: 380px; max-height: 200px">
                            <br>
                            <div id="each-name">
                               <a href="/houDetail?hid=${house.house_id}" class="each-name"><c:out value="${house.h_name}" /></a>
                     </div>
                              </div>
                            </c:if>
                          </c:forEach>
              </div>
                    <c:if test="${fn:length(resultList.houseList) > 7}">
                       <a href="search?searchName=${keyword }&amp;category=category_house"><button type="submit" style="margin-left: auto; margin-right: -30cm; margin-bottom: 10px">더보기</button></a>
                    </c:if>
               </c:if>
                </ul>
            </div>
            
            
            <!-- 맛집 검색 결과 리스트 -->
            <div class="res-result">
                <ul><c:if test="${not empty resultList.getRestaurantList()}">
                <hr id="line" >
                <h1 id="res-places" class="title">맛집</h1>
                <br>
                <div style="display: flex; flex-wrap: wrap; justify-content: space-between; gap: 10px;">
                          <c:forEach items="${resultList.restaurantList}" var="res" varStatus="status">
                            <c:if test="${status.index < 7}">
                              <div style="width: calc(33.33% - 10px); margin-bottom: 20px;" id="each-name">
                            
                                      <a href="/resDetail?rid=${res.restaurant_id}">
                               
                                  <c:url value="/display" var="url">
                                    <c:param name="file" value="${res.img_stored_file }"></c:param>
                                  </c:url>
                                  <img src="${url}" alt="#" style="width: 100%; height: auto; max-width: 380px;  max-height: 200px"></a>
                      <br>
                           <div id="each-name"> 
                         <a href="/resDetail?rid=${res.restaurant_id}" class="each-name"><c:out value="${res.r_name}" /></a>
                         </div>
                              </div>
                            </c:if>
                          </c:forEach>
           </div>
                 <c:if test="${fn:length(resultList.restaurantList) > 7}">
                    <a href="search?searchName=${keyword }&amp;category=category_res"><button type="submit" style="margin-left: auto; margin-right: -30cm; margin-bottom: 10px">더보기</button></a>
                 </c:if>
            </c:if>
            </ul>
            </div>
            
            
            <!-- 커뮤니티 검색 결과 리스트 -->
              <div class="bod-result"  > 
              <ul>
               <c:if test="${not empty resultList.getBoardList()}">
               <hr id="line" >
                   <table id ="bod-table"style=" text-align: top;    margin-left: 20%;">
                      <tr>
                           <td colspan="3">
                             <h1 id="board-places" style="margin-left: 35%; font-size: 24px; color: #404040; font-family: 'IBM Plex Sans KR', sans-serif;">커뮤니티</h1>
                           </td>
                      </tr>
                      
                      <c:forEach items="${resultList.boardList}" var="board" varStatus="status">
                           <c:if test="${status.index < 10}">
                              <tr style="padding-bottom: 20px;">
                                  <td style="padding-left: 50px;">
                                  <a href="detailBoard?board_id=${board.board_id}&amp;b_common_board=${board.b_common_board}" class="each-name"><c:out value="${board.b_title}" /></a>
                                  <br>
                                     <c:choose>
                                <c:when test="${fn:length(board.b_content) < 50}">
                                  <c:out value="${board.b_content}" />
                                </c:when>
                                <c:otherwise>
                                  <c:out value="${fn:substring(board.b_content, 0, 60)}" />...
                                </c:otherwise>
                              </c:choose>
                                     
                                     <hr id="line" >
                                   </td>
                                  <td style="padding-left: 50px;">${board.formattedCreateDateSearch }
                                 </td>
                              </tr>
                           <c:if test="${not status.last }">
                          </c:if>
                           </c:if>
                      </c:forEach>
                      
                    
                   </table>
            </c:if>
                
                 <c:if test="${fn:length(resultList.boardList) > 9}">
                   <a href="search?searchName=${keyword }&amp;category=category_comm">
                     <button type="submit" style="margin-left: 70%; margin-top: 20px;  ">더보기</button>
                   </a>
                 </c:if>
            
            </ul>
         </div>
        </article>
     </c:if> 
     <!-- 카테고리를 전체로 설정하고 검색한 경우 끝 -->



      <!-- 카테고리를 여행지로 설정하고 검색한 경우 -->
     <c:if test="${category eq 'category_travel'}">
     
        <article id="search-content-travel" >
             <!-- 여행지 검색 결과 리스트 -->
             <div class="onlytravel-result">
                   <ul><c:if test="${not empty resultList.getTravelList()}">
                   <br>
                    <!-- <div style="display: flex; flex-wrap: wrap; justify-content: space-between; gap: 10px;"> -->
                   <div style="display: flex; flex-wrap: wrap; justify-content: flex-start; gap: 10px;">
                 <c:forEach items="${resultList.travelList}" var="travel" varStatus="status">
                   <%-- <c:if test="${status.index < 9}"> --%>
                     <div style="width: calc(33.33% - 10px); margin-bottom: 20px;" id="each-name">
                       <a href="/traDetail?tid=${travel.travel_id}">
                         <c:url value="/display" var="url">
                           <c:param name="file" value="${travel.img_stored_file}"></c:param>
                         </c:url>
                         <img src="${url}" alt="#" style="width: 100%; height: auto; max-width: 380px;    max-height: 200px">
                       </a>
                       <br>
                       <div id="each-name"> 
                         <a href="/traDetail?tid=${travel.travel_id}" class="each-name"><c:out value="${travel.t_name}" /></a>
                       </div>
                     </div>
                  <%--  </c:if> --%>
                 </c:forEach>
                    </div>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                        <div  style="text-align: center; margin-left: 300px;">
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
                   <br>
                   <div style="display: flex; flex-wrap: wrap; justify-content: flex-start; gap: 10px;">
                 <c:forEach items="${resultList.houseList}" var="house" varStatus="status">
                     <div style="width: calc(33.33% - 10px); margin-bottom: 20px;" id="each-name">
                       <a href="/houDetail?hid=${house.house_id}">
                         <c:url value="/display" var="url">
                           <c:param name="file" value="${house.img_stored_file}"></c:param>
                         </c:url>
                         <img src="${url}" alt="#" style="width: 100%; height: auto; max-width: 380px;    max-height: 200px">
                       </a>
                       <br>
                       <div id="each-name"> 
                         <a href="/houDetail?hid=${house.house_id}" class="each-name"><c:out value="${house.h_name}" /></a>
                       </div>
                     </div>
                 </c:forEach>
                    </div>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
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
                   <br>
                   <div style="display: flex; flex-wrap: wrap; justify-content: flex-start; gap: 10px;">
                 <c:forEach items="${resultList.restaurantList}" var="res" varStatus="status">
                     <div style="width: calc(33.33% - 10px); margin-bottom: 20px;" id="each-name">
                       <a href="/resDetail?rid=${res.restaurant_id}">
                         <c:url value="/display" var="url">
                           <c:param name="file" value="${res.img_stored_file }"></c:param>
                         </c:url>
                         <img src="${url}" alt="#" style="width: 100%; height: auto; max-width: 380px;    max-height: 200px">
                       </a>
                       <br>
                       <div id="each-name"> 
                         <a href="/resDetail?rid=${res.restaurant_id}" class="each-name"><c:out value="${res.r_name}" /></a>
                       </div>
                     </div>
                 </c:forEach>
                    </div>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
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
           
              <c:if test="${not empty resultList.getBoardList()}">
                         <table style="padding-left: 10%; text-align: top;">
                            <tr>
                                 <td colspan="3">
                                 </td>
                            </tr>
                            <c:forEach items="${resultList.boardList}" var="board" varStatus="status">
                                    <tr style="padding-bottom: 20px;">
                                          <td style="padding-left: 50px;">
                                           <a href="detailBoard?board_id=${board.board_id}&amp;b_common_board=${board.b_common_board}" class="each-name"><c:out value="${board.b_title}" /></a>
                                           <br>
                                              <c:choose>
                                         <c:when test="${fn:length(board.b_content) < 50}">
                                           <c:out value="${board.b_content}" />
                                         </c:when>
                                         <c:otherwise>
                                           <c:out value="${fn:substring(board.b_content, 0, 60)}" />...
                                         </c:otherwise>
                                       </c:choose>
                                              
                                              <hr id="line" >
                                          </td>
                                        <td style="padding-left: 50px;">${board.formattedCreateDateSearch }
                                       </td>
                                    </tr>
                                 <c:if test="${not status.last }">
                                </c:if>
                            </c:forEach>
                         </table>
                         <br>
                         <br>
                         <br>
                         <br>
                         <br>
                         <br>
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
                  </c:if>
        </article>
     </c:if> 
     

     
   
   <!-- 인기 검색어 --> 
    <c:if test="${not empty resultList.getTravelList() or not empty resultList.getHouseList() or not empty resultList.getRestaurantList() or not empty resultList.getBoardList()}">
      <article id="word" style="display:flex; flex-direction:column; align-items:center;    ">
      <article id="popular-container">
         <div id="popular-title"><p style="color: white; font-size: 20px; font-weight: bold;">인기 검색어</p></div>
         <br>
         <input id="tab-1" type="radio" name="tabs" checked>
         <label for="tab-1">일간</label>
         
         <input id="tab-2" type="radio" name="tabs">
         <label for="tab-2">주간</label>
         
         <input id="tab-3" type="radio" name="tabs">
         <label for="tab-3">월간</label>
         
         <div class="content">
            <div id="content-1">
               <ul id="search-list">
                  <c:forEach var="daily" items="${dailyPopularKeywords}">
                     <li style="text-align: left;"><a href="/search?category=category_total&amp;searchName=${daily}">
                        <c:out value="${daily}"  /></a>
                     </li>
                  </c:forEach>
               </ul>  
            </div>
         
            <div id="content-2">
               <ul id="search-list">
                  <c:forEach var="weekly" items="${weeklyPopularKeywords}">
                     <li style="text-align: left;"><a href="/search?category=category_total&amp;searchName=${weekly}">
                        <c:out value="${weekly}" /></a>
                     </li>
                  </c:forEach>
               </ul>   
            </div>
         
            <div id="content-3">
               <ul id="search-list">
                  <c:forEach var="monthly" items="${monthlyPopularKeywords}">
                     <li style="text-align: left;"><a href="/search?category=category_total&amp;searchName=${monthly}">
                        <c:out value="${monthly}" /></a>
                     </li>
                  </c:forEach>
               </ul>
            </div>
         </div>
      </article>
      <br>
      
      
      
        <!-- 최근 검색어 -->       
        <c:if test="${not empty recentSearchList}">
      <article id="recent-container">
      <div id="recent-title"><p style="color: white; font-size: 20px; font-weight: bold;">최근 검색어</p></div>
      <br>
           <ul id="recent-list">
         <c:forEach var="recentSearchList" items="${recentSearchList}">
            <li><a href="/search?category=category_total&amp;searchName=${recentSearchList.search_term}">
               <c:out value="${recentSearchList.search_term}" /></a>
            </li>
         </c:forEach>
         </ul>
      </article>
      </c:if>
      
   </article>
   </c:if>
   
   </div>
   
   </section>

     
   <hr>
   <br><br><br><br><br><br><br><br>
   	<c:import url="../fragments/footer.jsp"/>
</body>