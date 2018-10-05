<%@ page import="org.gmnz.vega.VegaUtil" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page session="true" %>

<%
    boolean userIsLogged = !VegaUtil.stringNullOrEmpty(request.getRemoteUser());
    String ctxRoot = application.getContextPath();
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
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
    }
    else {
        response.sendRedirect(ctxRoot + "/home.jsp");
    }
%>

</body>
</html>
