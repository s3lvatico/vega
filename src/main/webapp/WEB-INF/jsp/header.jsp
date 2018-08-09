<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 30/06/2018
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>

<h1>Gmnz's Vega</h1>
<hr/>
<h6>
    <% String contextRoot = request.getContextPath(); %>
    <a href="<%= contextRoot %>/app/mainMenu.jsp">Index</a>
    <a href="<%= contextRoot %>/app/category/getAll">Categories</a>
    <a href="<%= contextRoot %>/app/allergen/getAll">Allergens</a>
    <a href="<%= contextRoot %>/app/report/getAll">Reports</a>
</h6>
<hr/>

</body>
</html>
