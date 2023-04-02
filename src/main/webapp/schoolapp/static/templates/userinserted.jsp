<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Επιτυχής Εισαγωγή</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/schoolapp/static/css/userstemplategeneric.css">
</head>
<body>
<div class="centering">
<h1>User inserted successfully</h1>
<div>
    <p>${requestScope.insertedUser.username}</p>
    <p>${requestScope.insertedUser.password}</p>
    <p></p>
</div>

<div>
    <a href="${pageContext.request.contextPath}/schoolapp/static/templates/usersmenu.jsp">Επιστροφή</a>
</div>
</div>
</body>
</html>