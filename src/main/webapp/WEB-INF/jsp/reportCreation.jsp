<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page errorPage="showError.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Report</title>
</head>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Report</h2>
<h3>${reportBean.operationLabel}</h3>
<form method="POST" action="${contextRoot}/app/report/do/${reportBean.action}">
	<!--
		<input type="hidden" name="action" value="${reportBean.action}">
		 -->
	<h4>Report Summary</h4>
	<p>
		Subject name <input type="text" name="subjectName">
	</p>
	<h4>Toxicity assessment</h4>
	<c:forEach var="category" items="${categories}">
		<table>
			<thead>
			<tr>
				<th>${category.name}</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="allergen" items="${category.allergens}">
				<tr>
					<td>${allergen.name}</td>
					<td><input type="range" name="tr-${allergen.id}" min="0" max="100"></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:forEach>

	<input type="submit" value="Create"/>
</form>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
