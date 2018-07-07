<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:useBean id="catBean" class="org.gmnz.vega.ui.CategoryManagementBean" scope="request"/>

<html>
<head>
    <title>Category</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Categories</h2>
<h3><%=catBean.getOperationLabel()%>
</h3>
<p>Please confirm the deletion:</p>
<p>
    Category name:
    <%=catBean.getCategoryName()%>
</p>
<form action="<%=request.getContextPath()%>/category/do">
    <input type="submit" value="delete it"/>
    <input type="hidden" name="categoryName" value="<%=catBean.getCategoryName()%>"/>
    <input type="hidden" name="action" value="<%=catBean.getAction()%>"/>
</form>
<p>Or <a href="<%=application.getContextPath()%>/index.jsp">go back to the main page</a></p>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
