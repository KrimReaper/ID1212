<%-- 
    Document   : index
    Author     : Alexander Lundqvist & Ramin Shojaei

    This JSP document is the view component of the number guessing game. It relays
    the attempted guess to the HTTPHandler (Controller) and renders the web page
    according to the data stored in the HTTPSession.  
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guess The Number Game</title>
    </head>
    <body>
        <div id="message">
            <%-- Conditional rendering based on session attributes --%>
            <%  
                if (request.getSession(false).getAttribute("GuessBean") == null) {
                    out.print("Welcome to the Number Guess Game. Guess a number between 1 and 100.");
                }
                else {
                    String answer = (String) request.getSession(false).getAttribute("Answer");
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
                            out.print("Something went wrong...");
                            break;
                    }
                    if (!answer.equals("CORRECT")) {
                        out.print("<br>You have made " + (int) request.getSession(false).getAttribute("AmountOfGuesses") + " guess(es).");
                    }
                }
            %>
        </div>
        <%-- submit calls the HTTPHandler.java method doGet() to handle the guess --%>
        <form id="form" action="HTTPHandler" method="GET" >
            <input type="text" name="guess">
            <input type="submit" value="Guess">
        </form>
    </body>
</html>
