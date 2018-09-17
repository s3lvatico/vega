<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Categories</title>
</head>
<body>

<%@include file="/WEB-INF/jsp/header.jsp" %>

<h2>Categories</h2>
<h3>Registered in the system</h3>

<table>
    <thead></thead>
    <tbody>
    <c:forEach var="category" items="${categories}">
        <tr>
            <c:if test="${managementEnabled}">
                <td>
                    <form method="GET" action="${contextRoot}/app/category/edit">

                        <!--  edit -->

                        <input type="hidden" name="categoryId" value="${category.id}">
                        <input type="submit" value="E">
                    </form>
                </td>
                <td>
                    <form method="POST" action="${contextRoot}/app/category/delete">

                        <!--  delete -->

                        <input type="hidden" name="categoryId" value="${category.id}">
                        <input type="submit" value="D">
                    </form>
                </td>
            </c:if>
            <td>${category.name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>Total registered categories: ${categories.size()} </p>
<c:choose>
    <c:when test="${managementEnabled}">
        <h3>Operations</h3>
        <p>Create new category:</p>

        <!-- create -->

        <form method="POST" action="${contextRoot}/app/category/create">
            <input type="submit" value="create new category">
        </form>
    </c:when>
    <c:otherwise>
        <h5>You are not permitted to manage the categories.</h5>
    </c:otherwise>
</c:choose>
<%@include file="/WEB-INF/jsp/footer.jsp" %>
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
