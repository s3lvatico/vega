<%@ page import="java.util.Date"%>
<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 30/06/2018
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String contextRoot = request.getContextPath();
	System.out.println("contextRoot : " + contextRoot);
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
