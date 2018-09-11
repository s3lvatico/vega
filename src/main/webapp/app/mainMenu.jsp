<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String contextRoot = application.getContextPath();
%>
<html>
<head>
<meta charset="utf-8">
<title>vega</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<h2>Main menu</h2>
	<img src="../img/apple.png"/> Questa è solo una prova su un'immagine
	<ul>
		<li><a href="<%=contextRoot%>/app/category/getAll">Categories</a></li>
		<li><a href="<%=contextRoot%>/app/allergen/getAll">Allergens</a></li>
		<li><a href="<%=contextRoot%>/app/report/getAll">Reports</a></li>
	</ul>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>
