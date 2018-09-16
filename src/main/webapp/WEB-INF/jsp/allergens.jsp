<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Allergens</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Allergens</h2>
<h3>Registered in the system</h3>
<table border="1">
    <thead>
    <tr>
        <c:if test="${managementEnabled}">
            <th colspan="2">Commands</th>
        </c:if>
        <th>Allergen Name</th>
        <th>Category</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="allergen" items="${allergens}">
        <tr>
            <c:if test="${managementEnabled}">
                <td>

                    <!-- EDIT -->
                    <form method="POST" action="${contextRoot}/app/allergen/edit">
                        <input type="hidden" name="allergenId" value="${allergen.id}"/>
                        <input type="submit" value="E"/>
                    </form>
                </td>
                <td>

                    <!-- DELETE -->
                    <form method="POST" action="${contextRoot}/app/allergen/delete">
                        <input type="hidden" name="allergenId" value="${allergen.id}"/>
                        <input type="submit" value="D"/>
                    </form>
                </td>
            </c:if>
            <td>${allergen.name}</td>
            <td>${allergen.category.name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>Total registered allergens: ${allergens.size()} </p>
<c:choose>
    <c:when test="${managementEnabled}">

        <h3>Operations</h3>
        <p>Create new allergen:</p>

        <!-- CREATE -->
        <form method="POST" action="${contextRoot}/app/allergen/create">
            <input type="submit" value="create new allergen">
        </form>
    </c:when>
    <c:otherwise>
        <h5>You are not permitted to manage the allergens.</h5>
    </c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
