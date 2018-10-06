<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
	<meta charset="utf-8">
</head>
<body>
<h1>Gmnz's Vega</h1>
<hr/>
<nav>

	<a href="${contextRoot}/app/mainMenu">Index</a>
	<a href="${contextRoot}/app/category/getAll">Categories</a>
	<a href="${contextRoot}/app/allergen/getAll">Allergens</a>
	<a href="${contextRoot}/app/report/getAll">Reports</a>
	<c:if test="${userManagementEnabled}">
		<a href="${contextRoot}/app/users/getAll">Users Management</a>
	</c:if>
	<c:if test="${userIsLogged}">
		<%= " |  logged in as [" + request.getRemoteUser() + "] - " %> <a href="${contextRoot}/logout.jsp">Log out</a>
	</c:if>
</nav>
<hr/>

</body>
</html>


