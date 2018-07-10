<%@ page import="org.gmnz.vega.Vega"%>
<%@ page import="org.gmnz.vega.VegaImpl"%>
<%@ page import="org.gmnz.vega.domain.Allergen"%>
<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 30/06/2018
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!Vega vega = new VegaImpl();%>
<html>
<head>
<title>Allergens</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"/>
	<h2>Allergens</h2>
	<h3>Registered in the system</h3>
	<table>
		<thead>
			<tr>
				<th>Allergen Name</th>
				<th>Category</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Allergen a : vega.getAllAllergens()) {
			%>
			<tr>
				<td><%=a.getName()%></td>
				<td><%=a.getCategory().getName()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
