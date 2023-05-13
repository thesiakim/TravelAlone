<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 사용자 스크립트 추가 -->
</head>
	<link href="<%=contextPath%>/css/list.css" rel="stylesheet" type="text/css">
	<link href="<%=contextPath%>/css/login.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script defer type="text/javascript">
	function passwordChecked() {
		const password = document.querySelector("#password").value;
		const passwordCheck = document.querySelector("#passwordCheck");

		if (password === passwordCheck.value && passwordCheck.value !== '') {
			if (confirm("비밀번호가 일치합니다. 비밀번호를 새로 변경하시겠습니까?")){
				alert("변경되었습니다.")
				return true;
			}
			return false;
		} else {
			alert("비밀번호가 일치하지 않습니다.");
			passwordCheck.value = "";
			return false;
		}
	}
</script>
<body>
<h1>비밀번호 변경</h1>
<form action="<%=contextPath%>/api/v1/password/${memberId}" method="post" onsubmit="return passwordChecked()">
	<p>
		<label for="email">아이디</label>
		<input type="text" name="email" id="email" value="${info.email}" readonly>
	</p>
	<p>
		<label for="name">이름</label>
		<input type="text" name="name" id="name" value="${info.name}" readonly>
	</p>
	<p>
		<label for="phone">휴대폰 번호</label>
		<input type="tel" name="phone" id="phone" value="${info.phone}" readonly>
	</p>
	<p>
		<label for="password">새 비밀번호</label>
		<input type="password" name="password" id="password" required>
	</p>
	<p>
		<label for="passwordCheck">새 비밀번호 확인</label>
		<input type="password" name="passwordCheck" id="passwordCheck" required>
	</p>
	<p>
		<input type="submit" value="비밀번호 찾기">
	</p>
</form>
</body>
</html>