package Main;

import java.net.ServerSocket;

/*
Laboration 1: Socketar & Trådar

Uppgiften består i att skriva ett socket-baserat chatsystem med hjälp av 
klasserna java.net.Socket och java.net.ServerSocket. Det kan bestå av bl.a. 
föjande klasser, förslagsvis:

ChatServer: En instans av denna representerar servern. Har en tråd till var och 
en av de klienter som för närvarande är anslutna men också en tråd för att 
lyssna efter nya inkommande anslutningar från nya klienter, dessa bör 
representeras av en instans ClientHandler


Det finns inget krav på att hantera användarinloggning eller chattrum. Lätt och 
enkel. Dessa är dock krav:

Klienter ska kunna lämna chatten utan att krascha servern.
Om servern går ner ska klienterna ge ett meddelande istället för att krascha.
Både klient och server ska kunna köras på olika JVM. 

Testa att programmet verkligen fungerar genom att skapa en server och två 
klienter, du kommer alltså att ha tre stycken samtidiga javaprogram som körs. 
Samtliga program ska kunna köras från olika datorer, detta kan ni testa genom 
att använda t ex ssh:a mot t ex student-shell.sys.kth.se för att ansluta 
tillbaka till er dator.
*/

/**
 * About this class:
 *
 * @author Alexander Lundqvist
 * Created: 15.11.2022
 */
public class ChatServer {
    private static int PORT;
    private static ServerSocket serverSocket;
    // Array med trådar för clienter??
    // ClientHandler clientHandler = new ClientHandler();
    

    public static void main(String[] args) {
        System.out.println("Initializing server...");
        
        /* 
        Rudimentary error handling as we only want to run the server without
        any errors whatsoever.
        */
        try{
            /* 
            In case we want to test running the server on other ports 
            without having to change it in the code. 
            */
            if      (args.length <= 0) {PORT = 8080;}
            else    {PORT = Integer.parseInt(args[0]);}
            
            /*
            Initialize the server and start it
            */
            serverSocket = new ServerSocket(PORT);
            
            /*
            Accept new clients with client handler thread
            */
            
            
            System.out.println("Server has been started and is running on port" + PORT);
        }catch (Exception e){
            System.out.println("Server could not be initialized: " + e);
        }
    }
}
