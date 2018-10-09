<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta charset="utf-8">
	<title>Users</title>
</head>
<body>

<%@include file="/WEB-INF/jsp/header.jsp" %>

<h2>Users</h2>
<h3>Create a new user</h3>

<form method="POST" action="${contextRoot}/app/users/do/${commandName}">
	<table>
		<tr>
			<td><label for="userId">User id</label></td>
			<td><input type="text" id="userId" name="userId"></td>
		</tr>
		<tr>
			<td><label for="userFullName">Full name</label></td>
			<td><input type="text" id="userFullName" name="userFullName"></td>
		</tr>
	</table>
	<fieldset>
		<legend>Password</legend>
		<table>
			<tr>
				<td><label for="newPassword">Password</label></td>
				<td><input type="password" id="newPassword" name="newPassword"></td>
			</tr>
			<tr>
				<td><label for="newPasswordConf">Confirm password</label></td>
				<td><input type="password" id="newPasswordConf" name="newPasswordConf"></td>
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<legend>User Roles</legend>
		<div>Check all that apply.</div>
		<div><em>Caution</em>: every user must have at least one role; also, there must always be
			at least one user with the "v-admin" role.
		</div>
		<table>
			<c:forEach var="role" items="${userRoles}">
				<tr>
					<td><input type="checkbox" name="${role.name}" id="${role.name}"></td>
					<td><label for="${role.name}">${role.name}</label></td>
				</tr>
			</c:forEach>
		</table>
	</fieldset>
	<p>
		<input type="submit" value="Create user">
	</p>
</form>
<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
