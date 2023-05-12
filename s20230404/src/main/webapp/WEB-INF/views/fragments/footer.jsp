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
<style>
  #container-left {
    padding: 0 250px;
    background-color: #F8F8F8;
    font-size: 15px;
    font-color : 205E61;
  }
  
</style>
</head>
<body>
	
	
	
  <form>
	<div id="container-left" class="clearfix">
 	  <div class="footerForm">	
 		 <div style="float: right;">  
 		<div style='font-size:20px;  font-weight: border;'>
 		<br>
 		<a href="http://localhost:4040/service.html">서비스 약관</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="http://localhost:4040/personalData.html" style="margin-right: 75px;">개인정보 처리방침</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<c:if test="${user.role == 'rol200' }">
      <a href="admin" style="margin-left: 150px;">관리자페이지</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          </c:if>
      <a href="notice">고객센터</a>
	  </div>
	  </div>
	  </div>
	  
	  <div class="footerInfo">
	  <div style="float: left;">
	  <div style='font-size:12px; text-align: left; '>
	  <br>
	  
		서울특별시 마포구 신촌로 176 501호<br>
		02-123-4567<br>
		Copyright ⓒ 2023 Nahonyeo All Rights Reserved.

		</div>
		</div>
		</div>
<br>
<br>
<br>
<br>


<br>
<br>
<br>
	  </div>
	  </div>
	  </div>
  </form>
</body>
</html>