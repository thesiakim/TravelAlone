<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<STYLE>
	.profile-block {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin: 20px;
		padding: 20px;
		border: 1px solid #ddd;
		border-radius: 5px;
		background-color: #f9f9f9;
	}
	.profile-pic {
		width: 100%;
		height: 100%;
		border-radius: 50%;
		object-fit: cover;
		margin-bottom: 20px;
	}
	.button-block {
		text-align: center;
	}
</STYLE>
<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<script defer>
	function saveButton() {

			var form = $('#form')[0];
			var formData = new FormData(form);

			formData.append("file", document.getElementById('file').files[0]);

			$.ajax({
				type: 'POST',
				url: '/api/v1/mypage/profile',
				processData: false,
				contentType: false,
				data: formData,

			})
					.done(function (responseText) {
						alert(responseText+"! 수정되었습니다.");
						window.close(); // 현재 창 닫기
						window.opener.location.reload(); // 부모 창 새로 고치기
					})
					.fail(function (error) {
						alert(JSON.stringify(error));
					});

	};

	function refresh() {
		var reader = new FileReader();
		reader.onload = function (e) {
			$('.profile-pic').attr('src', e.target.result);
		}
		reader.readAsDataURL(event.target.files[0]);
	}
</script>
</head>
<body>
<div class="profile-block">
<div class="img-block">
	<div>
		<img
				class="profile-pic"
				src="/display?file=${storedImgName}"
				alt="#"
		/>
	</div>
	<form enctype="multipart/form-data" id="form">
		<input type="file" id="file" onchange="refresh(event)">
	</form>
	<div class="button-block">
		<button id="saveButton" onclick="saveButton()">수정</button>
		<button id="cancelButton" onclick="window.close()">취소</button>
	</div>
</div>
</div>
</body>

</html>