<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<link href="css/write.css" rel="stylesheet" type="text/css">
	<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
	<script defer src="/js/pictureUpdate.js"></script>
	<script defer type="text/javascript">
		function deleteBoardImage(board_id, img_id, p_index){
			alert("board_id : "+ board_id);
         
			$.ajax({
				url: "/deleteBoardImg",
				data: {
					board_id : board_id,
					img_id : img_id
				},
				dataType: 'text',
					success: function(resultData) {
					if (parseInt(resultData) > 0) {
						alert("사진삭제 성공"+ resultData);
						$('#delBoardImage'+p_index).remove();
					} else {
						alert("실패")
					}
				}
			});
		}
	</script>
<body>
	<c:import url="boardHeader.jsp"/>
   
	<c:if test="${msg != null }">${msg }</c:if>
   
	<input type="hidden" name="board_id" id="board_id" value="${board.board_id }">
	<input type="hidden" name="user_id" id="user_id" value="${board.member_id }">
	<input type="hidden" name="b_common_board" id="common_board" value="${board.b_common_board }">
		<table>
			<tr>
				<td colspan="5">
					<input type="text" id="title" name="b_title" required="required" value="${board.b_title }">
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<textarea id="content" name="b_content" required="required">${board.b_content }</textarea>
				</td>
			</tr>
			<tr>
				<td style="width: 80px;">
					<label for="title">첨부파일</label>
				</td>
	            <td style="width: 320px;"colspan="4">
					<form enctype="multipart/form-data" id="ImgMulForm">
						<input type="file" multiple="multiple" accept="image/*" class="multipleImage" id="imgFiles" value=""/>
					</form>
	            </td>
			</tr>
			<tr>
				<td style="width: 80px;">
					원래 이미지
				</td>
				<c:forEach items="${listBoardImgs }" var="boardImg" varStatus="status">
					<td id="delBoardImage${status.index }" style="width:10px;">
						<!-- /display 경로를 가지는 URL이 생성되어 url이라는 변수에 저장 -->
						<c:url value="/display" var="url">
							<c:param name="file" value="${boardImg.img_stored_file }"/>
						</c:url>
						<img alt="imege 없음" src="${url}" width="25%" height="25%">
						<br>
						<input type="button" onclick="deleteBoardImage(${boardImg.board_id}, ${boardImg.img_id}, ${status.index })" value="삭제">
					</td>   
				</c:forEach>
			</tr>
			<tr>
				<td colspan="5" id="updateFormSubmit">
					<input type="submit" id="btn-update" value="수정">
					<input type="reset" value="취소">         
				</td>
			</tr>   
		</table>
</body>
	<c:import url="../fragments/footer.jsp"/>
</html>