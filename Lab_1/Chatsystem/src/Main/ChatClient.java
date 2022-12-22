package Main;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents the client/program/interface that allows the user to 
 * connect and interact with the chat server. Utilizes two threads for sending
 * messages and listening for messages from other clients connected to the chat.
 *
 * @author Alexander Lundqvist & Ramin Shojaei
 * Created: 15.11.2022
 */
public class ChatClient {
    private static final int portNumber = 8420; // Safe to use https://www.speedguide.net/port.php?port=8420
    private static final String hostname = "localhost";
    
    /**
     * Main client process.
     * @param args should be empty
     */
    public static void main(String[] args) {
        Socket socket = null;
        try {
            System.out.println("Connecting to the chat...");
            socket = new Socket(hostname, portNumber);
            System.out.println("You are now connected to the chat!");
            System.out.println("Write a message then hit enter to send. Type /quit to exit the application.");
            
        } catch (IOException exception) {
            System.err.println("Could not connect to chat server: " + exception.getMessage());
            exception.printStackTrace();
        }
        
        // If connection is ok, then we can try starting the stream threads.
        try {
            Thread clientReaderThread = new Thread(new ClientReader(socket));
            Thread clientWriterThread = new Thread(new ClientWriter(socket));
            clientReaderThread.start();
            clientWriterThread.start();
            
            clientReaderThread.join();
            
        } catch (Exception exception) {
            System.err.println("Client thread error: " + exception.getMessage());
            exception.printStackTrace();
        } finally {
            System.out.println("You have been disconnected from the chat!"); 
            if (!socket.isClosed() && socket != null) {
                try {
                    socket.close();
                } catch (IOException exception) {
                    System.err.println("Could not close client socket: " + exception.getMessage());
                }
            } 
        }
    }  
}

/**
* A runnable listener responsible for reading the user's input and passing it 
* on to the chat server.
*/
class ClientReader implements Runnable {
    private Socket socket;
    private DataOutputStream outgoing; // Used for sending network messages.
    
    /**
     * Constructor.
     * @param socket is the clients socket
     */
    public ClientReader(Socket socket) {
        this.socket = socket;
        try {
            this.outgoing = new DataOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
        } catch (IOException exception) {
            System.err.println("Error getting output stream: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            while (!this.socket.isClosed() && this.socket != null && this.outgoing != null) {
                String message = userInput.readLine();
                if (message.isEmpty()) {
                    continue;
                }
                this.outgoing.writeUTF(message);
                this.outgoing.flush();
                if (message.equals("/quit")) {
                    break;
                }
            }      
        } catch (IOException exception) {
            System.err.println("Client input error: " + exception.getMessage());
            exception.printStackTrace();       
        } 
    }
}

/**
* A runnable listener responsible for incoming messages from other clients
* currently connected to the chat.
*/
class ClientWriter implements Runnable {
    private Socket socket;
    private DataInputStream incoming; // Used for receiving network messages.
    
    /**
     * Constructor.
     * @param socket is the clients socket 
     */
    public ClientWriter(Socket socket){
        this.socket = socket;
        try {
            this.incoming = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
        } catch (IOException exception) {
            System.err.println("Error getting input stream: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
    
    /**
     * A simple function to create a timestamp for chat messages. When assigned
     * here instead of by the handler or server, we get time zone independency.
     * @return the timestamp 
     */
    private static String getTimestamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String timeStamp = "[" + formatter.format(new Date()) + "]";
        return timeStamp;
    }
    
    @Override
    public void run(){
        try { 
            while (!this.socket.isClosed() && this.socket != null && this.incoming != null) {
                String message = this.incoming.readUTF(); // Error here...
                System.out.println(getTimestamp() + " " + message);
            }
            
        } catch (EOFException exception) {
            // Unfortunate side effect from DataInputStream is that we have to catch this
        } catch (IOException exception) {
            System.out.println("You have been disconnected from the chat!"); 
            System.err.println("Client output error: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}

