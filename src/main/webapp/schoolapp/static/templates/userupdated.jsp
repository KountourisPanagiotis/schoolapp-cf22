<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/schoolapp/static/css/userstemplategeneric.css">
</head>
<body>
<div class="centering">
<h1>Νέα Στοιχεία User</h1>
<p>user ${requestScope.user.username}</p>
<p>user ${requestScope.user.password}</p>
<a href="${pageContext.request.contextPath}/schoolapp/static/templates/usersmenu.jsp">Επιστροφή</a>
</div>
</body>
</html>
