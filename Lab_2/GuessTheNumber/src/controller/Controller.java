package controller;

import java.net.Socket;
import java.util.ArrayList;
import model.Model;

/**
 * This class is the main controller for the application. Provides communication
 * between the model and the view objects by assigning handlers to each client
 * connected to the server.
 * Refer to <a href="https://folk.universitetetioslo.no/trygver/themes/mvc/mvc-index.html">MVC</a>
 *
 * @author Alexander Lundqvist
 */
public class Controller {
    private static Model model;
    private static ArrayList<RequestHandler> handlers; 
    
    public Controller (Model model) {
        this.model = model;
        this.handlers = new ArrayList<>();
    }
    
    public void createNewHandler(Socket socket) {
        RequestHandler handler = new RequestHandler(model, socket);
        handlers.add(handler);
        handler.run();
    }
    
    // Model.addSession();
    
    public void handleIncomingRequest(String cookie, String request) {
        
    }
    
    public void handleServerResponse(String response) {
        
    }
}
