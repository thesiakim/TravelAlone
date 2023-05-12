<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="<%=contextPath%>/css/list.css" rel="stylesheet" type="text/css">
	<link href="<%=contextPath%>/css/login.css" rel="stylesheet" type="text/css">
<!-- 사용자 스크립트 추가 -->
<script type="text/javascript">
	//회원가입 실패시 에러 메시지 출력
	$(document).ready(function(){
		var errorMessage = [[${errorMessage}]];
		if(errorMessage != null){
			alert(errorMessage);
		}
	});
	function checkedPasswd(){
		// 비밀번호 확인
		var pswd1 = $(document).querySelector('#pwsd1').get(0).value;
		var pswd2 = $(document).querySelector('#pwsd2').get(0).value;
		console.log(pswd1 +'+'+ pswd2);
		if (pswd1 != pswd2){
			$(document).querySelector('#pswdMessage').style('display:');
			$(document).querySelector('#pswdMessage').text = "비밀번호가 일치하지 않습니다.";
		}else{
			$(document).querySelector('#pswdMessage').style('display:none');
		}
	}
</script>
</head>
<body>
	<div class="loginLogo">
		<a href="/"><img src="<%=contextPath%>/img/gosunee.png"></a>
	</div>
	
	<form action="<%=contextPath%>/api/v1/join" role="form" method="post">
		<table>
			<tr class="form-group py-2">
				<td><label for="email">이메일 아이디</label></td>
				<td colspan="2"><input type="email" id="email" name="email" class="form-control" placeholder="이메일 아이디를 입력해주세요 (필수)" value="${memberDto.email}"/></td>
			</tr>
			
			<c:if test="${not empty requestScope['org.springframework.validation.BindingResult.memberDto'] and requestScope['org.springframework.validation.BindingResult.memberDto'].hasFieldErrors('email')}">
				<tr>
					<td colspan="3">
					    <p class="fieldError" style="color:red;"><c:out value="${requestScope['org.springframework.validation.BindingResult.memberDto'].getFieldError('email').defaultMessage}" /></p>
					</td>
				</tr>
			</c:if>
			
			<tr class="form-group py-2">
				<td><label for="nickName">닉네임</label></td>
				<td colspan="2"><input type="text" id="nickName" name="nickName" class="form-control" placeholder="닉네임을 입력해주세요" value="${memberDto.nickName}"/></td>
			</tr>
			
			<c:if test="${not empty requestScope['org.springframework.validation.BindingResult.memberDto'] and requestScope['org.springframework.validation.BindingResult.memberDto'].hasFieldErrors('nickName')}">
				<tr>
					<td colspan="3">
					    <p class="fieldError" style="color:red;"><c:out value="${requestScope['org.springframework.validation.BindingResult.memberDto'].getFieldError('nickName').defaultMessage}" /></p>
					</td>
				</tr>
			</c:if>
				
			<tr class="form-group py-2">
				<td><label for="password">비밀번호</label></td>
				<td colspan="2"><input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력해주세요 (필수)" value="${memberDto.password}"/></td>
			</tr>
			
			
			<c:if test="${not empty requestScope['org.springframework.validation.BindingResult.memberDto'] and requestScope['org.springframework.validation.BindingResult.memberDto'].hasFieldErrors('password')}">
				<tr>
					<td colspan="3">
					    <p class="fieldError" style="color:red;"><c:out value="${requestScope['org.springframework.validation.BindingResult.memberDto'].getFieldError('password').defaultMessage}" /></p>
					</td>
				</tr>
			</c:if>
			
			<tr class="form-group py-2">
				<td><label for="name">이름</label></td>
				<td colspan="2"><input type="text" id="name" name="name" class="form-control" placeholder="이름을 입력해주세요 (필수)" value="${memberDto.name}"/></td>
			</tr>
			
			<c:if test="${not empty requestScope['org.springframework.validation.BindingResult.memberDto'] and requestScope['org.springframework.validation.BindingResult.memberDto'].hasFieldErrors('name')}">
				<tr>
					<td colspan="3">
					    <p class="fieldError" style="color:red;"><c:out value="${requestScope['org.springframework.validation.BindingResult.memberDto'].getFieldError('name').defaultMessage}" /></p>
					</td>
				</tr>
			</c:if>
			
			<tr class="form-group py-2">
				<td><label for="gender">성별</label></td>
				<td>
					<span>남자</span>
			       	<input type="radio" id="gender-male" name="gender" value="0" class="form-control" checked="${memberDto.gender == 0}"/>
			    </td>
			    <td>
			       	<span>여자</span>
			       	<input type="radio" id="gender-female" name="gender" value="1" class="form-control" checked="${memberDto.gender == 1}"/>
			   	</td>
			</tr>
			
			<c:if test="${not empty requestScope['org.springframework.validation.BindingResult.memberDto'] and requestScope['org.springframework.validation.BindingResult.memberDto'].hasFieldErrors('gender')}">
				<tr>
					<td colspan="3">
					    <p class="fieldError" style="color:red;"><c:out value="${requestScope['org.springframework.validation.BindingResult.memberDto'].getFieldError('gender').defaultMessage}" /></p>
					</td>
				</tr>
			</c:if>
			
			<tr class="form-group py-2">
				<td><label for="phone">전화번호</label></td>
				<td colspan="2"><input type="text" id="phone" name="phone" class="form-control" placeholder="전화번호를 '-'를 제외하고 입력해주세요" value="${memberDto.phone}"/></td>
			</tr>
			
			<c:if test="${not empty requestScope['org.springframework.validation.BindingResult.memberDto'] and requestScope['org.springframework.validation.BindingResult.memberDto'].hasFieldErrors('phone')}">
				<tr>
					<td colspan="3">
					    <p class="fieldError" style="color:red;"><c:out value="${requestScope['org.springframework.validation.BindingResult.memberDto'].getFieldError('phone').defaultMessage}" /></p>
					</td>
				</tr>
			</c:if>
			
			
			<tr style="text-align:center" class="py-3">
				<td></td>
				<td style="width: 80px;"><button type="submit" class="btn btn-outline-dark">가 입</button></td>
				<td><button type="button" class="btn btn-outline-dark" onclick="history.back();">취 소</button></td>
			</tr>
    	</table>
	</form>
</body>
</html>