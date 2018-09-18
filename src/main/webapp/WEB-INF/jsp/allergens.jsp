<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<title>Allergens</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<h2>Allergens</h2>
	<h3>Registered in the system</h3>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-9">

				<table class="table">
					<thead>
						<tr>
							<c:if test="${managementEnabled}">
								<th colspan="2">Commands</th>
							</c:if>
							<th>Allergen Name</th>
							<th>Category</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="allergen" items="${allergens}">
							<tr>
								<c:if test="${managementEnabled}">
									<td>
										<!-- EDIT -->
										<form method="POST" action="${contextRoot}/app/allergen/edit">
											<input type="hidden" name="allergenId" value="${allergen.id}" /> <input type="submit" value="E" />
										</form>
									</td>
									<td>
										<!-- DELETE -->
										<form method="POST" action="${contextRoot}/app/allergen/delete">
											<input type="hidden" name="allergenId" value="${allergen.id}" /> <input type="submit" value="D" />
										</form>
									</td>
								</c:if>
								<td>${allergen.name}</td>
								<td>${allergen.category.name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<p>Total registered allergens: ${allergens.size()}</p>
	<c:choose>
		<c:when test="${managementEnabled}">

			<h3>Operations</h3>
			<p>Create new allergen:</p>

			<!-- CREATE -->
			<form method="POST" action="${contextRoot}/app/allergen/create">
				<input type="submit" value="create new allergen">
			</form>
		</c:when>
		<c:otherwise>
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6 alert alert-warning" role="alert">You are not permitted to manage the allergens.</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
