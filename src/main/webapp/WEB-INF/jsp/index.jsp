<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>Pharmacy</title>
  <link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Bitter" rel="stylesheet">
  <link href="index.css" rel="stylesheet">
  <link rel="icon" href="cross.png">
  <script src="webjars/jquery/3.0.0/jquery.min.js"></script>
  <script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
  <div class="container">
    <div class="container" id="logo_container">
      <img src="cross.png" class="logo">
    </div>
    <div class="jumbotron text-center">
      <h1 class="display-4">PHARMACY</h1>
    </div>
    <form action="/pills" method="GET">
      <div class="form-group">
        <div class="col-xs-6 col-xs-offset-3">
          <input type="query" name="query" class="form-control form-control-lg shadow p-3 mb-5 bg-white rounded" >
        </div>
      </div>
      <div class="form-group">
        <div class="container" id="button_container">
          <div id="search_btn">
              <button type="submit" class="btn btn-outline-secondary btn-block">Search</button>
          </div>
        </div>
      </div>
    </form>

    <c:forEach items="${pills}" var="pill">
      <article>
        <h2><a href="${pill.url}" target="_blank">${pill.title}</a></h2>
        <p>${pill.summary}</p>
      </article>
    </c:forEach>
  </div>
</body>
</html>
