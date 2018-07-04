<%@ page import="org.gmnz.vega.Vega" %>
<%@ page import="org.gmnz.vega.VegaImpl" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:useBean id="catBean" class="org.gmnz.vega.ui.CategoryManagementBean" scope="request" />

<%!Vega vega = new VegaImpl();%>

<html>
<head>
    <title>Category</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Categories</h2>
<h3><%= catBean.getOperationLabel() %></h3>
<p>Category name: </p>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
