<%@ page import="java.util.ArrayList" %>
<%@ page import="com.entities.Flower" %>
<%@ page import="com.servlet.UserServlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>

<body>
<header>CATALOG</header>
<div>
    <input value="Cart" type="button" onclick="location.href='/cart'" />
    <input value="Exit" type="button" onclick="location.href='/'" />
    <jsp:useBean id="user" class="com.entities.User" scope="session"></jsp:useBean>
    <input type="hidden" name="userId" value="${user.id}"/>
    <c:out value="${user.name}"></c:out>
</div>
<div>
    <form action="/user" method="POST">
        <table border="1">
            <tr>
                <td>name</td>
                <td>cost</td>
                <td>amount</td>
                <td>order</td>
                <td></td>
            </tr>
            <c:forEach items="${catalog}" var="flower">
                <jsp:useBean id="flower" class="com.entities.Flower" scope="session"></jsp:useBean>
                <tr>
                    <td><input type="hidden" name="flowerId" value="${flower.flowerId}"/>
                    ${flower.name}</td>
                    <td>${flower.cost}</td>
                    <td>${flower.amount}</td>
                    <td><input type="number" name="orderAmount" value="0" min=0 max=${flower.amount} size=100px></td>
                    <td><input type="submit" value="Add to cart"></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</body>
</html>