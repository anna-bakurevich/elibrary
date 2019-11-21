<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springc" uri="http://www.springframework.org/tags" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="translations" var="messages"/>

<head>
    <title>Edit book catalogue</title>
</head>

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
        <tr>
            <td>${book.authorFirstName}</td>
            <td>${book.authorLastName}</td>
            <td>${book.title}</td>
            <td>${book.genre}</td>
            <td align="center">${book.count}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/editBookCatalogue">
                    <input name="bookDelete" type="hidden" value="${book.id}">
                    <input type="submit" style="height: 22px; width: 75px;"
                           value=<fmt:message key="delete" bundle="${messages}"/>>
                    <input name="countDelete" type="number" value=0 min=0 style="width: 40px">
                </form>
                <form method="post" action="${pageContext.request.contextPath}/editBookCatalogue">
                    <input name="bookAdd" type="hidden" value="${book.id}">
                    <input type="submit" style="height: 22px; width: 75px;"
                           value=<fmt:message key="add" bundle="${messages}"/>>
                    <input name="countAdd" type="number" value=0 min=0 style="width: 40px">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<form method="post" action="${pageContext.request.contextPath}/editBookCatalogue">
    <input name="pageNumber" type="hidden" value="${pageNumber}">

    <c:if test="${pageNumber>1}">
        <input name="prevPage" type="submit" value=<fmt:message key="button.prev" bundle="${messages}"/>>
    </c:if>

    <c:if test="${pageNumber<maxNumber}">
        <input name="nextPage" type="submit" value=<fmt:message key="button.next" bundle="${messages}"/>>
    </c:if>
</form>

<br>
<br>
<a href="<springc:url value="/librarianPage"/>"><fmt:message key="return.private" bundle="${messages}"/></a>