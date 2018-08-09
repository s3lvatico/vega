<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String contextRoot = request.getContextPath();
%>
<html>
<head>
<meta charset="utf-8">
<title>vega</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<h2>Main menu</h2>
	<ul>
		<li><a href="<%=contextRoot%>/category/getAll">Categories</a></li>
		<li><a href="<%=contextRoot%>/allergen/getAll">Allergens</a></li>
		<li><a href="<%=contextRoot%>/report/getAll">Reports</a></li>
	</ul>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>
