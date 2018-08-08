<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Error occurred</title>
</head>

<body>
<h1>OOPS!</h1>
<table border="1">
    <tr>
        <td width="15%"><b>Error:</b></td>
        <td>${pageContext.exception}</td>
    </tr>
    <tr>
        <td colspan="2"><a href="<%=application.getContextPath()%>/index.jsp">Go back to the main page</a></td>
    </tr>
    <tr>
        <td><b>URI:</b></td>
        <td>${pageContext.errorData.requestURI}</td>
    </tr>

    <tr>
        <td><b>Status code:</b></td>
        <td>${pageContext.errorData.statusCode}</td>
    </tr>

    <tr>
        <td><b>Stack trace:</b></td>
        <td>
            <c:if test="${pageContext.exception != null}">
                <c:forEach var="stackTraceElement" items="${pageContext.exception.stackTrace}">
                    <p>${stackTraceElement}</p>
                </c:forEach>
            </c:if>
        </td>
    </tr>
    <tr>
        <td><b>error message:</b></td>
        <!-- 
        <td><%= request.getAttribute("errorMessage") %></td>
         -->
         <td>${errorMessage}</td>
    </tr>

</table>

</body>
</html>