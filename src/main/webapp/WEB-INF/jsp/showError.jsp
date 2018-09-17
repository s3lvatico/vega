<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>error occurred</title>
</head>

<body>
<h1>OOPS!</h1>
<table border="1">
    <tr>
        <td width="15%"><b>Status code:</b></td>
        <td><c:out value="${statusCode}"/></td>
    </tr>
    <!--

    <tr>
        <td><b>URI:</b></td>
        <td>${pageContext.errorData.requestURI}</td>
    </tr>
    -->
    <tr>
        <td><b>Outcome:</b></td>
        <td>${processingOutcome}</td>
    </tr>
    <tr>
        <td><b>error message:</b></td>
        <!--
        <td><%= request.getAttribute("errorMessage") %></td>
         -->
        <td>${errorMessage}</td>
    </tr>
    <tr>
        <td colspan="2"><a href="${contextRoot}/app/mainMenu.jsp">Go back to the main page</a></td>
    </tr>
    <tr>
        <td><b>Stack trace:</b></td>
        <td>
            <!--
            <c:if test="${pageContext.exception != null}">
                <c:forEach var="stackTraceElement" items="${pageContext.exception.stackTrace}">
                    <p>${stackTraceElement}</p>
                </c:forEach>
            </c:if>
            -->
            <c:out value="${stackTrace}"/>
        </td>
    </tr>

</table>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>