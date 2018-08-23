<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
  <c:forEach items="${pills}" var="pill">
    <article>
      <h2><a href="${pill.url}">${pill.title}</a></h2>
      <p>${pill.summary}</p>
    </article>
  </c:forEach>
</body>
</html>
