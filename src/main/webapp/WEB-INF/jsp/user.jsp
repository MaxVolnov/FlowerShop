
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
<header action="/user" method="get">CATALOG</header>
<div>
        <jsp:useBean id="user" class="com.entities.User" scope="session"></jsp:useBean>
        <c:out value="${user.name}"></c:out><br>
        Balance: <c:out value="${user.balance}"></c:out><br>
        Discount<c:out value="${user.discount}"></c:out>%<br>
        <input value="Cart" type="button" onclick="location.href='/cart'" />
        <form action="/exit" method="get">
            <input value="Exit" type="submit"/>
        </form>
</div>
<div>
        <input type="hidden" name="userId" value="${user.id}"/>
        <table border="1">
            <tr>
                <td>id</td>
                <td>name</td>
                <td>cost</td>
                <td>amount</td>
                <td>order</td>
            </tr>
            <c:forEach items="${catalog}" var="flower">
                <jsp:useBean id="flower" class="com.entities.Flower" scope="session"></jsp:useBean>
                <tr>
                    <form action="/user" method="post">
                    <td id="${flower.flowerId}" name="flowerId"><input type="hidden" name="flowerId" value="${flower.flowerId}">${flower.name}</td>
                    <td>${flower.cost}</td>
                    <td>${flower.amount}</td>
                    <td><input type="number" name="orderAmount" value="0" min=0 max=${flower.amount} size=100px></td>
                    <td><input type="submit" value="Add to cart"></td>
                    </form>
                </tr>
            </c:forEach>
        </table>

</div>
</body>
</html>