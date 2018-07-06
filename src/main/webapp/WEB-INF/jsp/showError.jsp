<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Show Error Page</title>
</head>

<body>
<h1>Opps...</h1>
<table width="100%" border="1">
    <tr>
        <td width="40%"><b>Error:</b></td>
        <td><%=pageContext.getException()%>
        </td>
    </tr>

    <tr>
        <td><b>URI:</b></td>
        <td><%=pageContext.getErrorData().getRequestURI()%>
        </td>
    </tr>

    <tr>
        <td><b>Status code:</b></td>
        <td><%=pageContext.getErrorData().getStatusCode()%>
        </td>
    </tr>

    <tr>
        <td><b>Stack trace:</b></td>
        <td>
            <% for (StackTraceElement ste : pageContext.getException().getStackTrace()) {%>
            <p><%= ste %>
            </p>
            <% } %>
        </td>
    </tr>
</table>

</body>
</html>