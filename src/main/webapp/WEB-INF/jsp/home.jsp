<%@ page import="org.gmnz.vega.Vega" %>
<%@ page import="org.gmnz.vega.VegaImpl" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page errorPage="showError.jsp" %>
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
<h3>Current system status</h3>
<table>
	<tbody>
	<tr>
		<td>Categories stored: ${nCategories}</td>
	</tr>
	<tr>
		<td>Allergens stored: ${nAllergens}</td>
	</tr>
	<tr>
		<td>Users registered: ${nUsers}</td>
	</tr>
	<tr>
		<td>Reports created: ${nReports}</td>
	</tr>
	</tbody>
</table>

</body>
</html>