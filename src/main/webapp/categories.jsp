<%@ page import="org.gmnz.vega.Vega"%>
<%@ page import="org.gmnz.vega.VegaImpl"%>
<%@ page import="org.gmnz.vega.domain.Category"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!Vega vega = new VegaImpl();%>
<%
	String ctxRoot = request.getContextPath();
%>
<html>
<head>
<title>Allergens</title>
</head>
<body>
	<%@include file="/WEB-INF/jsp/header.jsp"%>
	<h2>Categories</h2>
	<h3>Registered in the system</h3>

	<table>
		<thead></thead>
		<tbody>

			<%
				for (Category c : vega.getAllCategories()) {
			%>
			<tr>
				<td>
					<form action="<%=ctxRoot%>/category/edit">
						<input type="hidden" name="categoryName" value="<%=c.getName()%>"> <input type="submit" value="E">
					</form>
				</td>
				<td>
					<form action="<%=ctxRoot%>/category/delete">
						<input type="hidden" name="categoryName" value="<%=c.getName()%>"> <input type="submit" value="D">
					</form>
				</td>
				<td><%=c.getName()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<h3>Operations</h3>
	<p>Create new category:</p>
	<form action="<%=ctxRoot%>/category/create">
		<input type="submit" value="create new category">
	</form>

	<%@include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
