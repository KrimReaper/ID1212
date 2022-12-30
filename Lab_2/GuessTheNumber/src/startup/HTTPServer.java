package startup;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import model.Model;


/**
 * This class represents a simple HTTP server that runs on localhost.
 * It contains the main method that runs the whole application
 *
 * @author Alexander Lundqvist & Ramin Shojaei
 */
public class HTTPServer {   
    private static final int port = 8080; // Change this if needed
    private static ArrayList<ClientHandler>
    
    /**
    * Main method used to start the server and application components.
    * 
    * @param args The application does not take any command line arguments.
    */
    public static void main(String[] args) {
        try {
            System.out.println("Initializing server...");
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server has been started and is running on port " + port);
            Model model = new Model();
            
            /*
            The thread that listens for new connections and creates new game sessions
            for each individual client.
            */
            while (true) {
                System.out.println("Listening for connection request...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from: " + clientSocket.getInetAddress());
                
                
            }
            
        } catch (IOException exception) {
            System.err.println("Server error: " + exception.getMessage());
            exception.printStackTrace();
            System.exit(1);
        }
    }

}
