<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maksim.volnov
  Date: 28.06.2019
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <header action="/cart" method="get">CART</header>
</div>
<table border="1">
    <tr>
        <td>Flower</td>
        <td>Cost</td>
        <td>Pieces</td>
    </tr>
    <c:forEach items="${userCart}" var="flower">
        <jsp:useBean id="flower" class="com.entities.Flower" scope="session"></jsp:useBean>
        <tr>
            <td>${flower.name}</td>
            <td>${flower.cost}</td>
            <td>${flower.amount}</td>
            <td><input type="submit" value="Remove"></td>
        </tr>
    </c:forEach>
    <tr>
        <td>Total cost =</td>
        <td>
        <c:out value="${totalCost}"></c:out>
        </td>
    </tr>
</table>

</body>

</html>
