<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Menu</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/schoolapp/static/css/menu.css">
</head>
<body>
<div class="button-container">
  <form method="post" action="${pageContext.request.contextPath}/schoolapp/static/templates/teachersmenu.jsp">
    <button class="button" name="button" value="teachers">Teachers</button>
  </form>
  <form method="post" action="${pageContext.request.contextPath}/schoolapp/static/templates/usersmenu.jsp">
    <button class="button" name="button" value="users">Users</button>
  </form>
</div>
</body>
</html>

