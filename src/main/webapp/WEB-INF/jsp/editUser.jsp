<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8">
<title>Users</title>
</head>
<body>

	<%@include file="/WEB-INF/jsp/header.jsp"%>

	<h2>Users</h2>
	<h3>Modify user information</h3>

	<form method="POST" action="${contextRoot}/app/users/do/${commandName}">
		<input type="hidden" name="userId" value="${user.userId}">
		<p>User id : ${user.userId}</p>
		<p>Full name : ${user.fullName}</p>
		<p>
			New password <input type="password" name="newPassword">
		</p>
		<p>
			Confirm new password <input type="password" name="newPasswordConf">
		</p>
		<p>User roles</p>
		<table>
			<c:forEach var="role" items="${userRoles}">
				<tr>
					<td><input type="checkbox" name="${role.name}" <c:if test="${role.selected}">checked</c:if>></td>
					<td>${role.name}</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<%@include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
