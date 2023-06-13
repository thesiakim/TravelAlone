<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script defer src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<link href="<%=contextPath%>/css/list.css" rel="stylesheet" type="text/css">
<link href="<%=contextPath%>/css/login.css" rel="stylesheet" type="text/css">
<script defer src="<%=contextPath%>/js/mypageMemberInfo.js"></script>
<script defer src="<%=contextPath%>/js/mypage-info-update.js"></script>
</head>
<body>
	<h1>회원 정보 수정</h1>
		<hr>
		<table>
			<tr>
				<th>ID</th>
				<td colspan="2"><input type="text" id="m_email" value="${email}"  readonly></td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td colspan="2"><input type="text" id="m_nickname" value="${nickName}"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td colspan="2"><input type="text" id="m_name" value="${name}" readonly></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td colspan="2"><input type="tel" id="m_phone" value="${phone}"></td>
			</tr>
			<tr>
				<td></td>
				<td style="width: 65px;"></td>
				<td> 
					<button id="saveButton" onclick="saveButton()">저장</button>
				</td>
			</tr>
		</table>
</body>

</html>