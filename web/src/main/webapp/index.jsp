<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Start</title>
</head>
<body>

    <h1 align="center">Добро пожаловать в электронную библиотеку!</h1><br>
    <form method="post" action="${pageContext.request.contextPath}/index">

        <div style="text-align: center;">
        <input class="button" type="submit" value="Продолжить">
        </div>

    </form>

</body>
</html>