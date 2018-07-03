<%@ page import="java.util.Date"%>
<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 30/06/2018
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>vega</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
	<h2>Main menu</h2>
	<ul>
		<li>Categories</li>
		<li><a href="allergens.jsp">Allergens</a></li>
		<li>Reports</li>
	</ul>
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
</body>
</html>
