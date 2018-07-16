<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Allergens</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Reports</h2>
<h3>stored in the system</h3>
<table border="1">
    <thead>
    <tr>
        <th colspan="2">Commands</th>
        <th>Subject Name</th>
        <th>Creation date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="report" items="${reports}">
        <tr>
            <td>
                <form method="POST" action="${contextRoot}/report/viewDetails">
                    <input type="hidden" name="reportId" value="${report.id}"/> <input type="submit" value="Details"/>
                </form>
            </td>
            <td>
                <form method="POST" action="${contextRoot}/report/delete">
                    <input type="hidden" name="reportId" value="${report.id}"/> <input type="submit" value="Details"/>
                </form>
            </td>
            <td>${report.subjectName}</td>
            <td>${report.creationDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h3>Operations</h3>
<p>Create new report:</p>
<form method="post" action="${contextRoot}/report/create">
    <input type="submit" value="create new report">
</form>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
