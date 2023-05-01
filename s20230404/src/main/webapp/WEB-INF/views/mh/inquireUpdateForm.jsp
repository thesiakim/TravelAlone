<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	function deleteImage(house_id, img_id, p_index) {
	/* 	alert("house_id:"   + house_id);
		alert("img_id:"	    + img_id);
		alert("p_index:"	+ p_index); */
		
		$.ajax({
		    url: "<%=context%>/deleteHouImg",
		    data: {   house_id : house_id
		    	    , img_id   : img_id 
		          },
			dataType:'text',
		    success: function(result) {
		   /*  	alert(".ajax deleteImage success ..."); 
				alert(".ajax deleteImage data->"+result);  */
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
<h1>문의게시판 글수정</h1>

	<form action="updateInquire" method="post">
		<input type="hidden" name="g_writing_id" value="${inquire.g_writing_id }">
		<table>
			<tr>
				<th>글아이디</th>
				<td>${inquire.g_writing_id }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="g_title"
					required="required" value="${inquire.g_title }"></td>
			</tr>
			
			<tr>
				<th>게시판종류</th>
				<td><input type="text" name="g_common_csboard"
					required="required" value="${inquire.g_common_csboard }"></td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td><input type="text" name="g_passwd"
					required="required" value="${inquire.g_passwd }"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><input type="text" name="g_content"
					id="g_content" value="${inquire.g_content }">
				</td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="확인"></td>
			</tr>
		</table>
	</form>
</body>
</html>