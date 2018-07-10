<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 30/06/2018
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<h1>Gmnz's Vega</h1>
<hr/>
<h6>
    <% String contextRoot = request.getContextPath(); %>
    <a href="<%= contextRoot %>/index.jsp">Index</a>
    <a href="<%= contextRoot %>/category/getAll">Categories</a>
    <a href="<%= contextRoot %>/allergen/getAll">Allergens</a>
</h6>
<hr/>

</body>
</html>
