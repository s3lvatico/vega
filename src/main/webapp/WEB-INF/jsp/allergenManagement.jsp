<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page errorPage="showError.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Allergen</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Allergens</h2>
<h3>${allergenBean.operationLabel}</h3>
<form action="${contextRoot}/allergen/do">
    <p>
        Allergen name: <input type="text" name="allergenName" value="${allergenBean.allergenName}" title="allergenName"/>
        <input type="hidden" name="action" value="${allergenBean.action}"/>
        <input type="hidden" name="trackingId" value="${trackingId}"/>
    </p>

    <p>Category
        <select name="categoryName" title="categoryName">
            <c:forEach var="category" items="${categories}">
                <c:set var="selectedAttribute" scope="page" value=""/>
                <c:if test="${category.name == initialAllergenCategoryName}">
                    <c:set var="selectedAttribute" scope="page" value="selected"/>
                </c:if>
                <option value="${category.name}" ${selectedAttribute}>${category.name}</option>
            </c:forEach>
        </select>
    </p>
    <input type="submit" value="Confirm"/>
</form>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
