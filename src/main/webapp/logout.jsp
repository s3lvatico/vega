<%@ page import="org.gmnz.vega.VegaUtil" %><%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 09/08/2018
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    boolean userIsLogged = !VegaUtil.stringNullOrEmpty(request.getRemoteUser());
    String ctxRoot = application.getContextPath();
%>
<html>
<head>
    <title>Logout</title>
</head>
<body>
<%
    if (userIsLogged) {
    	session.invalidate();
%>

<p>Log off complete.</p>
<p>You may go back to the <a href="<%= ctxRoot %>/home.jsp">initial page</a>.</p>

<%
    } else {
        response.sendRedirect(ctxRoot + "/home.jsp");
    }
%>
</body>
</html>
