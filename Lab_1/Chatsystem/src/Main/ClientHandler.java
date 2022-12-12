package Main;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * About this class:
 *
 * @author Alexander Lundqvist
 * Created: 15.11.2022
 */
public class ClientHandler extends Thread{
    private final int userId; // Aight den här är fel misstänker jag
    private final Socket socket;
    private DataInputStream incoming; // Used for receiving network messages.
    private DataOutputStream outgoing; // Used for sending network messages.
    /*
    This will hold all the client handler threads
    */
    ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
    
            
    // Default constructor
    public ClientHandler(Socket clientConnection, int userId) throws IOException {
        this.userId = userId;
        this.socket = clientConnection;
        incoming = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        outgoing = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }
    
    // Vet inte om den ska va här
    private static String getTime() {
        //Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String timeStamp = formatter.format(new Date());
        return timeStamp;
    }
    
    private static String messageTag(int userId) {
        String messageTag = "[" + getTime() + "] " + "User" + userId + ": ";
        return messageTag;
    }
    
    // Den här ska haa två trådar? Är det bara den här som körs när man kallar på tråden från server?
    public void run() {
        try {
            
        } catch (IOException e) {
			//e.printStackTrace();
        } finally {
            clients.remove(this);
            broadcast("[" + userId + "] has left the room!");
                try {
                    socket.close();
                } catch (IOException e) {
                        //e.printStackTrace();
                }
        }
    }
}
