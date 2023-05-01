<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp" %>
<c:forEach items="${travelList }" var="travel" varStatus="status">	
	<div>
		<p>${travel.t_name }</p>
		<p>${travel.formattedCreateDate }</p>
		<p>${travel.t_content }</p>
	</div>

</c:forEach>

</body>
</html>