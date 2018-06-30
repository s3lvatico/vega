<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 30/06/2018
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<hr/>
<h6>
    <a href="<%=request.getContextPath()%>/index.jsp">index</a>
</h6>
<h5>Current time: <%= new Date() %>
</h5>
</body>
</html>
