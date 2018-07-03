<%@ page import="org.gmnz.vega.Vega"%>
<%@ page import="org.gmnz.vega.VegaImpl"%>
<%@ page import="org.gmnz.vega.domain.Category"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!Vega vega = new VegaImpl();%>
<html>
<head>
<title>Allergens</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
	<h2>Categories</h2>
	<h3>Registered in the system</h3>
	<ul>
		<%
			for (Category c : vega.getAllCategories()) {
		%>
		<li><%=c.getName()%></li>
		<%
			}
		%>
	</ul>
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
</body>
</html>
