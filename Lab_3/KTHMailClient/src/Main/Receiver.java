//package Main; // Comment out to compile and run from terminal

import java.io.*;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * This class takes a @kth.se mail address and password combination and uses
 * the Internet Message Access Protocol (IMAP) connect to the remote email account
 * and retrieves a mail.
 * 
 * Reference: <a href="https://en.wikipedia.org/wiki/Internet_Message_Access_Protocol">Wikipedia</a>
 * 
 * Settings for sending mail.
 * Server: webmail.kth.se
 * Port: 993
 * Protocol: IMAP (SSL/TLS)
 * Authentication: Normal password
 *
 * @author Alexander Lundqvist & Ramin Shojaei
 */
public class Receiver {
    public static final String SERVER = "webmail.kth.se";
    public static final int PORT = 993;
    private static String username;
    private static String password;
    private static String response;
    private static int prefix = 0;
    private static String login;
    private static String inbox;
    private static String logout;
    private static String firstMail;

    public static void main(String[] args) {
        try {

        // Skapar ett Socket factory för SSL Sockets.
        SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        // Vi skapade först en standard socket och slår nu in den i en SSLSocket.
        SSLSocket sslSocket = (SSLSocket) socketFactory.createSocket(SERVER, PORT);
        // Sätter upp Socketens input- och outputstream
        BufferedReader input = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()));

        
            try {
                //För att kunna skriva det som efterfrågas i terminalen använder vi oss av System.console()
                Console console = System.console();
                //Skriver in användarnamn och lösenord
                username = console.readLine("Type your Username: ");
                password = new String(console.readPassword("Your Password: "));
                
                input = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                output = new PrintWriter(sslSocket.getOutputStream(), true);
            } 
            catch (IOException exception) {
                System.err.println("Error: " + exception.getMessage());
                //exception.printStackTrace();
            }
  
            //Referens till detta: https://www.rfc-editor.org/rfc/rfc3501#section-6.4.5
            // Läser in hälsning från servern, från sslSocket.
            response = input.readLine();
            System.out.println(response);  //Response från Server

            // Måste innehålla ett prefix "a" tillsammans med något nummer. Vi har a01 för inloggning.
            login = "a01 LOGIN " + username + " " + password;  //loggar in med användarnamn o lösenord
            inbox = "a02 SELECT INBOX"; //  inkorg a002
            firstMail = "a03 FETCH 1 BODY[TEXT]"; //Hämtar äldsta e-postmeddelandet i inkorgen genom detta kommando.
            logout = "999 LOGOUT"; //loggar ut
            output.println(login);
            output.println(inbox);
            output.println(firstMail);
            output.println(logout);
            messageReader(input);  
            messageReader(input);
            messageReader(input);
            messageReader(input);

            //Stänger ssLsocket, input- och outputstream
            sslSocket.close();
            input.close();   
            output.close();

        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }


    public static void messageReader(BufferedReader input) throws IOException {
        String message;
        while ((message = input.readLine()) != null) {
            System.out.println(message);
            if ( message.contains("a" + prefix))
                break;
        }
    }

}