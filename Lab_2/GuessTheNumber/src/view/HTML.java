package view;

/**
 * This class contains predefined HTML strings that are used to construct the
 * browser view.
 *
 * @author Alexander Lundqvist
 */
public final class HTML {
    
    /**
     * We keep the constructor private to prevent instantiation.
     */
    private HTML() {}
    
    public static final String INITIAL_PAGE = 
            "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "    <head>\n" +
            "        <meta charset=\"UTF-8\">\n" +
            "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "        <title>Number Guess Game</title>\n" +
            "    </head>\n" +
            "    <body> \n" +
            "        <div>Welcome to the Number Guess Game. Guess a number between 1 and 100.</div>\n" +
            "        <form name=\"guessform\">\n" +
            "            <input type=text name=guess>\n" +
            "            <input type=submit value=\"Guess\">\n" +
            "        </form>\n" +
            "    </body>\n" +
            "</html>";
   

    public static final String RESPONSE_PREFIX = 
            "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "    <head>\n" +
            "        <meta charset=\"UTF-8\">\n" +
            "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "        <title>Number Guess Game</title>\n" +
            "    </head>\n" +
            "    <body> \n";
    
    public static final String RESPONSE_POSTFIX = 
            "        <form name=\"guessform\">\n" +
            "            <input type=text name=guess>\n" +
            "            <input type=submit value=\"Guess\">\n" +
            "        </form>\n" +
            "    </body>\n" +
            "</html>";
    
    public static final String RESPONSE_CORRECT = 
            "        <div>You made it!!!</div>";
    
    public static final String RESPONSE_TOO_LOW = 
            "        <div>Nope, guess lower!</div>";
    
    public static final String RESPONSE_TOO_HIGH = 
            "        <div>Nope, guess higher!</div>";
    
    public static final String RESPONSE_ERROR = 
            "        <div>You have to guess on an integer!</div>";
    
    public static final String BAD_REQUEST = 
            "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "    <head>\n" +
            "        <meta charset=\"UTF-8\">\n" +
            "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "        <title>Number Guess Game</title>\n" +
            "    </head>\n" +
            "    <body> \n" +
            "        <div>Could not find the requested page.</div>\n" +
            "    </body>\n" +
            "</html>";
    
    /**
     * Creates the string which displays the amount of guesses that have been made.
     * @param amountOfGuesses the amount of guesses the client has done
     * @return The response string
     */
    public static String countResponse(int amountOfGuesses) {
        String response = 
                "        <div>You have made " + amountOfGuesses + " guess(es)</div>\n";
        return response;
    }
}
