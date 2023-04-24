<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:import url="header.jsp"/>
</head>
	<link href="css/main.css" rel="stylesheet" type="text/css">
	<link href="/css/list.css" rel="stylesheet" type="text/css">
<body>
	
	<c:import url="boardHeader.jsp"/>
	
	<hr>
	
	<c:set value="${listBoardS }" var="contents"/>
	
	<c:forEach items="${contents}" var="content" >
		<c:choose>
			<c:when test="${content.b_re_level == 0}">
				<table>
					<tr>
						<td style="width: 360px; font-size: 25px;">${content.b_title}</td>
						<td style="width: 100px; font-size: 16px;">${content.m_nickname}</td>
						<td style="width:150px; font-size: 16px;">${content.getFormattedCreateDate()}</td>
					</tr>
					<tr>
						<td colspan="3" height="400px">${content.b_content}</td>
					</tr>
					<tr>
						<td colspan="3" style="padding-left: 360px; color:red;">${content.b_like_cnt}</td>
					</tr>
					<tr>
						<td style="padding-left: 346px;">
							<form action="boardlike" method="post">
								<input type="hidden" name="board_id" value="${content.board_id}">
								<input type="hidden" name="b_common_board" value="${b_common_board}">
								<input type="submit" value="추천">
							</form>
						</td>
						<td colspan="2">
							<input type="button" id="reportBtn" value="신고" onclick="showRadio(this)">
						</td>
					</tr>
				</table>
				
				<form id="reportForm" action="reportMember" method="get" style="display:none;">
					<hr>
					<input type="hidden" name="board_id" value="${content.board_id}">
					<input type="hidden" name="b_common_board" value="${b_common_board}">
					<input type="hidden" name="member_id" value="${content.member_id}">
					<input type="hidden" name="u_nickname" value="${5}">
					<input type="radio" value="war100" name="w_common_warning">영리목적/홍보성
					<input type="radio" value="war200" name="w_common_warning">불법정보
					<input type="radio" value="war300" name="w_common_warning">음란성/선정성
					<input type="radio" value="war400" name="w_common_warning">욕설/인신공격
					<input type="radio" value="war500" name="w_common_warning">도배
					<input type="submit" value="신 고" onclick="submitForm()" id="reportSubBtn">
				</form>
				
				<script>
					function showRadio(button) {
					    var form = document.getElementById("reportForm");
					
					    if (form.style.display == "none") { 
							form.style.display = "block";
						} else {
							form.style.display = "none"; 
						}
					}
					document.getElementById('reportSubBtn').addEventListener('click', function(event) {
					event.preventDefault(); // 신고 버튼의 기본 동작을 막음
						var reportForm = document.getElementById('reportForm');
						var url = reportForm.getAttribute('action') + '?' + new URLSearchParams(new FormData(reportForm)).toString();
					    // 확인, 취소 버튼 추가
					    var confirmBox = document.createElement("div");
				        confirmBox.style.cssText = "position:fixed; top:0; left:0; width:100%; height:100%; background-color: rgba(0,0,0,0.5); display:flex; justify-content:center; align-items:center;";
				        
				        var confirmMessage = document.createElement("div");
				        confirmMessage.style.cssText = "background-color:#fff; padding:20px; border-radius:10px; text-align:center;";
				        
				        var confirmText = document.createElement("div");
				        confirmText.innerText = '"${content.m_nickname}" 님을  정말로 신고 하시겠습니까?';
				        confirmMessage.appendChild(confirmText);
				        
				        var confirmBtnContainer = document.createElement("div");
				        confirmBtnContainer.style.cssText = "display:flex; justify-content:center; margin-top:10px;";
				        
				        var confirmBtn = document.createElement("button");
				        confirmBtn.innerText = "확인";
				        confirmBtn.style.cssText = "background-color:#205E61; color:white; border:none; padding:10px 20px; font-weight:bolder; font-size: 15px; border-radius:5px; margin-right:10px;";
						
				        confirmBtn.addEventListener("mouseover", function() {
				            confirmBtn.style.backgroundColor = "white";
				            confirmBtn.style.color = "#205E61";
				            confirmBtn.style.transition = "ease 0.5s";
				            
				        });
				        
				        confirmBtn.addEventListener("mouseout", function() {
				            confirmBtn.style.backgroundColor = "#205E61";
				            confirmBtn.style.color = "white";
				        });
				        
				        confirmBtn.addEventListener("click", function() {
				        	var selectedRadio = document.querySelector('input[name="w_common_warning"]:checked');
				        	if (selectedRadio !== null) {
				        		reportForm.submit();
			        		}
			        		document.body.removeChild(confirmBox);
			        	});
				        confirmBtnContainer.appendChild(confirmBtn);
				        
				        var cancelBtn = document.createElement("button");
				        cancelBtn.innerText = "취소";
				        cancelBtn.style.cssText = "background-color:#205E61;; color:white; border:none; padding:10px 20px; font-weight:bolder; font-size: 15px; border-radius:5px;";
							
				        cancelBtn.addEventListener("mouseover", function() {
				        	cancelBtn.style.backgroundColor = "white";
				        	cancelBtn.style.color = "#205E61";
				        	cancelBtn.style.transition = "ease 0.5s";
				            
				        });
				        
				        cancelBtn.addEventListener("mouseout", function() {
				        	cancelBtn.style.backgroundColor = "#205E61";
				        	cancelBtn.style.color = "white";
				        });
				        
				        cancelBtn.addEventListener("click", function() {
				            document.body.removeChild(confirmBox);
				        });
				        confirmBtnContainer.appendChild(cancelBtn);
				        
				        confirmMessage.appendChild(confirmBtnContainer);
				        confirmBox.appendChild(confirmMessage);
				        document.body.appendChild(confirmBox);
				    });
				</script>
			<hr>
			
			</c:when>
							
			<c:when test="${content.b_re_level > 0}">
				<form id="reply" action="#" class="reply-form">
					<table>
						<tr>
							<td style="width: 100px;">${content.m_nickname}</td>
							<td style="width: 400px;">${content.b_content}</td>
							<td style="width:180px;">${content.getFormattedCreateDate()}</td>
							<td><input type="button" value="답글" onclick="showMembers(this)"></td>
							<td><input type="submit" value="신고"></td>
						</tr>
						<tr class="members" style="display: none;">
							<form action="#">
							<td colspan="5">
								<input type="text" name="b_content" placeholder="댓글을 입력하세요">
								<input type="submit" value="등 록">
							</td>
							</form>
						</tr>
					</table>
				</form>
			</c:when>
			
		</c:choose>
	</c:forEach>
	
	<script>
		function showMembers(button) {
		var membersRow = button.parentNode.parentNode.nextElementSibling;
			if (membersRow.classList.contains("members")) {
				if (membersRow.style.display == "none") {
		    		membersRow.style.display = "table-row";
				} else {
					membersRow.style.display = "none";
				}
			}
		}
	</script>
	
	<c:if test="${msg != null }">${msg }</c:if>
	
	<h6>명예훼손, 개인정보 유출, 분쟁 유발, 허위 사실 유포 등의 글은 이용약관에 의해 제제는 물론<br> 
	법률에  의해 처벌받을 수 있습니다.건전한 커뮤니티를 위해 자제 당부 드립니다.</h6>
	<form action="writeBoardRe" method="post">
		<div id="replyForm">
			<input type="hidden" name="board_id" value="${board_id}">
	         <input type="hidden" name="member_id" value="5">
	         <input type="hidden" name="b_common_board" value="${b_common_board}">
	         <input type="text" name="b_content" placeholder="댓글을 입력하세요">
	         <input type="submit" value="등 록">
		</div>
	</form>
	
</body>
	<c:import url="footer.jsp"/>
</html>