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

    <form action="/user" method="get">
        <%
            ArrayList flowers = (ArrayList) request.getAttribute("catalog");
            if (flowers != null) {
                if (flowers.size()==0)
                    out.print("Catalog is empty");
                else {
                    out.print("<TABLE border=1>");
                    out.print("<TR><TD>Id</TD><TD>Name</TD><TD>Cost</TD><TD>Amount</TD></TR>");

                    for (int i = 0; i < flowers.size(); i++) {
                        out.print("<TR>");
                        Flower tempFlower = (Flower) flowers.get(i);
                        out.print("<TD>" + tempFlower.getFlowerId()+ "</TD>");
                        out.print("<TD>" + tempFlower.getFlowerName()+ "</TD>");
                        out.print("<TD>" + tempFlower.getCost()+ "</TD>");
                        out.print("<TD>" + tempFlower.getAmount()+ "</TD>");

                        out.print("</TR>");
                    }
                    out.print("</TABLE>");
                }
            }
        %>
    </form>
</body>
</html>