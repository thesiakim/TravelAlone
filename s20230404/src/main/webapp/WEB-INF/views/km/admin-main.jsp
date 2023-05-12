<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 목록</title>
	<link rel="stylesheet" href="css/mypage.css">
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
					role : $("#selectRole").val()
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
						})
						.fail(function (error) {

							alert(JSON.stringify(error));
						});


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
<h1 class="title">회원 목록</h1>
<div class="search-container">
	<form action="/admin" method="GET">
		<input type="text" name="search" placeholder="회원 이메일로 검색하세요" class="search-input" value="${param.search}">
		<button type="submit" class="search-button">검색</button>
	</form>
</div>

<div class="table-container">
	<table>
		<thead>
		<tr>
			<th>ID</th>
			<th>Email</th>
			<th>Nickname</th>
			<th>Name</th>
			<th>Gender</th>
			<th>Phone</th>
			<th>Created Date</th>
			<th>Modified Date</th>
			<th>Role</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="member" items="${members}">
			<tr>
				<td onclick="detailMove(${member.id})">
					${member.id}
				</td>
				<td onclick="detailMove(${member.id})">${member.email}</td>
				<td onclick="detailMove(${member.id})">${member.nickname}</td>
				<td onclick="detailMove(${member.id})">${member.name}</td>
				<td onclick="detailMove(${member.id})">${member.gender}</td>
				<td onclick="detailMove(${member.id})">${member.phone}</td>
				<td onclick="detailMove(${member.id})">${member.createdDate}</td>
				<td onclick="detailMove(${member.id})">${member.modifiedDate}</td>
				<td id="role">
					<select name="role" id="selectRole" onchange="changeRole(${member.id})">
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

	<c:choose>
		<c:when test="${currentPage > 0}">
			<a href="?page=${currentPage - 1}${queryString}">이전</a>
		</c:when>
		<c:otherwise>
			<span class="disabled">이전</span>
		</c:otherwise>
	</c:choose>

	<c:forEach var="page" begin="${startPage}" end="${endPage}">
		<c:choose>
			<c:when test="${page == currentPage}">
				<span class="current">${page+1}</span>
			</c:when>
			<c:otherwise>
				<a href="?page=${page}${queryString}">${page+1}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:choose>
		<c:when test="${currentPage < (totalPage-1)}">
			<a href="?page=${currentPage + 1}${queryString}">다음</a>
		</c:when>
		<c:otherwise>
			<span class="disabled">다음</span>
		</c:otherwise>
	</c:choose>
</div>
</body>
<c:import url="footer.jsp"/>
</html>
