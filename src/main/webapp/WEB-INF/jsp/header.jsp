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
<ul>
</ul>
<h6>
    <a href="<%= contextRoot %>/app/mainMenu.jsp">Index</a>
    <a href="<%= contextRoot %>/app/category/getAll">Categories</a>
    <a href="<%= contextRoot %>/app/allergen/getAll">Allergens</a>
    <a href="<%= contextRoot %>/app/report/getAll">Reports</a>
    <c:if test="${userIsLogged}">
        <%= " |  logged in as [" + request.getRemoteUser() + "] - " %> <a href="<%= contextRoot %>/logout.jsp">Log out</a>
    </c:if>
</h6>
<hr/>

</body>
</html>
