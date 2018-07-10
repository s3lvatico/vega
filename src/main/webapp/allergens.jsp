<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Allergens</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Allergens</h2>
<h3>Registered in the system</h3>
<table border="1">
    <thead>
    <tr>
        <th colspan="2">Commands</th>
        <th>Allergen Name</th>
        <th>Category</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="allergen" items="${vega.allergenService.all}">
        <tr>
            <td>
                <form method="post" action="${contextRoot}/allergen/edit">
                    <input type="hidden" name="allergenName" value="${allergen.name}"/> <input type="submit" value="E"/>
                </form>
            </td>
            <td>
                <form method="post" action="${contextRoot}/allergen/delete">
                    <input type="hidden" name="allergenName" value="${allergen.name}"/> <input type="submit" value="D"/>
                </form>
            </td>
            <td>${allergen.name}</td>
            <td>${allergen.category.name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h3>Operations</h3>
<p>Create new allergen:</p>
<form method="POST" action="${contextRoot}/allergen/create">
    <input type="submit" value="create new allergen">
</form>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
