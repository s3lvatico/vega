<%@ page import="org.gmnz.vega.Vega" %>
<%@ page import="org.gmnz.vega.VegaImpl" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page errorPage="WEB-INF/jsp/showError.jsp" %>
<% Vega vega = new VegaImpl();%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Vega Home</title>
</head>
<body>
<h2>Welcome to Vega</h2>
<p>
    Access the <a href="<%=application.getContextPath()%>/app/mainMenu.jsp">main application</a> page.
</p>
<h3>System status</h3>
<p>Current:</p>
<table>
    <tbody>
    <tr>
        <td>Categories registered:</td>
        <td><%= vega.getCategoryService().getAllCategories().size() %></td>
    </tr>
    <tr>
        <td>Allergens registered:</td>
        <td><%= vega.getAllergenService().getAllAllergens().size() %></td>
    </tr>
    </tbody>
</table>
</body>
</html>