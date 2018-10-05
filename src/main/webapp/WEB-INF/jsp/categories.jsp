<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Categories</title>
</head>
<body>

<%@include file="/WEB-INF/jsp/header.jsp" %>

<h2>Categories</h2>
<h3>Registered in the system</h3>

<table class="table">
	<thead>
	<tr>
		<c:if test="${managementEnabled}">
			<th colspan="2">Commands</th>
		</c:if>
		<th>Category Name</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="category" items="${categories}">
		<tr>
			<c:if test="${managementEnabled}">
				<td>
					<form method="GET" action="${contextRoot}/app/category/edit">
						<!--  edit -->
						<input type="hidden" name="categoryId" value="${category.id}"> <input type="submit" value="Edit" class="btn btn-primary">
					</form>
				</td>
				<td>
					<form method="POST" action="${contextRoot}/app/category/delete">
						<!--  delete -->
						<input type="hidden" name="categoryId" value="${category.id}"> <input type="submit" value="Delete" class="btn btn-warning">
					</form>
				</td>
			</c:if>
			<td>${category.name}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<p>Total registered categories: ${categories.size()}</p>
<c:choose>
	<c:when test="${managementEnabled}">
		<h3>Operations</h3>
		<p>Create new category:</p>

		<!-- create -->

		<form method="POST" action="${contextRoot}/app/category/create">
			<input type="submit" value="create new category">
		</form>
	</c:when>
	<c:otherwise>
		<div>You are not permitted to manage the categories.</div>
	</c:otherwise>
</c:choose>

<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
