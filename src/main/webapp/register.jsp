<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register</title>
</head>
<body>
<h2>Register</h2>
<form action="register" method="post">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" required><br><br>
  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required><br><br>
  <label for="role">Role:</label>
  <select id="role" name="role">
    <option value="user">User</option>
    <option value="admin">Admin</option>
  </select><br><br>
  <button type="submit">Register</button>
</form>
<p>${message}</p>
<a href="login.jsp">Login</a>
</body>
</html>
