<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Report</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Reports</h2>
<h3>${reportBean.operationLabel}</h3>
<p>Please confirm report deletion:</p>
<ul>
    <li>Subject name: ${subjectName}</li>
    <li>Creation date: ${creationDate}</li>
</ul>

<form action="${contextRoot}/report/do">
    <input type="submit" value="delete it"/>
    <input type="hidden" name="reportId" value="${reportId}"/>
    <input type="hidden" name="action" value="${reportBean.action}"/>
</form>
<p>
    Or <a href="${contextRoot}/index.jsp">go back to the main page</a>
</p>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
