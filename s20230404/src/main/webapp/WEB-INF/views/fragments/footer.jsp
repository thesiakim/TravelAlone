<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <form>
	<div id="container-left clearfix">
	  <div class="footerForm">
		  <c:if test="${user.role == 'rol200' }">
			 <a href="admin">관리자페이지</a>
			    </c:if>
		<a href="notice">고객센터</a>
	  </div>
	  <div class="footerInfo">
		<p>서울시 이대 중앙학원 501호</p><br>
		<p>모든 저작권은 중앙정보처리 회사에 있습니다</p><br>
		<p>010-1234-5678</p>
	  </div>
	</div>
  </form>
</body>
</html>