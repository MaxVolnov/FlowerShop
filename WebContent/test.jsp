<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="com.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title></title>
</head>
<body>

<jsp:useBean id="portfolio" class="com.mainPackage.PortfolioBean" />
<h1>Database result</h1>
<%
    java.util.Iterator folio = portfolio.getPortfolio();
    Stock stock = null;
%>

<% while (folio.hasNext()) { %>
<% stock = (Stock)folio.next(); %>

<a href="#"><%=stock.getName() %></a>

<% } %>

</body>
</html>