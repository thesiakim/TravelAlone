<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<link href="<%=contextPath%>/css/list.css" rel="stylesheet" type="text/css">
	<link href="<%=contextPath%>/css/login.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<c:set var="errorMessage" value="${errorMessage}"/>
	<c:if test="${not empty errorMessage}">
	    <script type="text/javascript">
			$(document).ready(function(){
				var errorMessage = '<c:out value="${errorMessage}"/>';
				if(errorMessage != null){
					alert(errorMessage);
				}
			});
	    </script>
	</c:if>
<body>
	<div class="loginLogo">
		<a href="<%=contextPath%>/"><img src="<%=contextPath%>/img/Logo.png"></a>
	</div>
    <form role="form" method="post" action="${ctx}/login">
		<table>
			<tr class="form-group py-2">
				<td style="width: 100px;"><label for="email">이메일 주소</label></td>
				<td colspan="3" style="width: 300px;"><input type="email" name="email" class="form-control" placeholder="이메일을 입력해주세요"></td>
			</tr>
	        
	        <tr class="form-group py-2">
				<td style="width: 100px;"><label for="password">비밀번호</label></td>
				<td colspan="3" style="width: 300px;"><input type="password" name="password" class="form-control" placeholder="비밀번호 입력"></td>
			</tr>
	        <tr>
				<c:if test="${not empty loginErrorMsg}">
					<td colspan="4"><p class="error" style="color: red; margin-left: 100px; font-weight:bolder;"><c:out value="${loginErrorMsg}" /></p></td>
				</c:if>
			</tr>
			<tr class="py-3">
				<td></td>
				<td style="width: 80px;"><button type="submit" class="btn btn-outline-dark">로그인</button></td>
				<td style="width: 80px;"><button type="button" class="btn btn-outline-dark" onclick="location.href='<%=contextPath%>/join'">회원가입</button></td>
				<td></td>
			</tr>
		</table>
    </form>
</body>
</html>