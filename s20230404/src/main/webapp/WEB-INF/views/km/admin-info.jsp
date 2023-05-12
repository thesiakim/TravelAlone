<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- <link href="/css/login.css" rel="stylesheet" type="text/css"> -->

<STYLE>


	button {
		width: 100px;         border-radius: 7px;
		height: 40px;         box-sizing: border-box;
		font-size: 15px;        font-weight:bolder;
		color:white;
		border:  #205E61;       background-color: #205E61;
	}
	button:hover {
		color: #205E61;
		background-color:  white;
		transition: ease 0.5s;
	}



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
		width: 300px;
		height: 300px;
		border-radius: 50%;
		object-fit: cover;
		margin-bottom: 20px;
	}
	.form-block{
		display: flex;
		flex-direction: row;

	}
	.button-block {
		text-align: center;
		 color: #205E61;
	  font-weight: bold;
	  box-sizing: border-box;
	  background-color: #F8F8F8;
	  
	}
</STYLE>
<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<script defer>
	var originalPictureSrc = "${member.imgStoredFile}";
	var normalPicture = false;


	function normalPictureChange() {
		if (confirm("기본이미지로 수정하시겠습니까?")) {
			normalPicture = true;
			document.getElementById('form').reset();

			$('.profile-pic').attr('src', "http://localhost:4040/display?file=src/main/resources/static/img/user-picture.png");
		}
	}
	function originalPictureChange(){
		normalPicture = false;
		document.getElementById('form').reset();

		$('.profile-pic').attr('src', 'http://localhost:4040/display?file='+originalPictureSrc);
	}

	function saveButton(id) {

		var data = {
				id: id,
				phone: $('#phone').val(),
				nickname: $('#nickname').val(),
				name: "${member.name}",
				checked: normalPicture
			};

		var form = $('#form')[0];
		var formData = new FormData(form);
		if(document.getElementById('file').files.length){
			formData.append("file", document.getElementById('file').files[0]);
		}
		formData.append('key', new Blob([JSON.stringify(data)], {type: "application/json"}));

		$.ajax({
			type: 'PATCH',
			url: '/api/v1/admin/info',
			processData: false,
			contentType: false,
			data: formData
		})
				.done(function () {
					alert("사용자 정보가 수정되었습니다.");
					window.close(); // 현재 창 닫기
					window.opener.location.reload(); // 부모 창 새로 고치기
				})
				.fail(function (error) {
					alert(JSON.stringify(error));
				});
	}

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
					src="/display?file=${member.imgStoredFile}"
					alt="#"
			/>
		</div>
		<div class="form-block">
			<form enctype="multipart/form-data" id="form">
				<input type="file" id="file" onchange="refresh(event)">
			</form>
		</div>
			<button type="button" onclick="normalPictureChange()">사진 기본값 설정</button>
			<button type="button" onclick="originalPictureChange()">현재사진 복원</button>


		<p><strong>ID:</strong> ${member.id}</p>

		<p><strong>Email:</strong> ${member.email}</p>

		<label for="nickname">Nickname:</label>
		<input type="text" id="nickname" name="nickname" value="${member.nickname}"><br>

		<p><strong>Name:</strong> ${member.name}</p>

		<p><strong>Gender:</strong> ${member.gender}</p>

		<label for="phone">Phone:</label>
		<input type="tel" id="phone" name="phone" value="${member.phone}"><br>

		<p><strong>Role:</strong> ${member.role}</p>

		<p><strong>가입일:</strong> ${member.createdDate}</p>

		<p><strong>생성일:</strong> ${member.modifiedDate}</p>

		<div class="button-block">
			<a>
			<button id="saveButton" onclick="saveButton(${member.id})">수정</button>
			</a>
			<a>
			<button id="cancelButton" onclick="window.close()">취소</button>
			</a>
		</div>
	</div>
</div>

</body>

</html>