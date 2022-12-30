package controller;

import java.net.Socket;
import java.security.SecureRandom;
import java.util.Base64;
import model.Model;

/**
 * This class represents a controller for the application. Each new client gets 
 * assigned a RequestHandler object. It manages incoming HTTP requests from the 
 * browser and relays the models response to a view.
 *
 * @author Alexander Lundqvist & Ramin Shojaei
 */
public class RequestHandler implements Runnable{
    private Socket socket;
    private Model model;
    
    
    public RequestHandler(Socket socket) {
        this.socket = socket;
    }
       
    /**
     * This method creates a URL-safe base64-encoded 160-bit random value token
     * that can be used as a cookie.
     * Refer to <a href="https://neilmadden.blog/2018/08/30/moving-away-from-uuids/">Neil Madden</a>
     * @return the cookie string
     */
    public static String generateCookie() {
        SecureRandom random = new SecureRandom();
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        
        byte[] buffer = new byte[20];
        random.nextBytes(buffer);
        return encoder.encodeToString(buffer);
    }
    
    private void handleGETrequest() {
        
    }
    
    
    private void handlePOSTrequest() {
        
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
