<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <form action="/library/login"> 
            Please enter your username <input type="text" name="username"/><br> 
            Please enter your password <input type="text" name="password"/> 
            <input type="submit" value="submit"> 
        </form> 
    </body>
</html>
