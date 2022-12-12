package Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
Laboration 1: Socketar & Trådar

ChatServer: En instans av denna representerar servern. Har en tråd till var och 
en av de klienter som för närvarande är anslutna men också en tråd för att 
lyssna efter nya inkommande anslutningar från nya klienter, dessa bör 
representeras av en instans ClientHandler

Klienter ska kunna lämna chatten utan att krascha servern.
Om servern går ner ska klienterna ge ett meddelande istället för att krascha.
Både klient och server ska kunna köras på olika JVM. 
*/

/**
 * About this class:
 *
 * @author Alexander Lundqvist
 * Created: 15.11.2022
 */
public class ChatServer {    
    /*
    Parse the command line argument. If it does not exist or it is malformed,
    then use the default port. Default port might have to be changed depending 
    on the machine the server is running on.
    */
    private static int setPortNumber(String[] args) {
        int portNumber = 8080; // Default port
        if (args.length > 1) {
            System.out.println("Too many arguments! Using default port instead.");
        }
        if (args.length == 1) {
            try {
                portNumber = Integer.parseInt(args[0]);
            } catch(NumberFormatException exception) {
                System.out.println("Malformed port number! Using default port instead.");
            }
        }
        return portNumber;
    }
    
    /**
     * Main server process.
     * @param args[0] is the port number
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        int portNumber = setPortNumber(args); 
        int userId = 11; // Might remove
        
        
        /* 
        Initialize the server and start it.
        */
        try {
            System.out.println("Initializing server...");
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server has been started and is running on port " + portNumber);
        } catch (IOException exception) {
            System.err.println("Error starting server: " + exception);
            System.exit(1);
        }  
        
        /*
        Accept new clients with client handler threads.
        */
        while (true) {
            try {
                Socket clientConnection = serverSocket.accept();
                System.out.println("Accepted connection from: " + clientConnection.getInetAddress());
                ClientHandler client = new ClientHandler(clientConnection, userId);
                client.start();
                userId++;
                
            } catch (Exception exception) {
                System.err.println("Server error: " + exception);
                System.exit(1);
            }  
        }
    }
}
