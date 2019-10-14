<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations" var="messages"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Start</title>
</head>
<body>

    <h1 align="center"><fmt:message key="welcome" bundle="${messages}"/></h1><br>
    <form method="post" action="${pageContext.request.contextPath}/index">

        <div style="text-align: center;">
        <input class="button" type="submit" value=<fmt:message key="button.continue" bundle="${messages}"/>>
        </div>

    </form>

</body>
</html>