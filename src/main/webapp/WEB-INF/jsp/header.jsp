<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.gmnz.vega.VegaUtil" %>

<%
	request.setAttribute("webAppRoot", request.getContextPath());
	request.setAttribute("userIsLogged", !VegaUtil.stringNullOrEmpty(request.getRemoteUser()));

	boolean userManagementEnabled = request.isUserInRole("v-admin");
	request.setAttribute("userManagementEnabled", userManagementEnabled);
%>

<html>
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
