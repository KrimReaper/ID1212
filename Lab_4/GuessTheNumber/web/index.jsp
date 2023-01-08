<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controller.*" %>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guess The Number Game</title>
    </head>
    <body>
        <div id="message">
            <%  
                String answer = (String) request.getSession().getAttribute("Answer");
                switch (answer) {
                    case "NO GUESS":
                        out.print("Welcome to the Number Guess Game. Guess a number between 1 and 100.");
                        break;
                    case "WRONG INPUT":
                        out.print("You have to guess on an integer between 1 and 100!");
                        break;
                    case "LOWER":
                        out.print("Nope, guess lower!");
                        break;
                    case "HIGHER":
                        out.print("Nope, guess higher!");
                        break;
                    case "CORRECT":
                        out.print("You made it!!!");
                        break;
                    default:
                        out.print("Welcome to the Number Guess Game. Guess a number between 1 and 100.");
                        break;
                }
                if (!answer.equals("CORRECT")) {
                    out.print("<br>You have made " + (int) request.getSession().getAttribute("AmountOfGuesses") + " guess(es).");
                }
            %>
        </div>
        <form id="form" action="/HTTPHandler" method="GET" >
            <input type="text" name="guess">
            <input type="submit" value="Guess">
        </form>
    </body>
</html>
