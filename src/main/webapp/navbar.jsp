<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="background-color: #333; overflow: hidden;">
  <c:if test="${empty user}">
    <a href="login.jsp" style="float: right; color: white; text-decoration: none; padding: 14px 20px;">Login</a>
  </c:if>
  <c:if test="${not empty user}">
    <a href="index.jsp" style="float: left; color: white; text-decoration: none; padding: 14px 20px;">About site</a>
    <a href="welcome.jsp" style="float: left; color: white; text-decoration: none; padding: 14px 20px;">Quests</a>
    <a href="login.jsp" onclick="return confirmLogout();" style="float: right; color: white; text-decoration: none; padding: 14px 20px;">Logout</a>
  </c:if>
</div>

<script>
  function confirmLogout() {
    if (confirm("Are you sure you want to log out?")) {
      window.location.href = "logout";
      return true;
    }
    return false;
  }
</script>
