<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	    <%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<%
	String context = request.getContextPath();
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
	function deleteImage(g_notice_id, img_id, p_index) {
	 	/* alert("g_notice_id:"   + g_notice_id);
		alert("img_id:"	    + img_id);
		alert("p_index:"	+ p_index);  */
		
		$.ajax({
		    url: "<%=context%>/deleteNotImg",
		    data: {   g_notice_id : g_notice_id
		    	    , img_id   	  : img_id 
		          },
			dataType:'text',
		    success: function(result) {
		    /* 	alert(".ajax deleteImage success ..."); 
				alert(".ajax deleteImage data->"+result);   */
				if (result == '1') {
					alert("사진삭제성공 "+result); 
					// 성공하면 아래라인 수행 
					$('#delImage'+p_index).remove();     /*  Delete Tag */
				} else {
			    	alert(".ajax deleteImage 실패하였습니다..."); 
				}
		      
		    }
		});
	 }

</script>
<body>
<div id="img_benner">
		<img src="img/main-picture.png" alt="배너">
	</div>
	<div>
		<h1>고객센터</h1>
			
			<div >
				 <a href="notice">공지사항 </a>
				  <a href="faq" style=" padding-left:50px;">자주 묻는 질문 </a>
				  <a href="inquire" style=" padding-left:50px;">문의하기</a>
			</div>			
						
	</div>
	<hr>
	<form action="updateNotice" method="post"  enctype="multipart/form-data">
		<input type="hidden" name="g_notice_id" value="${notice.g_notice_id }">
		<table style="margin:auto;">

			<tr>
				<th>제목</th>
				<td ><input type="text" name="g_notice_title"
					required="required" value="${notice.g_notice_title }"
					style="width: 700px;"></td>
			</tr>

			<tr>
				<th>게시판종류</th>				
				<td style="text-align: left;">
					<select name="g_common_csboard">
					<option value="not100">공지사항</option>
					<option value="faq100">자주묻는질문</option>										
				</select>
					</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>				
				<textarea 	rows="10" cols="100" name="g_notice_content" id="g_notice_content">
				${notice.g_notice_content}</textarea>	
				</td>
			</tr>

			<tr>
			</tr>
		</table>
		<table style="margin:auto;">
		<tr> 사진 변경</tr>
		<tr>
			<td hidden>번호</td>													
			<td>
			<br>
			 
			<img alt="사진추가 " src="/images/noticeUpload/${savedName}">
			<input type="file" name="file1" multiple="multiple"> <p>
			</td>
			
			
			<td>
			
					<c:forEach items="${imgNotList}" var="notImg" varStatus="status">
				        
				         <input type="hidden" name="g_notice_id" value="${notImg.g_notice_id}">
				         
						<td hidden>${notImg.img_id}</td>
					 	<td  id="delImage${status.index}">
							<c:url value='/display' var='url'>
								<c:param name='file' value='${notImg.img_stored_file}'/>
							</c:url>
			                     <img alt="#" src="${url}"  width="100" height="100">
							
							
					 		<br>
	 				 		<a href="#" 
	 				 		   class="button" 
	 				 		   onclick="deleteImage(${notImg.g_notice_id},${notImg.img_id}, ${status.index})">
	 				 		   	사진삭제
	 				 		</a>
                            											 	
					 	</td>									
				</c:forEach>				
			 </td>		
			</tr>

		</table>
			
				<input type="submit" value="확인">
				<a href="javascript:window.history.back();">수정취소</a>
	</form>
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>