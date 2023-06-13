<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../fragments/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 목록</title>

	<link rel="stylesheet" href="<%=contextPath%>/css/mypage.css">
	<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
	
	<style>
		.search-container{
			display: flex;
			flex-direction: column;
			align-items: center;
			margin-bottom: 40px;

		}
		.search-input{
			text-align: center;
			width: 300px;
			height: 30px;
		}
		.search-button{
			width: 60px;
			height: 30px;
		}
		.table-container{
			display: flex;
			flex-direction: column;
			align-items: center;

		}
		table{
			border: 1px solid #ddd;
		}
		thead{
			border: 1px solid #ddd;
			background-color: whitesmoke;
		}
		tbody tr:hover{
			color: white;
			background-color: #5F9EA0;
		}
		tbody{
			font-weight: normal;
			color: black;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
	<script>

		function changeRole(id){
			if (confirm("권한을 변경하시겠습니까?")){

				var data = {
					id : id,
					role : $("#selectRole"+id).val()
				}


				$.ajax({
					type: "PATCH",
					url: "/api/v1/admin/role",
					dataType: "text",
					contentType: "application/json; charset=utf-8",
					data: JSON.stringify(data),
				})
						.done(function (responseText) {
							alert("회원 ID :"+responseText+" 수정되었습니다.");
							window.location.reload();
						})
						.fail(function (error) {
							alert(JSON.stringify(error));
						});
			}else{
				window.location.reload();
			}
		}

		function detailMove(id){

				// 수정창을 띄울 URL 설정
				var editUrl = "/admin/info/" + id;

				// 미니창으로 수정창을 띄우기
				var editWindow = window.open(editUrl, "edit_window", "width=600,height=700");


		}
	</script>
</head>
<body>

<div id="img_benner">
      <img src="img/house-picture.png" alt="배너">
   </div>
<h1 class="title">회원 목록</h1>
<div class="search-container">
	<form action="<%=contextPath%>/admin" method="GET">
		<div id="serch">
		<input	 type="text" name="search" placeholder="회원 이메일로 검색하세요" class="search-input" value="${param.search}">
		</div>
	</form>
</div>

<div class="table-container">
	<table>
		<thead>
		<tr>
		
			<th width="250px;" style="color: white; background-color: #5F9EA0;">Email</th>
			<th width="140px;"style="color: white; background-color: #5F9EA0;">Nickname</th>
			<th width="140px;"style="color: white; background-color: #5F9EA0;">Name</th>
			<th  width="140px;" style="color: white; background-color: #5F9EA0;">Gender</th>
			<th width="200px;" style="color: white; background-color: #5F9EA0;">Phone</th>
			<th style="color: white; background-color: #5F9EA0;">Created Date</th>
			<th style="color: white; background-color: #5F9EA0;">Modified Date</th>
			<th style="color: white; background-color: #5F9EA0;">Warning</th>
			<th  width="100px;" style="color: white; background-color: #5F9EA0;">Role</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="member" items="${members}">
			<tr>
				
				<td width="250px;" onclick="detailMove(${member.id})">${member.email}</td>
				<td width="140px;" onclick="detailMove(${member.id})">${member.nickname}</td>
				<td width="140px;" onclick="detailMove(${member.id})">${member.name}</td>
				<td  width="140px;"  onclick="detailMove(${member.id})">${member.gender}</td>
				<td width="200px;" onclick="detailMove(${member.id})">${member.phone}</td>
				<td onclick="detailMove(${member.id})">${member.createdDate}</td>
				<td onclick="detailMove(${member.id})">${member.modifiedDate}</td>
				<td onclick="detailMove(${member.id})">${member.warningCount}</td>
				<td  width="100px;" id="role">
					<select name="role" id="selectRole${member.id}" onchange="changeRole(${member.id})">
						<option value="user" ${member.role == ('rol100') ? 'selected' : ''}>User</option>
						<option value="admin" ${member.role == ('rol200') ? 'selected' : ''}>Admin</option>
						<option value="ban" ${member.role == ('rol300') ? 'selected' : ''}>Ban</option>
					</select>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

<br><br>
<div class="page-button">
	<c:choose>
		<c:when test="${not empty param.search}">
			<c:set var="queryString" value="&search=${param.search}" />
		</c:when>
		<c:otherwise>
			<c:set var="queryString" value="" />
		</c:otherwise>
	</c:choose>

	<c:set var="startPage" value="${currentPage - 5}" />
	<c:if test="${startPage < 0}">
		<c:set var="startPage" value="0" />
	</c:if>

	<c:set var="endPage" value="${currentPage + 5}" />
	<c:if test="${endPage >= totalPage}">
		<c:set var="endPage" value="${totalPage-1}" />
	</c:if>

<%-- 	<c:choose>
		<c:when test="${currentPage > 0}">
			<a href="?page=${currentPage - 1}${queryString}">이전</a>
		</c:when>
		<c:otherwise>
			<span class="disabled">이전</span>
		</c:otherwise>
	</c:choose> --%>

	<c:forEach var="page" begin="${startPage}" end="${endPage}">
		<c:choose>
			<c:when test="${page == currentPage}">
				<span class="current">[${page+1}]</span>
			</c:when>
			<c:otherwise>
				<a href="?page=${page}${queryString}">[${page+1}]</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

<%-- 	<c:choose>
		<c:when test="${currentPage < (totalPage-1)}">
			<a href="?page=${currentPage + 1}${queryString}">다음</a>
		</c:when>
		<c:otherwise>
			<span class="disabled">다음</span>
		</c:otherwise>
	</c:choose> --%>
</div>
<br><br><br><br><br><br>
</body>
<c:import url="../fragments/footer.jsp"/>
</html>
