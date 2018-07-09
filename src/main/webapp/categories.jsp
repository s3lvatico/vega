<%@ page import="org.gmnz.vega.Vega" %>
<%@ page import="org.gmnz.vega.VegaImpl" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%!Vega vega = new VegaImpl();%>
<%
    String ctxRoot = request.getContextPath();

    pageContext.setAttribute("vega", vega);
%>
<html>
<head>
    <title>Categories</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<h2>Categories</h2>
<h3>Registered in the system</h3>

<table>
    <thead></thead>
    <tbody>

    <c:forEach var="category" items="${vega.categoryService.allCategories}">
        <tr>
            <td>
                <form action="<%=ctxRoot%>/category/edit">
                    <input type="hidden" name="categoryName" value="${category.name}"> <input type="submit" value="E">
                </form>
            </td>
            <td>
                <form action="<%=ctxRoot%>/category/delete">
                    <input type="hidden" name="categoryName" value="${category.name}"> <input type="submit" value="D">
                </form>
            </td>
            <td>${category.name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h3>Operations</h3>
<p>Create new category:</p>
<form action="<%=ctxRoot%>/category/create">
    <input type="submit" value="create new category">
</form>

<%@include file="/WEB-INF/jsp/footer.jsp" %>

</body>
</html>
