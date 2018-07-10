<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page errorPage="showError.jsp"%>

<html>
<head>
<title>Allergen</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<h2>Allergens</h2>
	<h3>${allergenBean.operationLabel}</h3>
	<form action="<%=request.getContextPath()%>/allergen/do">
		<p>
			Allergen name: 
			<input type="text" name="allergenName" value="${allergenBean.}" /> 
			<input type="hidden" name="oldCategoryName" value="${allergenBean.categoryName}"
			/> <input type="hidden" name="action" value="${allergenBean.action}" />
		</p>

		<p>Category
			<select name="category">
				<option value="volvo">Volvo</option>
				<option value="saab">Saab</option>
				<option value="fiat">Fiat</option>
				<option value="audi">Audi</option>
			</select>
		</p>
		<input type="submit" value="Confirm" />
	</form>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>
