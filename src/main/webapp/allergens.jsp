<%@ page import="org.gmnz.vega.Vega" %>
<%@ page import="org.gmnz.vega.VegaImpl" %>
<%@ page import="org.gmnz.vega.domain.Allergen" %>
<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 30/06/2018
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%!
    Vega vega = new VegaImpl();
%>
<html>
<head>
    <title>Allergens</title>
</head>
<body>
<h2>Allergens</h2>
<h3>Registered in the system</h3>
<%--
<%
    for (Allergen allergen : DummyRepository.getRegisteredAllergens()) {
%>
<p><%= allergen.getName() %> [ <%= allergen.getCategory().getName() %> ]</p>
<%
    }
%>
--%>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Category</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Allergen a : vega.getAllAllergens()) {
    %>
    <tr>
        <td><%=a.getName()%>
        </td>
        <td><%=a.getCategory().getName()%>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<table border="1">
    <thead>
    <tr>
        <th>Category</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>1,1</td>
        <td>1,2</td>
    </tr>
    <tr>
        <td rowspan="2">2,1</td>
        <td>2,2</td>
    </tr>
    <tr>
        <td>2,3</td>
    </tr>
    <tr>
        <td>3,1</td>
        <td>3,2</td>
    </tr>
    </tbody>
</table>
<%@ include file="WEB-INF/jsp/footer.jsp" %>
</body>
</html>
