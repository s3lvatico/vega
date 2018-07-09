<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page errorPage="showError.jsp" %>

<html>
<head>
    <title>Category</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Categories</h2>
<h3>${catBean.operationLabel}</h3>
<p>Category name: </p>
<form action="<%=request.getContextPath()%>/category/do">
    <input type="text" name="categoryName" value="${catBean.categoryName}"/>
    <input type="hidden" name="oldCategoryName" value="${catBean.categoryName}"/>
    <input type="hidden" name="action" value="${catBean.action}"/>
    <input type="submit" value="Confirm"/>
</form>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
