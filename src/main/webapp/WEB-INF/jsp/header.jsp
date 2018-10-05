<%@ page import="org.gmnz.vega.VegaUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String contextRoot = request.getContextPath();
	request.setAttribute("userIsLogged", !VegaUtil.stringNullOrEmpty(request.getRemoteUser()));
%>
<html lang="en">
<head>
	<meta charset="utf-8">
</head>
<body>
<h1>Gmnz's Vega</h1>
<hr/>
<h6>
	<a href="${webAppRoot}/app/mainMenu.jsp">Index</a>
	<a href="${webAppRoot}/app/category/getAll">Categories</a>
	<a href="${webAppRoot}/app/allergen/getAll">Allergens</a>
	<a href="${webAppRoot}/app/report/getAll">Reports</a>
	<c:if test="${userManagementEnabled}">
		<a href="${webAppRoot}/app/users/getAll">Users Management</a>
	</c:if>
	<c:if test="${userIsLogged}">
		<%= " |  logged in as [" + request.getRemoteUser() + "] - " %> <a href="${webAppRoot}/logout.jsp">Log out</a>
	</c:if>
</h6>
<hr/>

</body>
</html>


