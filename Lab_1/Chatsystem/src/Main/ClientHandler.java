package Main;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

/**
 * This class represents the clients that connect to the server. It serves two
 * purposes. One, listening for messages from other clients and two, broadcasts
 * the message from the client to all other clients currently connected to the
 * server.
 *
 * @author Alexander Lundqvist & Ramin Shojaei
 * Created: 15.11.2022
 */
public class ClientHandler extends Thread{
    private final String userId;
    private final Socket socket;
    private DataInputStream incoming; // Used for receiving network messages.
    private DataOutputStream outgoing; // Used for sending network messages.
    private static ArrayList<ClientHandler> clientList; // This holds all other active clients
          
    /**
     * 
     * @param clientConnection
     * @param clientList
     * @param userId
     * @throws IOException 
     */
    public ClientHandler(Socket clientConnection, ArrayList<ClientHandler> clientList, int userId) throws IOException {
        this.userId = "User" + userId;
        this.socket = clientConnection;
        this.clientList = clientList;
        this.incoming = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        this.outgoing = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }
    
    // Vet inte om den ska va här
    private static String getTimeStamp() {
        //Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String timeStamp = "[" + formatter.format(new Date()) + "]";
        return timeStamp;
    } 
    
    // Testa synchronized
    public static synchronized void sendMessage(String message) {
        ListIterator<ClientHandler> iterator = clientList.listIterator();
    }
    
    // Den här ska haa två trådar? Är det bara den här som körs när man kallar på tråden från server?
    
    /**
     * 
     */
    @Override
    public void run() {
        try {
            this.clientList.add(this);
            String message;
            while (true) {
                message = incoming.readUTF();
                if (message.startsWith("/quit")) {
                    // User quits
                }
                else {
                    sendMessage(getTimeStamp() + " " + this.userId + ": " + message);
                }
            }
            
        } catch (IOException exception) {
            System.err.println("Client handler error: " + exception);
        } finally {
            clientList.remove(this);
            sendMessage(getTimeStamp() + " User" + userId + " has left the chat...");
            try {
                socket.close();
            } catch (IOException exception) {
                System.err.println("Could not close client handler socket: " + exception);
            }
        }
    }
}
