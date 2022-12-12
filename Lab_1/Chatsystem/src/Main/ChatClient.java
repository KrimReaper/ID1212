package Main;

/*
ChatClient: En instans av denna representerar klienten. Har två trådar, en för 
att lyssna efter inkommande meddelanden från servern och en för att skicka 
meddelanden till servern.
*/

/**
 * About this class:
 *
 * @author Alexander Lundqvist
 * Created: 15.11.2022
 */
public class ChatClient implements Runnable{
    

    public ChatClient() {
    }
    
    
    
    public void run() {
        System.out.println("Write a message then hit enter. To exit, enter /quit.");
    }
}
