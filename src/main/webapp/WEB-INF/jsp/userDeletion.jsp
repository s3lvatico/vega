<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Users</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<h2>Users</h2>
	<h3>User deletion</h3>
	<p>Please confirm the deletion of the user</p>
	<table>
		<tr>
			<td>${targetUser.userId}</td>
			<td>${targetUser.fullName}</td>
		</tr>
	</table>
	<div><em>warning:</em> all user data will be deleted, including his/her reports.</div>
	<form method="POST" action="${contextRoot}/app/users/do/${action}">
		<input type="submit" value="delete it" /> <input type="hidden" name="userId" value="${targetUser.userId}" />
	</form>
	<p>
		Or <a href="${contextRoot}/app/mainMenu.jsp">go back to the main page</a>
	</p>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>
