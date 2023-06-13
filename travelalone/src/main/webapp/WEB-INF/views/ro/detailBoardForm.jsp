<%@page import="com.travelAlone.s20230404.model.BodImg"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
	<script defer src="/js/detailBoardDelChk.js"></script>
	<script defer src="/js/detailBoardWarning.js"></script>
	<script defer src="/js/detailBoardRe.js"></script>
	<script defer src="/js/detailBoardLoginChk.js"></script>
	<script defer src="/js/detailBoardUpdateChk.js"></script>
	<script defer src="/js/userPage.js"></script>
	<link href="css/main.css" rel="stylesheet" type="text/css">
	<link href="/css/list.css" rel="stylesheet" type="text/css">
</head>
<body>
	<c:if test="${not empty message}">
		<script type="text/javascript">
			alert("${message}");
		</script>
	</c:if>
	<c:import url="boardHeader.jsp"/>
      
	<hr>
	
	<c:set value="${listBoardS }" var="contents"/>
		<c:forEach items="${contents}" var="content" varStatus="status">
			<c:choose>
	            <c:when test="${content.b_re_level == 0}">
	            
				<table>
					<tr>
						<td style="width: 360px; font-size: 25px;">${content.b_title}</td>
						<td style="width: 100px; font-size: 16px;"><a onclick="openUserPage(${content.member_id})">${content.m_nickname}</a></td>
						<td style="width:150px; font-size: 16px;">${content.getFormattedCreateDate()}</td>
					</tr>
					<tr>
		            	<c:if test="${user_id == content.member_id }">
							<td colspan="3">
								<div class="button-container">
									<form action="updateBoardForm" method="post">
	                                   <input type="hidden" name="board_id" value="${board_id }">
	                                   <input type="hidden" name="member_id" value="${content.member_id }">
	                                   <input type="hidden" name="b_title" value="${content.b_title}">
	                                   <input type="hidden" name="b_content" value="${content.b_content}">
	                                   <input type="submit" value="수정">
                                	</form>
									
		                            <input type="button" id="del_board" value="삭제" onclick="delConfirm(${board_id })" >
								</div>
							</td>
						</c:if>
					</tr>
					
	                <c:forEach items="${content.img_stored_file}" var="img_file">
					<tr>
						<td colspan="3">
						<c:url value='/display' var='url'><c:param name='file' value='${img_file}'/></c:url>
						<img alt="#" src="${url}" width="100%" height="100%">
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="3" height="400px">${content.b_content}</td>
					</tr>
					<tr>
						<td colspan="3" style="padding-left: 250px; color:red;">${content.b_like_cnt}</td>
					</tr>
					<tr>
						<td style="padding-left: 230px;">
							<form action="boardlike" method="post">
								<input type="hidden" name="board_id" value="${content.board_id}">
								<input type="hidden" name="b_common_board" value="${b_common_board}">
								<input type="submit" value="추천" onsubmit="return chkId();">
							</form>
						</td>
						<td colspan="2">
							<input type="button" id="reportBtn" value="신고" onclick="showRadio(this)">
						</td>
					</tr>
				</table>
            
			<form id="reportForm" action="reportMember" method="get" style="display:none;" onsubmit="return chkId();">
	            <hr>
	            
	            <h3> "${content.m_nickname }" 님을 신고하는 사유</h3>
	            
				<input type="hidden" name="board_id" value="${content.board_id}">
				<input type="hidden" name="b_common_board" value="${b_common_board}">
				<input type="hidden" name="member_id" value="${content.member_id}">
				<input type="hidden" name="u_nickname" value="${user_id}">
				<input type="radio" value="war100" name="w_common_warning">영리목적/홍보성
				<input type="radio" value="war200" name="w_common_warning">불법정보
				<input type="radio" value="war300" name="w_common_warning">음란성/선정성
				<input type="radio" value="war400" name="w_common_warning">욕설/인신공격
				<input type="radio" value="war500" name="w_common_warning">도배
				<input type="submit" value="신 고" onclick="submitForm()" id="reportSubBtn">
			</form>
	            
			<hr>
	         
		</c:when>
                     
	<c:when test="${content.b_re_level > 0}">
		<div id="reply" class="reply-form">
		    <table>
               	<tr>
                  	<td class="level-${content.b_re_level}" style="width: 130px;"><a onclick="openUserPage(${content.member_id})">${content.m_nickname}</a></td>
                    	<td style="width: 400px;">${content.b_content}</td>
                    	<td style="width: 180px;">${content.getFormattedCreateDate()}</td>
                 
                    		<c:if test="${user_id == content.member_id }">
                          		<td>
									<input type="hidden" id="b_ref" value="${content.b_ref }">
									<input type="hidden" id="b_common_board" value="${content.b_common_board }">
									<input type="button" value="삭제" onclick="delReConfirm(${content.b_re_step } , ${content.b_re_level })">
                          		</td>
		                    </c:if>
		                    <c:if test="${user_id != content.member_id }">
                      			<td style="width: 50px;"></td>
                    		</c:if>
		                    <c:if test="${user_id == content.member_id }">
                     			<td><input type="button" value="수정" onclick="showUpdate(this)"></td>
		                    </c:if>
		                    <c:if test="${user_id != content.member_id }">
                      			<td style="width: 50px;"></td>
                    		</c:if>
                  			<c:if test="${content.b_re_level < 3}">
                       			<td><input type="button" value="답글" onclick="showMembers(this)"></td>
                    		</c:if>
                    		<c:if test="${content.b_re_level >= 3 }">
                       			<td style="width: 50px;"></td>
                   			</c:if>
                   			<td><input type="button" value="신고" onclick="showReWarning(this)"></td>
               			</tr>
              			<tr class="members" style="display: none;">
				    		<td colspan="7">
				        		<form id="bruForm" onsubmit="BoardReUpdate(${status.index})">
						            <input type="hidden" id="board_id${status.index}" name="board_id" value="${content.board_id}">
						            <input type="hidden" name="b_ref" value="${content.b_ref}">
						            <input type="hidden" name="b_common_board" value="${b_common_board}">
						            <input type="text" id="b_content${status.index}" placeholder="수정할 댓글을 입력하세요">
						            <input type="submit" value="수 정">
				        		</form>
				    		</td>
						</tr>
               			<tr class="members" style="display: none;">
							<td colspan="7">
                      			<form action="WriteBoardReLevel">
									<input type="hidden">
									<input type="hidden" name="board_id" value="${board_id}">
									<input type="hidden" name="member_id" value="${user_id}">
									<input type="hidden" name="b_common_board" value="${b_common_board}">
									<input type="hidden" name="b_ref" value="${content.b_ref}">
									<input type="hidden" name="b_re_step" value="${content.b_re_step}">
									<input type="hidden" name="b_re_level" value="${content.b_re_level}">
									<input type="text" name="b_content" placeholder="댓글을 입력하세요">
									<input type="submit" value="등 록">
								</form>
                 			</td>
               			</tr>
						<tr class="members" style="display: none;">
                  			<td colspan="7">
                       			<form action="reportMember" onsubmit="return chkId();">
			                        <h3> "${content.m_nickname }" 님을 신고하는 사유</h3>
			                        <input type="hidden" name="board_id" value="${board_id}">
			                        <input type="hidden" name="b_common_board" value="${b_common_board}">
			                        <input type="hidden" name="member_id" value="${content.member_id}">
			                        <input type="hidden" name="u_nickname" value="${user_id}">
			                        <input type="radio" value="war100" name="w_common_warning">영리목적/홍보성
			                        <input type="radio" value="war200" name="w_common_warning">불법정보
			                        <input type="radio" value="war300" name="w_common_warning">음란성/선정성
			                        <input type="radio" value="war400" name="w_common_warning">욕설/인신공격
			                        <input type="radio" value="war500" name="w_common_warning">도배
			                        <input type="submit" value="신 고" onclick="submitForm()" id="reportSubBtn">
                       			</form>
							</td>
						</tr>
					</table>
				</div>
			</c:when>
         
	    </c:choose>
	</c:forEach>
   
	<c:if test="${msg != null }">${msg }</c:if>
   
   	<h6>명예훼손, 개인정보 유출, 분쟁 유발, 허위 사실 유포 등의 글은 이용약관에 의해 제제는 물론<br> 
	법률에  의해 처벌받을 수 있습니다.건전한 커뮤니티를 위해 자제 당부 드립니다.</h6>
	<form action="writeBoardRe" name="frm" method="post" onsubmit="return chkId();">
		<div id="replyForm">
			<input type="hidden" name="board_id" value="${board_id}">
               <c:choose>
                    <c:when test="${user_id != null }">
                        <input type="hidden" name="member_id" value="${user_id }">
                    </c:when>
					<c:otherwise>
						<input type="hidden" name="member_id">
					</c:otherwise>
				</c:choose>
            <input type="hidden" name="b_common_board" value="${b_common_board}">
            <input type="text" name="b_content" placeholder="댓글을 입력하세요">
			<input type="submit" value="등 록">
		</div>
	</form>
</body>
	<c:import url="../fragments/footer.jsp"></c:import>
</html>