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
	<link href="/css/write.css" rel="stylesheet" type="text/css">
	<link href="/css/list.css" rel="stylesheet" type="text/css">
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
	<h1>고객센터</h1>
		<a href="notice">공지사항 </a>
		<a href="faq" style=" padding-left:50px;">자주 묻는 질문 </a>
		<a href="inquire" style=" padding-left:50px;">문의하기</a>
		
	<hr>
	
	<form action="updateNotice" method="post"  enctype="multipart/form-data">
		<input type="hidden" name="g_notice_id" value="${notice.g_notice_id }">
		<table>

			<tr>
				<td colspan="3">
					<input type="text" name="g_notice_title" required="required"style="width: 600px; height: 25px;"  value="${notice.g_notice_title }">
				</td>
			</tr>

			<tr>
				<td style="width: 120px;">게시판종류</td>				
				<td style="width: 320px;">
					<select name="g_common_csboard">
						<option value="not100">공지사항</option>
						<option value="faq100">자주묻는질문</option>										
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="3">				
					<textarea required="required" name="g_notice_content" id="g_notice_content" style="width: 600px;
						height: 500px; font-size: 15px; text-align: left;">
						${notice.g_notice_content}
					</textarea>	
				</td>
			</tr>
			<tr>
				<td>
					<img alt="사진추가 " src="/images/noticeUpload/${savedName}">
				</td>		
				<td>	 
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
	 				 		<a href="#"  class="button" 
	 				 		   onclick="deleteImage(${notImg.g_notice_id},${notImg.img_id}, ${status.index})">
	 				 		   	사진삭제
	 				 		</a>
					 	</td>									
				</c:forEach>				
			 </td>		
			</tr>
		</table>
		<br>
		<input type="submit" value="확인">
		<a href="javascript:window.history.back();"><input type="submit" value="취소"></a>
	</form>
	
	<br><br>
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>