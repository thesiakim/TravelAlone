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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link href="<%=contextPath%>/css/list.css" rel="stylesheet" type="text/css">
	<link href="<%=contextPath%>/css/login.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
<!-- 이메일 인증 -->	
<script type="text/javascript"> 
 /* 회원가입 시 본인인증 요청 */
 $(document).ready(function() {
	   var isAuthComplete = false; // 인증 완료 여부 변수

	   $('#mail-Check-Btn').click(function() {
	     var email = $('#email').val(); 
	     console.log(email);
	     alert('인증 번호를 전송 중입니다.');
         $.ajax({
	       type: 'POST', 
	       url: '/checkEmail',
	       data: {'email': email}, 
	       dataType: 'json', 
	       success: function(data) { 
	    	 alert('인증 번호를 전송했습니다.')
	         if (data.result == 'success') {
	           $('.mail-check-input').prop('disabled', false); 
	           isAuthComplete = true; // 인증 완료 저장 저장
	         } else {
	           $('#mail-check-warn').text('인증번호 전송에 실패했습니다. 이메일을 확인해주세요.'); 
	         }
	       },
	       error: function(xhr, status, error) { 
	         console.log(xhr.responseText);
	       }
	     });
	   });

	   
	   /* 회원가입 시 인증코드 일치 여부 확인 */
	   $('#code-Check-Btn').click(function() {
	     var authCode = $('.mail-check-input').val(); 
	     console.log(authCode);
	     
	     $.ajax({
	       type: 'POST', 
	       url: '/checkAuthCode', 
	       data: {'authCode': authCode}, 
	       dataType: 'json', 
	       success: function(data) { 
	         if (data.result == 'success') {
	           $('.mail-check-input').prop('disabled', true); 
	           $('#mail-Check-Btn').prop('disabled', true); 
	           $('#mail-check-warn').text('인증이 완료되었습니다.').removeClass('error-msg'); 
	         } else {
	           $('.mail-check-input').val(''); 
	           $('#mail-check-warn').text('인증번호가 일치하지 않습니다.').addClass('error-msg'); 
	           
	           alert('다시 인증해주세요');
	         }
	       }
	     });
	   });

	   $('.btn-outline-dark').click(function() {
		   if (!isAuthComplete || $('.mail-check-input').val().length !== 6) { // 인증이 완료되지 않은 경우
	       alert('인증을 해주세요.'); 
	       return false; 
	     } else { // 인증이 완료된 경우
	     }
	   });
	});

</script>
	
<!-- 비밀번호 확인 -->
<script type="text/javascript">

	String.prototype.trim = function() {
	    return this.replace(/^\s+|\s+$/g,"");
	}

	//회원가입 실패시 에러 메시지 출력
	$(document).ready(function(){
		var errorMessage = [[${errorMessage}]].trim;
		if(errorMessage != null || typeof errorMessage != "undefined"){
			alert(errorMessage);
		}
	});
	
	//비밀번호 일치 여부 확인
	function checkPass() {
		const password = document.querySelector("#password").value;
		const passwordCheck = document.querySelector("#password-check");

		if (password === passwordCheck.value && passwordCheck.value !== '') {
			alert("비밀번호가 일치합니다.");
		      return true;
		    } else {
		      	alert("비밀번호가 일치하지 않습니다.");
		      	passwordCheck.value = "";
		      	return false;
		    }
		}
	

	//가입 버튼 클릭 시 비밀번호 일치 여부 확인
	function checkPassword() {
		const password = document.querySelector("#password").value;
		const passwordCheck = document.querySelector("#password-check");

		if (password === passwordCheck.value && passwordCheck.value !== '') {
			alert("회원 가입이 완료되었습니다.");
		      return true;
		    } else {
		      	alert("비밀번호가 일치하지 않습니다.");
		      	passwordCheck.value = "";
		      	return false;
		    }
		}
	
</script>
</head>
<body>
	<div class="loginLogo">
		<a href="/"><img src="/img/gosunee.png"></a>
	</div>
	
	<form action="http://localhost:4040/api/v1/join" role="form" method="post" onsubmit="return checkPassword();">
		<table>
			<tr class="form-group py-2">
				<td><label for="email">이메일 아이디</label></td>
				<td colspan="2"><input type="email" id="email" name="email" class="form-control" placeholder="이메일 아이디를 입력해주세요 (필수)" value="${memberDto.email}"/></td>
				<td><button type="button" class="btn btn-primary" id="mail-Check-Btn">본인인증</button></td>
				<td>  </td>
			</tr>
			<tr> 
				<td>  </td>
				<td class="mail-check-box"><input class="form-control mail-check-input" placeholder="인증번호 6자리를 입력해주세요" disabled="disabled" maxlength="6"></td>
				<td>  </td>
				<td><button type="button" class="btn btn-check" id="code-Check-Btn">인증하기</button></td>
				<td><span id="mail-check-warn" class="success-msg"></span>
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
			
			<tr>
				<td><label for="password">비밀번호 확인</label></td>
				<td colspan="2"><input type="password" id="password-check" name="password-check" class="form-control" placeholder="비밀번호를  확인해주세요"/></td>
				<td><button type="button" class="checkPass" onclick="checkPass()">확인</button></td>
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
			   
			       	<span>여자</span>
			       	<input type="radio" id="gender-female" name="gender" value="1" class="form-control" checked="${memberDto.gender == 1}"/>
			   
			    </td>
			    <td>
			   	</td>
			   	<td>  </td>
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
				<td style="width: 80px;">
					<button type="submit" class="btn btn-outline-dark">가 입</button>
					<button type="button" class="btn btn-outline-dark-cancel" onclick="history.back();">취 소</button>
				</td>
                <td></td>
			</tr>
    	</table>
	</form>
</body>
</html>