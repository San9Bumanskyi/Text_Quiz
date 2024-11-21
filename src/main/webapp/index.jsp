<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>About the site</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1>About This Game</h1>
<p>This is an adventure game where you choose quests and experience exciting challenges!</p>
<c:if test="${not empty user}">
    <p>Welcome, ${user.username}! Enjoy your journey.</p>
</c:if>
<c:if test="${empty user}">
    <p>Please <a href="login.jsp">log in</a> to start your adventure.</p>
</c:if>
</body>
</html>