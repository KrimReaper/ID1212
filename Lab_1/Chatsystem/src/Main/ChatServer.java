package Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This class represents a simple multi-threaded server 
 *
 * @author Alexander Lundqvist & Ramin Shojaei
 * Created: 15.11.2022
 */
public class ChatServer {    
    
    /**
     * Main server process.
     * @param args should empty
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        int portNumber = 8420; // Safe to use https://www.speedguide.net/port.php?port=8420
        int userId = 1; // Simple user identifier
        ArrayList<ClientHandler> clientList = new ArrayList<ClientHandler>();
        
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
            System.out.println("Listening for connection request...");
            try (Socket clientConnection = serverSocket.accept()) { 
                System.out.println("Accepted connection from: " + clientConnection.getInetAddress());
                System.out.println("Creating handler for this client");
                ClientHandler client = new ClientHandler(clientConnection, clientList, userId);
                //clientList.add(client);
                client.start();
                userId++;
                
            } catch (Exception exception) {
                System.err.println("Server error: " + exception);
                System.exit(1);
            }  
        }
    }
}
