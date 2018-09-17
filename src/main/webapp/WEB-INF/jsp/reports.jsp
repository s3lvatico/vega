<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
		  integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<title>Reports</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<h2>Reports</h2>
	<h3>Reports stored in the system</h3>
	<table border="1">
		<thead>
			<tr>
				<th colspan="2">Commands</th>
				<th>Subject Name</th>
				<th>Creation date</th>
				<th>Owner</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="report" items="${reports}">
				<tr>
					<c:choose>
						<c:when test="${report.owner eq currentUser}">
							<td>
								<form method="POST" action="${contextRoot}/app/report/viewDetails">
									<input type="hidden" name="reportId" value="${report.id}" /> <input type="submit" value="Details" />
								</form>
							</td>
							<td>
								<form method="POST" action="${contextRoot}/app/report/delete">
									<input type="hidden" name="reportId" value="${report.id}" /> <input type="submit" value="Delete" />
								</form>
							</td>
						</c:when>
						<c:otherwise>
							<td colspan="2">
								<form method="POST" action="${contextRoot}/app/report/viewDetails">
									<input type="hidden" name="reportId" value="${report.id}" /> <input type="submit" value="Details" />
								</form>
							</td>
						</c:otherwise>
					</c:choose>
					<td>${report.subjectName}</td>
					<td>${report.creationDate}</td>
					<td>${report.ownerFullName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h3>Operations</h3>
	<p>Create new report:</p>
	<form method="post" action="${contextRoot}/app/report/create">
		<input type="submit" value="create new report">
	</form>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
			integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
			crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
			integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
			crossorigin="anonymous"></script>
</body>
</html>
