<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String contextRoot = application.getContextPath();
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Vega</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Main menu</h2>
<ul>
    <li><a href="<%=contextRoot%>/app/category/getAll">Categories</a></li>
    <li><a href="<%=contextRoot%>/app/allergen/getAll">Allergens</a></li>
    <li><a href="<%=contextRoot%>/app/report/getAll">Reports</a></li>
</ul>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
