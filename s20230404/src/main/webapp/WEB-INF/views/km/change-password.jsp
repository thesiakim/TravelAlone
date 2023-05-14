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
	<table>
	<tr>
		<td><label for="email">아이디</label></td>
		<td><input type="text" name="email" id="email" value="${info.email}" readonly></td>
	</tr>
	<tr>
		<td><label for="name">이름</label></td>
		<td><input type="text" name="name" id="name" value="${info.name}" readonly></td>
	</tr>
	<tr>
		<td><label for="phone">휴대폰 번호</label></td>
		<td><input type="tel" name="phone" id="phone" value="${info.phone}" readonly></td>
	</tr>
	<tr>
		<td><label for="password">새 비밀번호</label></td>
		<td><input type="password" name="password" id="password" required></td>
	</tr>
	<tr>
		<td><label for="passwordCheck">새 비밀번호 확인</label></td>
		<td><input type="password" name="passwordCheck" id="passwordCheck" required></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" style="width: 275px; height: 30px" value="비밀번호 변경"></td>
	</tr>
	</table>
</form>
</body>
</html>