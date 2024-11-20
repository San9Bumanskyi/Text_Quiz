<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Quest</title>
</head>
<body>
<h1>Quest</h1>
<c:if test="${not empty message}">
    <p style="color: red;">${message}</p>
</c:if>
<form action="quest" method="post">

    <h2>${currentQuest.question}</h2>
    <c:forEach var="option" items="${currentQuest.options}" varStatus="status">
        <label>
            <input type="radio" name="option" value="${status.index}" required />
                ${option}
        </label><br />
    </c:forEach>
    <input type="hidden" name="questId" value="${currentQuest.questId}" />
    <button type="submit">Answer</button>
</form>
</body>
</html>
