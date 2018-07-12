<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="rangeMin" value="0"/>
<c:set var="rangeMax" value="5"/>
<c:set var="rangeStep" value="1"/>

<html>
<head>
    <meta charset="utf-8">
    <title>vega</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h2>Ranges?</h2>

<fieldset>
    <legend>Audio settings</legend>
    <div>
        <label for="volume">Volume</label>
        <input type="range" id="volume" name="volume" min="${rangeMin}" max="${rangeMax}"/>
    </div>

    <div>
        <label for="cowbell">Cowbell</label>
        <input type="range" id="cowbell" name="cowbell" min="${rangeMin}" max="${rangeMax}" step="${rangeStep}"/>
    </div>
</fieldset>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
