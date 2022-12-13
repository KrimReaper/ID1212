package Main;

/*
ChatClient: En instans av denna representerar klienten. Har två trådar, en för 
att lyssna efter inkommande meddelanden från servern och en för att skicka 
meddelanden till servern.
*/

/**
 * This class represents the client/program/interface that allows the user to 
 * connect and interact with the chat server.
 *
 * @author Alexander Lundqvist & Ramin Shojaei
 * Created: 15.11.2022
 */
public class ChatClient implements Runnable{
    
    /**
     * 
     */
    public ChatClient() {
    }
    
    

    /**
     * 
     */
    @Override
    public void run() {
        System.out.println("Write a message then hit enter. To exit, enter /quit.");
    }
}
