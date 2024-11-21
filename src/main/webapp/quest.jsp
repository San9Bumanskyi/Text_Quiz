<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Quest</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="container">
    <h1>Quest</h1>
    <c:if test="${not empty message}">
        <p style="color: red;">${message}</p>
    </c:if>
    <form action="quest" method="post">
        <h2>${currentQuest.question}</h2>
        <div class="options-container">
            <c:forEach var="option" items="${currentQuest.options}" varStatus="status">
                <button type="submit" name="option" value="${status.index}" class="option-button">
                        ${option}
                </button>
            </c:forEach>
        </div>
        <input type="hidden" name="questId" value="${currentQuest.questId}" />
    </form>
</div>
</body>
</html>
