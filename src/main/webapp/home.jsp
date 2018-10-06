<%@ page import="org.gmnz.vega.Vega" %>
<%@ page import="org.gmnz.vega.VegaImpl" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page errorPage="WEB-INF/jsp/showError.jsp" %>
<% Vega vega = new VegaImpl();%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Vega Home</title>
</head>
<body>
<h2>Welcome to Vega</h2>
<p>
    Access the <a href="<%=application.getContextPath()%>/app/mainMenu">main application</a> page.
</p>
<h3>System status</h3>
<h4>Current:</h4>
<table>
    <tbody>
    <tr>
        <td>Categories registered:</td>
        <td><%= vega.getCategoryService().getAllCategories().size() %>
        </td>
    </tr>
    <tr>
        <td>Allergens registered:</td>
        <td><%= vega.getAllergenService().getAllAllergens().size() %>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>