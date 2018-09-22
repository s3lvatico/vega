<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta charset="utf-8">
	<title>Categories</title>
</head>
<body>

<%@include file="/WEB-INF/jsp/header.jsp" %>

<h2>Users</h2>
<h3>Registered in the system</h3>

<table>
	<table border="1">
		<thead>
		<tr>
			<th>user id</th>
			<th>full name</th>
			<th>roles</th>
			<th colspan="2">commands</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.userId}</td>
				<td>${user.fullName}</td>
				<td>${user.roles}</td>
				<td>
					<!-- edit -->
					<form method="post" action="${contextRoot}/app/users/edit">
						<input type="hidden" name="userId" value="${user.userId}">
						<input type="submit" value="E">
					</form>
				</td>
				<td>
					<!-- delete -->
					<form method="post" action="${contextRoot}/app/users/delete">
						<input type="hidden" name="userId" value="${user.userId}">
						<input type="submit" value="D">
					</form>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<p>Total registered users: ${users.size()}</p>
	<h3>Operations</h3>
	<p>Create new user:</p>
	<!-- create -->
	<form method="POST" action="${contextRoot}/app/users/create">
		<input type="submit" value="create new user">
	</form>
	<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
