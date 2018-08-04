<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Allergen</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Allergens</h2>
<h3>${allergenBean.operationLabel}</h3>
<p>Please confirm the deletion:</p>
<p>
    Allergen name: ${allergenBean.allergen.name}
</p>
<form method="POST" action="${contextRoot}/allergen/do">
    <input type="submit" value="delete it"/>
    <input type="hidden" name="allergenId" value="${allergenBean.allergen.id}"/>
    <input type="hidden" name="action" value="${allergenBean.action}"/>
</form>
<p>
    Or <a href="${contextRoot}/index.jsp">go back to the main page</a>
</p>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
