<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
           <%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
   	<script defer src="/js/detailBoardDelChk.js"></script>
   	<script defer src="/js/detailBoardWarning.js"></script>
   	<script defer src="/js/detailBoardRe.js"></script>
	<script defer src="/js/detailBoardLoginChk.js"></script>
   	<link href="/css/list.css" rel="stylesheet" type="text/css">
   	
</head>
<body>

<div id="img_benner">
		<img src="img/main-picture.png" alt="배너">
	</div>
	<h1>여행지글 상세		</h1>
	
<div style="display:flex; justify-content:center;">	
	<!-- 사진 올리기 -->
<table style="margin:left;">
		<tr>
			<td hidden>번호</td>													
			<td>여행지 대표 사진</td>
			
		</tr>
		<c:forEach items="${traImgList}" var="traImg">
			<tr>
				<td hidden>${traImg.img_id}</td>
			 	<td >
			 	
				<c:url value='/display' var='url'>
					<c:param name='file' value='${traImg.img_stored_file}'/>
				</c:url>
                     <img alt="#" src="${url}"  width="500" height="300">
			 	
			 	</td>
								
		</c:forEach>
		
</table>
	<table style="margin:auto;">
		<tr><th>여행지명</th>			<td>${travel.t_name}</td></tr>
		<tr><th>여행지주소</th>			<td>${travel.t_address}</td></tr>
		<tr><th>입장료</th>			<td>${travel.t_fee}</td></tr>
		<tr><th>운영시간</th>			<td>${travel.t_hour}</td></tr>
		<tr><th>문의전화</th>			<td>${travel.t_call}</td></tr>
		<tr><th>주차장여부</th>			<td>${travel.t_parking}</td></tr>
		<tr><th>여행지 정보</th>		<td>${travel.t_content}</td></tr>
		<tr> <th hidden> 			${travel.travel_id} </th>  </tr>
		

		<tr><td colspan="2">
		</tr>
	</table>
	
</div>	
		<hr>
		<a href="tra" class="button">목록</a>
		<a href="traUpdateForm?travel_id=${travel.travel_id}" class="button">수정</a>
		<a href="traDelete?travel_id=${travel.travel_id}" class="button">삭제</a>
		
		<hr>
		<h3>리뷰		</h3>
	<table style="margin:auto;">
				<tr>
					<td>리뷰번호</td>													
					<td  style=" padding-left:30px;">작성자</td>
					<td>내용</td>
					<td>평점</td>
					<td>작성일</td>
					
					
				</tr>
					<c:forEach items="${traRevList}" var="traRev">
					<tr>
						<td>${traRev.review_id}</td>
					 	<td>${traRev.m_nickname}</td>
					 	<td>${traRev.r_content}</td>
					 	<td>${traRev.r_score}</td>
					 	<td>${traRev.create_date}</td>
					  <td  style=" padding-left:30px;" ><a href="traRevUpdateForm?travel_id=${traRev.travel_id}&review_id=${traRev.review_id}">수정</a></td>
					 	<td style=" padding-left:30px;">
					 	 <a href="traRevDelete?review_id=${traRev.review_id}" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</a>
					 	 </td>
						
											
					</tr>
				</c:forEach>
				<tr>
					
				</tr>
			</table>
				<br><br><a  style=" padding-left:600px;" href="traRevWriteForm?travel_id=${travel.travel_id}">리뷰작성</a>
	
	
	
</body>
	<c:import url="footer.jsp"/>
</html>