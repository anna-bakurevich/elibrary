<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>

<div class="form">

    <h1>Редактирование личных данных</h1><br>
    <form method="post" action="${pageContext.request.contextPath}/edit">

        <input type="text" required placeholder="Имя" name="firstName" value="${login.firstName}"><br>
        <input type="text" required placeholder="Фамилия" name="lastName" value="${login.lastName}"><br><br>
        <input type="text" required placeholder="Номер телефона" name="phone" value="${login.phone}"><br><br>
        <input class="button" type="submit" value="Сохранить изменения">

    </form>
</div>
</body>
