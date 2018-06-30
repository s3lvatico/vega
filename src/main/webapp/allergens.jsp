<%@ page import="org.gmnz.vega.domain.Allergen" %>
<%@ page import="org.gmnz.vega.repository.DummyRepository" %><%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 30/06/2018
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Allergens</title>
</head>
<body>
<h2>Allergens</h2>
<h3>Registered in the system</h3>
<%
    for (Allergen allergen : DummyRepository.getRegisteredAllergens()) {
%>
<p><%= allergen.getName() %> [ <%= allergen.getCategory().getName() %> ]</p>
<%
    }
%>
<%@ include file="WEB-INF/jsp/footer.jsp"%>
</body>
</html>
