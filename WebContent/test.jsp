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
<div>
    <form method="post" action="/login/">
        <table>
            <tr>
                <td><label for="loginField">?????</label></td>
                <td><input id="loginField" type="text" name="login"></td>
            </tr>
            <tr>
                <td><label for="passwordField">??????</label></td>
                <td><input id="passwordField" type="text" name="password"></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input type="submit" value="?????" onclik="Authorisation.authentication(loginField, passwordField)"></td>
            </tr>
            <tr>
                <td colspan="2" style="text-allign: center"><input type="button" value="???????????" onclick="location.href=''"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>