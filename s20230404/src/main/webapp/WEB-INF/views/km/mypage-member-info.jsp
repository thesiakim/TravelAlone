<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<script defer>
	function saveButton() {
		var data = {
			m_email: $("#m_email").val(),
			m_nickname: $("#m_nickname").val(),
			m_name: $("#m_name").val(),
			m_phone: $("#m_phone").val()
		};

		$.ajax({
			type: "POST",
			url: "/api/v1/mypage",
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
		})
				.done(function () {
					alert("수정되었습니다.");
				})
				.fail(function (error) {
					alert(JSON.stringify(error));
				});

	};
</script>
</head>
<body>

<h1>회원 정보 수정</h1>
	<table>
		<tr>
			<th>ID</th>
			<td><input type="text" id="m_email" value="${email}"  readonly></td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td><input type="text" id="m_nickname" value="${nickName}"></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" id="m_name" value="${name}" readonly></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="tel" id="m_phone" value="${phone}"></td>
		</tr>
	</table>
	<button id="saveButton" onclick="saveButton()">저장</button>
</form>
</body>


</body>

</html>