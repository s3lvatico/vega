<%@ page contentType="text/html;charset=UTF-8" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Report</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Report</h2>
<h3>${reportBean.operationLabel}</h3>
<!-- 
<input type="hidden" name="action" value="${reportBean.action}">
 -->
<h4>Report Summary</h4>
<p>Subject name : ${reportData.subjectName}</p>
<p>Creation date : ${reportData.creationDate}</p>
<h4>Toxicity assessment</h4>
<c:forEach var="reportCategory" items="${reportData.categories}">
    <table>
        <thead>
        <tr>
            <th colspan="3">${reportCategory.name}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="toxicityAssessment" items="${reportCategory.toxData}">
            <tr>
                <td>${toxicityAssessment.allergenName}</td>
                <td>
                    <input type="range" disabled min="0" max="100" value="${toxicityAssessment.toxicityRating}">
                </td>
                <td>
                    ${toxicityAssessment.toxicityRating}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:forEach>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
