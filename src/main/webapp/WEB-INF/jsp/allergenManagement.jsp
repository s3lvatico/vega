<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page errorPage="showError.jsp" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Vega</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Allergens</h2>
<h3>${allergenBean.operationLabel}</h3>
<form method="POST" action="${contextRoot}/app/allergen/do/${allergenBean.action}">
    <p>
        Allergen name:
        <input type="text" name="allergenName" value="${allergenBean.allergen.name}" title="allergenName"/>
        <!--
        <input type="hidden" name="action" value="${allergenBean.action}"/>
        <input type="hidden" name="allergenId" value="${allergenBean.allergen.id}"/>
        -->
    </p>

    <p>Category
        <select name="categoryId" title="categoryName">
            <c:forEach var="category" items="${categories}">
                <c:set var="selectedAttribute" scope="page" value=""/>
                <c:if test="${category.name == initialAllergenCategoryName}">
                    <c:set var="selectedAttribute" scope="page" value="selected"/>
                </c:if>
                <option value="${category.id}" ${selectedAttribute}>${category.name}</option>
            </c:forEach>
        </select>
    </p>
    <input type="submit" value="Confirm"/>
</form>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
