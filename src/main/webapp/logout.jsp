<%@ page import="org.gmnz.vega.VegaUtil" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%
    boolean userIsLogged = !VegaUtil.stringNullOrEmpty(request.getRemoteUser());
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
<p>You may go back to the <a href="<%= application.getContextPath() %>/home">initial page</a>.</p>

<%
    }
    else {
        response.sendRedirect(application.getContextPath() + "/home");
    }
%>

</body>
</html>
