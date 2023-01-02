package view;

import controller.Controller;
import controller.RequestHandler;

/**
 * This class generates the servers HTTP response with HTML.
 *
 * @author Alexander Lundqvist & Ramin Shojaei
 */
public class View {
    private String cookie;
    private final Controller controller;
    
    public View (Controller controller) {
        this.controller = controller;
    }
    
    public void displayResponse() {
        
    }

}
