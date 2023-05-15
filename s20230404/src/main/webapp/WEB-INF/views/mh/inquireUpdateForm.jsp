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
	function deleteImage(g_writing_id, img_id, p_index) {
		alert("p_index:"	+ p_index);  
		
		$.ajax({
		    url: "<%=context%>/deleteInqImg",
		    data: {   g_writing_id 	: g_writing_id
		    	    , img_id   		: img_id 
		          },
			dataType:'text',
		    success: function(result) {
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
	
	<form action="updateInquire" method="post" enctype="multipart/form-data">
		<input type="hidden" name="g_writing_id" value="${inquire.g_writing_id }">
		<table>
			
			<tr>
				<td colspan="3">
					<input type="text" name="g_title" required="required" style="width: 600px; height: 25px;" value="${inquire.g_title }">
				</td>
			</tr>
			
			<tr>
				<td style="width: 120px;">문의글 종류</td>
				<td style="width: 320px;">	
					<select name="g_common_csboard">
						<option value="inq100">여행지문의</option>
						<option value="inq200">숙소문의</option>
						<option value="inq300">맛집문의</option>
						<option value="inq400">기타문의</option>
					</select>					
				</td>
				<td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="text" name="g_passwd" size="10"	required="required" 
				 		   placeholder="숫자 4자리 입력" value="${inquire.g_passwd }" style="height: 25px;">
				</td>
			</tr>
			<tr>
				<td colspan="3">					
					<textarea required="required" name="g_content" id="g_content" style="width: 600px;
						height: 500px; font-size: 15px; text-align: left;">
						${inquire.g_content }
					</textarea>
				</td>
			</tr>
			<tr>
				<td>
					<img alt="사진추가 " src="/images/inquireUpload/${savedName}">
				</td>
				<td>
					<input type="file" name="file1" multiple="multiple"> <p>
				</td>								
				<td>				
					<c:forEach items="${imgInqList}" var="inqImg" varStatus="status">
							<input type="hidden" name="house_id" value="${inqImg.g_writing_id}">
						<td hidden>${inqImg.img_id}</td>
					 	<td  id="delImage${status.index}">
							<c:url value='/display' var='url'>
								<c:param name='file' value='${inqImg.img_stored_file}'/>
							</c:url>
							<img alt="#" src="${url}"  width="100" height="100">
					 		<a href="#" class="button" 
					 		   onclick="deleteImage(${inqImg.g_writing_id},${inqImg.img_id}, ${status.index})">
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