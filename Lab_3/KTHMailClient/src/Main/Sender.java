//package Main; // Comment out to compile and run from terminal

import java.io.*;
import java.net.Socket;
import java.util.Base64;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * This class takes a @kth.se mail address, password combination, message and a 
 * reciever email address. It then uses the Simple Mail Transfer Protocol (SMTP) 
 * to connect to the remote email account and send the mail via the mail server 
 * the specified recipient email address.
 * 
 * Reference: <a href="https://en.wikipedia.org/wiki/Simple_Mail_Transfer_Protocol">Wikipedia</a>
 * 
 * Settings for receiving mail.
 * Server: smtp.kth.se
 * Port: 587
 * Protocol: SMTP (STARTTLS)
 * Authentication: Normal password
 *
 * @author Alexander Lundqvist & Ramin Shojaei
 */
public class Sender {
    private static final String SERVER = "smtp.kth.se";
    private static final int PORT = 587;
    private static String username;
    private static String password;
    private static String receiver;
    private static String messageSend;

    
    public static void main(String[] args) throws IOException, InterruptedException {

        //För att kunna skriva det som efterfrågas i terminalen använder vi oss av System.console();
        Console console = System.console();
        username = console.readLine("Type your Username:  ");
        password = new String(console.readPassword("Your Password:  "));
        receiver = console.readLine("Who do you want to send mail to?:   ");
        messageSend = console.readLine("Type your message here:   ");

        // Skapa en ett objekt i form av en okrypterad socket
        Socket socket = new Socket(SERVER, PORT);

        // Sätter upp Socketens input- och outputstream
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        //Läser in första meddelandet från Servern
        String message = input.readLine();  //Läser in ett medelande och sen skriver ut (output) det.
        System.out.println(message);

        // Initierar SMTP konversation med servern. EHLO stödjer ESMTP.
        output.println("EHLO " + SERVER);
        output.flush();
        messageReader(input);

        // StartTLS är ett protokollkommando
        // Används för att informera servern om att klienten vill uppgradera från osäker till säker anslutning med TLS eller SSL.
        output.println("STARTTLS");
        output.flush();
        message = input.readLine();

        // Skickar bara lösenordet utifall att det är en säker anslutning
        if (message.contains("220")) {
            System.out.println(message);
        }
        else {
            System.out.println(message);
            System.out.println("Connection failed");
            System.exit(1);
        }


        // Skapar ett Socket factory för SSL Sockets.
        SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        // Vi skapade först en standard socket och slår nu in den i en SSLSocket.
        SSLSocket sslSocket = (SSLSocket) socketFactory.createSocket(socket, SERVER, PORT, true); 
        
        input = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
        output = new PrintWriter(sslSocket.getOutputStream(), true);

        // EHLO commanded skrivs in nu igen efter den säkra anslutningen har skett
        output.println("EHLO " + SERVER);
        messageReader(input);

        // Försöker logga in (Authentication Login)
        output.println("AUTH LOGIN");
        messageReader(input);

        //Encodear till Base 64 format, det är en metod för att binärt data ska kunna kodas med 7 bitars ASCII-tecken
        Base64.Encoder encoder = Base64.getEncoder();
        String usernameEncode = encoder.encodeToString(username.getBytes());
        String passwordEncode = encoder.encodeToString(password.getBytes());
        
        // Skickar iväg det encodeade användarnamnet och lösenordet
        output.println(usernameEncode);
        readingMsgFromServer(input);
        output.println(passwordEncode);
        readingMsgFromServer(input);

        // Kommandot MAIL FROM initierar en överföring av e-post.
        output.println("MAIL FROM:<" + username + "@kth.se>");
        readingMsgFromServer(input);
        // RCPT TO specificerar E-mail addressen av mottagaren
        // Vill man skicka det till flera kan man skapa flera RCPT TO under, det går bra
        //Man borde få ut 250 OK
        output.println("RCPT TO:<" + username + "@kth.se>");
        readingMsgFromServer(input);
        // Kommandot Data definierar informationen som datatexten har i meddelandet.
        output.println("DATA");
        readingMsgFromServer(input);
        output.println(messageSend);
        output.println(".");
        readingMsgFromServer(input);
        //Borde inte komma fler meddelanden efter vi nått 221.
        output.println("QUIT");
        messageReader(input);

        System.out.println("END");
        input.close();
        output.close();
        sslSocket.close();
        socket.close();
    }
    
    //Skriver ut det resulterande svaret från servern
    public static void messageReader(BufferedReader input) throws IOException {
        String message;
        while ((message = input.readLine()) != null) {
            System.out.println(message);
            if ( message.contains("220 ") || message.contains("250 ") || message.contains("334 "))
                break;
        }
    }

    public static void readingMsgFromServer(BufferedReader input) throws IOException {
        System.out.println(input.readLine());
    }

}
