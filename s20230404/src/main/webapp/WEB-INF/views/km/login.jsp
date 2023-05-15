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
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">

<script type="text/javascript">
	//로그인 실패시 에러 메시지 출력
	$(document).ready(function(){
		var errorMessage = ${errorMessage};
		if(errorMessage != null){
		alert(errorMessage);
		}
	});
</script>
<body>
	<div class="loginLogo">
		<a href="<%=contextPath%>/"><img src="<%=contextPath%>/img/Logo.png"></a>
	</div>
	<form role="form" method="post" action="<%=contextPath%>/login">
		<table>
			<tr class="form-group py-2">
				<td style="width: 100px;"><label for="email">이메일 주소</label></td>
				<td colspan="3" style="width: 300px;"><input type="email" style="width: 330px" name="email" class="form-control" placeholder="이메일을 입력해주세요"></td>
			</tr>
			<tr class="form-group py-2">
				<td style="width: 100px;"><label for="password">비밀번호</label></td>
				<td colspan="3" style="width: 300px;"><input type="password" style="width: 330px" name="password" class="form-control" placeholder="비밀번호 입력"></td>
			</tr>
			<tr>
				<div>
				<c:if test="${loginErrorMsg != null}">
					<td colspan="4"><p class="error" style="color: red; margin-left: 180px; font-weight:bolder; "><c:out value="${loginErrorMsg}" /></p></td>
				</c:if>
				</div>
			</tr>
			<tr class="py-3">
				<td></td>
				<td style="width: 150px;" colspan="3"><button type="submit" style="width: 330px" class="btn btn-outline-dark">로그인</button></td>
				<td></td>
			</tr>
			<tr class="py-3">
				<td></td>
			<td style="width: 100px;"><button type="button" style="width: 100px" class="btn btn-outline-dark btn-find" onclick="location.href='<%=contextPath%>/join'">회원가입</button></td>
			<td style="width: 100px;"><button type="button" style="width: 100px" class="btn btn-outline-dark btn-find" onclick="location.href='<%=contextPath%>/id'">아이디 찾기</button></td>
			<td style="width: 100px;"><button type="button" style="width: 100px" class="btn btn-outline-dark btn-find" onclick="location.href='<%=contextPath%>/password'">비밀번호 찾기</button></td>
			</tr>
		</table>
	</form>
</body>
</html>