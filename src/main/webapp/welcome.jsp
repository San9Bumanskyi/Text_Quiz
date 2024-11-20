<%--
  Created by IntelliJ IDEA.
  User: san9s
  Date: 17.11.2024
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="utils.QuestLoader" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Quest" %>

<%
  Map<String, List<Quest>> allQuests = QuestLoader.loadAllQuests();
%>

<html>
<head>
  <title>Welcome</title>
</head>
<body>
<h1>Welcome in the game!</h1>
<h2>Choose the quest:</h2>
<ul>
  <% for (String questName : allQuests.keySet()) { %>
  <li>
    <form action="quest" method="get">
      <input type="hidden" name="questId" value="<%= questName %>">
      <button type="submit"><%= questName %></button>
    </form>
  </li>
  <% } %>
</ul>
</body>
</html>
