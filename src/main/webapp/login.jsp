
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html" >
    <title></title>
</head>
<body>
<div>
    <header>LOGIN</header>
    <form action="/login" method="post">
        <input type="text" name="login" placeholder="Login">
        <input type="text" name="password" placeholder="Password">
        <br>
        <button type="submit" name="Sign in" value="Sign in">Sign in</button>
    </form>
    <%
        String url="http://localhost:8888/registration.jsp?Sign+up=Sign+up";
        String onlyUrl=url.substring(0,url.lastIndexOf("?"));
    %>
    <form action="registration.jsp">
        <button type="submit" name="Sign up" value="Sign up">Sign up</button>
    </form>
</div>
</body>
</html>