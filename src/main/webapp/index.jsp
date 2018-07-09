<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String contextRoot = request.getContextPath();
%>
<html>
<head>
<title>vega</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<h2>Main menu</h2>
	<ul>		
		<li><a href="<%=contextRoot%>/category/getAll">Categories</a></li>
		<li><a href="allergens.jsp">Allergens</a></li>
		<li>Reports (not yet available)</li>
	</ul>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>
