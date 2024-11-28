<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>${title_page}</title>
</head>
<body>
<h2>Hello!</h2>
<ul>
    <c:forEach var="user" items="${users}">
        <li>${user.name}</li>
    </c:forEach>
</ul>
</body>
</html>
