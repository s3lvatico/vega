<%@ page import="org.gmnz.vega.Vega" %>
<%@ page import="org.gmnz.vega.VegaImpl" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page errorPage="WEB-INF/jsp/showError.jsp" %>
<% Vega vega = new VegaImpl();%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <title>Vega Home</title>
</head>
<body>
<h2>Welcome to Vega</h2>
<p>
    Access the <a href="<%=application.getContextPath()%>/app/mainMenu.jsp">main application</a> page.
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

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

</body>
</html>