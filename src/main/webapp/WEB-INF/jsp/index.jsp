<!DOCTYPE html>
<html>
<head>
  <title>Pharmacy</title>
</head>
<body>
  <h1>Pharmacy</h1>
  <form action="/pills" method="GET">
      <input type="text">
      <input type="submit" value="Search">
  </form>
  <% for (Pill pill : pills) { %>
    <article>
      <h2><a href="<%= pill.getUrl() %>"><%= pill.getTitle() %></a></h2>
      <p><%= pill.getSummary() %></p>
    </article>
  <% } %>
</body>
</html>
