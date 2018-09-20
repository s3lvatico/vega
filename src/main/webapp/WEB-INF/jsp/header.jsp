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
<div class="container-fluid">
	<div class="row">
		<div class="col-md-6">
			<nav class="nav">
				<a class="nav-link" href="<%= contextRoot %>/app/mainMenu.jsp">Index</a>
				<a class="nav-link" href="<%= contextRoot %>/app/category/getAll">Categories</a>
				<a class="nav-link" href="<%= contextRoot %>/app/allergen/getAll">Allergens</a>
				<a class="nav-link" href="<%= contextRoot %>/app/report/getAll">Reports</a>
			</nav>
		</div>
		<div class="col-md-6">
			<c:if test="${userIsLogged}">
				<span>logged in as [<%= request.getRemoteUser() + "] - " %> <a href="<%= contextRoot %>/logout.jsp">Log out</a></span>
			</c:if>
		</div>
	</div>
</div> <!-- container -->

<hr/>

</body>
</html>
