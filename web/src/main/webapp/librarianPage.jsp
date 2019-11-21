<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springc" uri="http://www.springframework.org/tags" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations" var="messages"/>

<head>
    <title>Librarian page</title>
</head>


<h1><fmt:message key="welcome.privatepage" bundle="${messages}"/>${login.firstName}!</h1>
<a href="<springc:url value="/logout"/>"><fmt:message key="logout" bundle="${messages}"/></a>
<a href="<springc:url value="/edit"/>"><fmt:message key="edit.heading" bundle="${messages}"/></a>

<%--страница для библиотекаря--%>

<h3><fmt:message key="user.list" bundle="${messages}"/></h3>
<table>
    <tr>
        <th><fmt:message key="name" bundle="${messages}"/></th>
        <th><fmt:message key="surname" bundle="${messages}"/></th>
        <th><fmt:message key="phone" bundle="${messages}"/></th>

    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.phone}</td>
            <td>
                <c:if test="${login.id != user.id}">
                    <form method="post" action="${pageContext.request.contextPath}/privatePage">
                        <input name="deleteId" type="hidden" value="${user.id}">
                        <input type="submit" value=<fmt:message key="delete" bundle="${messages}"/>>
                    </form>
                </c:if>
            </td>

        </tr>
    </c:forEach>
</table>
<a href="<springc:url value="/editBookCatalogue"/>"><fmt:message key="edit.catalogue" bundle="${messages}"/></a>



