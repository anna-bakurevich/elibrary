<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations" var="messages"/>

<head>
    <title>Private page</title>
</head>


<h1><fmt:message key="welcome.privatepage" bundle="${messages}"/>${login.firstName}!</h1>
<a href="<c:url value="/logout"/>"><fmt:message key="logout" bundle="${messages}"/></a>
<a href="<c:url value="/edit"/>"><fmt:message key="edit.heading" bundle="${messages}"/></a>

<%--страница для библиотекаря--%>
<c:if test="${login.role == 'LIBRARIAN'}">
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
    <a href="<c:url value="/editCatalogue"/>"><fmt:message key="edit.catalogue" bundle="${messages}"/></a>
</c:if>

<%--страница для клиента--%>
<c:if test="${login.role == 'CUSTOMER'}">
    <h3><fmt:message key="catalogue" bundle="${messages}"/></h3>
    <table>
        <tr>
            <th><fmt:message key="name.author" bundle="${messages}"/></th>
            <th><fmt:message key="surname.author" bundle="${messages}"/></th>
            <th><fmt:message key="title" bundle="${messages}"/></th>
            <th><fmt:message key="genre" bundle="${messages}"/></th>
            <th><fmt:message key="quantity" bundle="${messages}"/></th>

        </tr>
        <c:forEach items="${books}" var="book">
<%--        выводим на страницу только те книги, кол-во которых не ноль (доступные к заказу)--%>
            <c:if test="${book.count != 0}">
            <tr>
                <td>${book.authorFirstName}</td>
                <td>${book.authorLastName}</td>
                <td>${book.title}</td>
                <td>${book.genre}</td>
                <td>${book.count}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/privatePage">
                        <input name="order" type="hidden" value="${book.id}">
                        <input type="submit" value=<fmt:message key="order" bundle="${messages}"/>>
                    </form>
                </td>
            </tr>
            </c:if>
        </c:forEach>
    </table>
</c:if>
