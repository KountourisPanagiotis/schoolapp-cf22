<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Welcome User</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/schoolapp/static/css/hellouser.css">
</head>
<body>
<div class="container">
<h1>WELCOME at AUEB</h1>
<form method="post" action="${pageContext.request.contextPath}/index.jsp">
  <button class="back-btn" name="back-btn" value="main">Back To Home Screen</button>
</form>
</div>
</body>
</html>

