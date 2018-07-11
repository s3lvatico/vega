<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page errorPage="showError.jsp" %>

<html>
<head>
    <title>Allergen</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Allergens</h2>
<h3>${allergenBean.operationLabel}</h3>
<form action="${contextRoot}/allergen/do">
    <p>
        Allergen name:
        <input type="text" name="allergenName" value="${allergenBean.allergenName}"/>
        <input type="hidden" name="originalAllergenName" value="${allergenBean.allergenName}"/>
        <input type="hidden" name="action" value="${allergenBean.action}"/>
    </p>

    <p>Category
        <select name="category">
            <c:forEach var="category" items="${categories}">
                <option value="${category.name}">${category.name}</option>
            </c:forEach>
        </select>
    </p>
    <input type="submit" value="Confirm"/>
</form>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
