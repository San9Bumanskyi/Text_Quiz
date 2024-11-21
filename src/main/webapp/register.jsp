<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register</title>
  <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h2>Register</h2>
<form action="register" method="post">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" required><br><br>
  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required><br><br>
  <button type="submit">Register</button>
</form>
<p>${message}</p>
<a href="login.jsp">Login</a>
</body>
</html>
