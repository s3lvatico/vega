<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>error occurred</title>
</head>

<body>
<h1>OOPS!</h1>
<table border="1">
    <tr>
        <td width="15%"><b>Status code:</b></td>
        <td><c:out value="${statusCode}"/></td>
    </tr>
    <tr>
        <td><b>Outcome:</b></td>
        <td>${processingOutcome}</td>
    </tr>
    <tr>
        <td><b>error message:</b></td>
        <td>${errorMessage}</td>
    </tr>
    <tr>
        <td colspan="2"><a href="${contextRoot}/app/mainMenu">Go back to the main page</a></td>
    </tr>
    <tr>
        <td><b>Stack trace:</b></td>
        <td>
            <c:if test="${pageContext.exception != null}">
                <c:forEach var="stackTraceElement" items="${pageContext.exception.stackTrace}">
                    <p>${stackTraceElement}</p>
                </c:forEach>
            </c:if>
            <c:out value="${stackTrace}"/>
        </td>
    </tr>

</table>
</body>
</html>