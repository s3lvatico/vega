<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Categories</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Categories</h2>
<h3>${categoryBean.operationLabel}</h3>
<p>Please confirm the deletion:</p>
<p>Category name: ${categoryBean.category.name}</p>
<form method="POST" action="${contextRoot}/app/category/do/${categoryBean.action}">
    <input type="submit" value="delete it"/>
    <input type="hidden" name="categoryId" value="${categoryBean.category.id}"/>
</form>
<p>
    Or <a href="${contextRoot}/app/mainMenu.jsp">go back to the main page</a>
</p>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
