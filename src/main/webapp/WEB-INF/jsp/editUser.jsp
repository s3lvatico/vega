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

<p>User id : ${user.userId}</p>
<form method="POST" action="<%=request.getContextPath()%>/app/users/do/${commandName}">
	<p>Full name : ${user.fullName}</p>
	<p>New password <input type="password" name="newPassword"></p>
	<p>Confirm new password <input type="password" name="newPasswordConf"></p>
	<p>User roles</p>
	<table>
		<c:forEach var="role" items="${userRoles}">
		<tr>
			<td>
				<input type="checkbox" name="${role.name}"
					<c:if test="${role.selected}">checked</c:if>
				>
			</td>
			<td>${role.name}</td>
		</tr>
		</c:forEach>
	</table>
</form>
<!--

id NON modificabile

nome completo modificabile
password modificabile
ruoli modificabili
	==> occorre l'elenco dei ruoli

	_dollaro_{roles}


id : fallo vedere
nome completo : input text

nuova password             : input secret
conferma nuova password    : input secret

ruoli                      : gruppo di checkbox

-->

<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
