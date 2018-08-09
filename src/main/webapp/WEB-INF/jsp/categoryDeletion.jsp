<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Category</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Categories</h2>
<h3>${catBean.operationLabel}</h3>
<p>Please confirm the deletion:</p>
<p>Category name: ${catBean.category.name}</p>
<form method="POST" action="${contextRoot}/app/category/do">
    <input type="submit" value="delete it"/>
    <input type="hidden" name="categoryId" value="${catBean.category.id}"/>
    <input type="hidden" name="action" value="${catBean.action}"/>
</form>
<p>
    Or <a href="${contextRoot}/app/mainMenu.jsp">go back to the main page</a>
</p>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
