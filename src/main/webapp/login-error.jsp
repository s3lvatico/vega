<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
	String contextRoot = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Error</title>
</head>
<body>
	<h1>Login Error</h1>
	<p>
		Authentication failed. Go back to the <a href="<%=contextRoot%>/index.jsp">main page</a>.
	</p>
</body>
</html>