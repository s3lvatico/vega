<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!doctype html>
<html lang="en">
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

<form method="POST" action="${contextRoot}/app/report/do/${reportBean.action}">
	<input type="submit" value="delete it"/>
	<input type="hidden" name="reportId" value="${reportId}"/>
</form>
<p>
	Or <a href="${contextRoot}/app/mainMenu.jsp">go back to the main page</a>
</p>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
