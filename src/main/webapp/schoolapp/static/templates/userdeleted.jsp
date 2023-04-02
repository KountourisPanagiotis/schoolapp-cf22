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
    <p>User: ${requestScope.user.id} ${requestScope.user.username} ${requestScope.user.password}
        was deleted</p>
    <a href="${pageContext.request.contextPath}/schoolapp/static/templates/usersmenu.jsp">Επιστροφή</a>
</div>
</body>
</html>
