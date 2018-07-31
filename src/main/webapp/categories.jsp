<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8">
<title>Categories</title>
</head>
<body>

	<%@include file="/WEB-INF/jsp/header.jsp"%>
	
	<h2>Categories</h2>
	<h3>Registered in the system</h3>

	<table>
		<thead></thead>
		<tbody>

			<c:forEach var="category" items="${categories}">
				<tr>
					<td>
						<form method="POST" action="${contextRoot}/category/edit">
							<input type="hidden" name="categoryId" value="${category.id}"> <input type="submit" value="E">
						</form>
					</td>
					<td>
						<form method="POST" action="${contextRoot}/category/delete">
							<input type="hidden" name="categoryId" value="${category.id}"> <input type="submit" value="D">
						</form>
					</td>
					<td>${category.name}</td>
				</tr>
			</c:forEach>			
		</tbody>
	</table>
	<p>Total registered categories: ${categories.size()} </p>
	<h3>Operations</h3>
	<p>Create new category:</p>
	<form method="POST" action="${contextRoot}/category/create">
		<input type="submit" value="create new category">
	</form>

	<%@include file="/WEB-INF/jsp/footer.jsp"%>

</body>
</html>
