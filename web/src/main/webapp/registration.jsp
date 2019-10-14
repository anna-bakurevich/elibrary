<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations" var="messages"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
</head>
<body>

<div class="form">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <h1><fmt:message key="registration.heading" bundle="${messages}"/></h1><br>
    <form method="post" action="${pageContext.request.contextPath}/registration">

        <input type="text" required placeholder=
        <fmt:message key="name" bundle="${messages}"/> name="firstName"><br>
        <input type="text" required placeholder=
        <fmt:message key="surname" bundle="${messages}"/> name="lastName"><br><br>
        <input type="text" required placeholder=
        <fmt:message key="phone" bundle="${messages}"/> name="phone"><br><br>
        <input type="text" required placeholder=
        <fmt:message key="login" bundle="${messages}"/> name="login"><br><br>
        <input type="text" required placeholder=
        <fmt:message key="password" bundle="${messages}"/> name="password"><br><br>
        <input class="button" type="submit" value=<fmt:message key="signup" bundle="${messages}"/>>

    </form>
    <c:if test="${error}">
        <p style="color: red"><fmt:message key="error.registration" bundle="${messages}"/></p>
    </c:if>
</div>
</body>
</html>
