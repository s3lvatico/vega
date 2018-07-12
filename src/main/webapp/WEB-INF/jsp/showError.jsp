<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Error occurred</title>
</head>

<body>
<h1>OOPS!</h1>
<table border="1">
    <tr>
        <td width="15%"><b>Error:</b></td>
        <td><%=pageContext.getException()%>
        </td>
    </tr>
    <tr>
        <td colspan="2"><a href="<%=application.getContextPath()%>/index.jsp">Go back to the main page</a></td>
    </tr>
    <tr>
        <td><b>URI:</b></td>
        <td><%=pageContext.getErrorData().getRequestURI()%></td>
    </tr>

    <tr>
        <td><b>Status code:</b></td>
        <td><%=pageContext.getErrorData().getStatusCode()%></td>
    </tr>

    <tr>
        <td><b>Stack trace:</b></td>
        <td>
            <% for (StackTraceElement stackTraceElement : pageContext.getException().getStackTrace()) {%>
            <p><%= stackTraceElement %></p>
            <% } %>
        </td>
    </tr>
</table>

</body>
</html>