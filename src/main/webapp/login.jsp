<%--
  Created by IntelliJ IDEA.
  User: san9s
  Date: 20.11.2024
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="login" method="post">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" required><br><br>
  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required><br><br>
  <button type="submit">Login</button>
</form>
<p>${message}</p>
<a href="register.jsp">Register</a>
</body>
</html>
