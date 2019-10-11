<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<div class="form">

    <h1>Регистрация в системе</h1><br>
    <form method="post" action="${pageContext.request.contextPath}/registration">

        <input type="text" required placeholder="Имя" name="firstName"><br>
        <input type="text" required placeholder="Фамилия" name="lastName"><br><br>
        <input type="text" required placeholder="Номер телефона" name="phone"><br><br>
        <input type="text" required placeholder="Имя пользователя" name="login"><br><br>
        <input type="text" required placeholder="Пароль" name="password"><br><br>
        <input class="button" type="submit" value="Регистрироваться">

    </form>

    <p style="color: red">${error}</p>
</div>
</body>
</html>
