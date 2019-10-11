<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <title>privatePage</title>
</head>


<h1>Добро пожаловать, ${login.firstName}!</h1>
<a href="<c:url value="/logout"/>">Выйти</a>
<a href="<c:url value="/edit"/>">Изменить личные данные</a>
<c:if test="${login.role == 'LIBRARIAN'}">
    <h3>Список пользователей</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Телефон</th>

        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.phone}</td>
                <td>
                    <c:if test="${login.id != user.id}">
                    <form method="post" action="${pageContext.request.contextPath}/privatePage">
                        <input name="deleteId" type="hidden" value="${user.id}">
                        <input type="submit" value="Удалить">
                    </form>
                    </c:if>
                </td>

            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${login.role == 'CUSTOMER'}">
    <h3>Каталог книг</h3>
    <table>
        <tr>
            <th>Имя автора</th>
            <th>Фамилия автора</th>
            <th>Название книги</th>
            <th>Жанр</th>
            <th>Количество</th>

        </tr>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.authorFirstName}</td>
                <td>${book.authorLastName}</td>
                <td>${book.title}</td>
                <td>${book.genre}</td>
                <td>${book.count}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
