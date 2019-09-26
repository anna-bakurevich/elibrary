<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Start</title>

</head>
<body>

<div class="form">

    <h1>Добро пожаловать в электронную библиотеку!</h1><br>
    <form method="post" action="${pageContext.request.contextPath}/index">

        <%--        <input type="text" required placeholder="login" name="login"><br>--%>
        <%--        <input type="password" required placeholder="password" name="password"><br><br>--%>
        <input class="button" type="submit" value="Продолжить">

    </form>
</div>
</body>
</html>