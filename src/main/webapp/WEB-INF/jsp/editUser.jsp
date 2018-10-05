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
<h3>Modify user information</h3>

<form method="POST" action="${contextRoot}/app/users/do/${commandName}">
	<p>User id : ${user.userId}</p>
	<p>
		Full name : <input type="text" name="userFullName" value="${user.fullName}">
	</p>
	<fieldset>
		<legend>Password modification</legend>
		<table>
			<tr>
				<td>New password</td>
				<td><input type="password" name="newPassword"></td>
			</tr>
			<tr>
				<td>Confirm new password</td>
				<td><input type="password" name="newPasswordConf"></td>
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<legend>User Roles</legend>
		<table>
			<c:forEach var="role" items="${userRoles}">
				<tr>
					<td><input type="checkbox" name="${role.name}" <c:if test="${role.selected}">checked</c:if>></td>
					<td>${role.name}</td>
				</tr>
			</c:forEach>
		</table>
	</fieldset>
	<p>
		<input type="submit" value="Confirm">
	</p>
</form>
<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
