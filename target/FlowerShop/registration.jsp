
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html" >
    <title></title>
</head>
<body>
<div>
    <header>REGISTRATION</header>
    <form action="/registration" method="post"> <br>
        <input type="text" name="login" placeholder="Login"> <br>
        <input type="text" name="password" placeholder="Password"> <br>
        <input type="text" name="name" placeholder="Name"> <br>
        <input type="text" name="eMail" placeholder="E-mail"> <br>

    <%
        String url="http://localhost:8888/registration.jsp?login=user2&password=user2&name=user2&eMail=user2@gmail.com";
        String onlyUrl=url.substring(0,url.lastIndexOf("?"));
    %>
        <button type="submit" name="Sign up" value="Sign up" charset="UTF-8">Register</button>
    </form>
</div>
</body>
</html>