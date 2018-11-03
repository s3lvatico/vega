<%--@elvariable id="categoryBean" type="org.gmnz.vega.ui.web.category.CategoryManagementBean"--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page errorPage="showError.jsp" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Category</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Categories</h2>
<h3>${categoryBean.operationLabel}</h3>
<p>Category name: </p>
<form method="POST" action="<%=request.getContextPath()%>/app/category/do/${categoryBean.action}">
	<input type="text" name="categoryName" value="${categoryBean.category.name}" title="categoryName"/>
	<input type="hidden" name="categoryId" value="${categoryBean.category.id}"/>
	<!--
   <input type="hidden" name="action" value="${categoryBean.action}"/>
   -->
	<input type="submit" value="Confirm"/>
</form>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
