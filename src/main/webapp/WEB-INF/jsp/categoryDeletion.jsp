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
<p>Category name: ${catBean.categoryName}</p>
<form action="${contextRoot}/category/do">
    <input type="submit" value="delete it"/>
    <input type="hidden" name="categoryId" value="${catBean.categoryName}"/>
    <input type="hidden" name="action" value="${catBean.action}"/>
</form>
<p>
    Or <a href="${contextRoot}/index.jsp">go back to the main page</a>
</p>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
