<%-- 
    Document   : login
    Author     : Alexander Lundqvist & Ramin Shojaei

    This is the login page for the game.   
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz login</title>
        <!-- Bootstrap latest compiled and minified core CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" 
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" 
        integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    </head>
    <body>
        <h2>ID1212: Lab 4 Quiz</h2>
        <p>Login to start a quiz!</p>
        <%-- submit calls the SessionController to handle the login attempt --%>
        <form id="form" action="SessionController" method="POST">
            <input name="username" type="text" value="Username" placeholder="email" required/>
            <input name="password" type="text" value="Password" placeholder="password" required/>
            <input name="login" type="submit"  value="Login"/>
        </form>
        <%-- Conditional rendering based on session attributes --%>
        <%  
            String message = (String) request.getSession().getAttribute("LoginResponse");
            if (message != null && message.equals("Error")) {
                out.print("<p>The username and password combination you wrote is invalid!</p>");
            }
        %>
    </body>
</html>
