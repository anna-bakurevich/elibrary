<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Вход в систему</h1><br>
<form method="post" action="${pageContext.request.contextPath}/login">


    <input type="text" required placeholder="login" name="login"><br>
    <input type="text" required placeholder="password" name="password"><br><br>
    <input class="button" type="submit" value="Войти">
    <a href="<c:url value="/registration"/>">Зарегистрироваться</a>

</form>
</body>
</html>