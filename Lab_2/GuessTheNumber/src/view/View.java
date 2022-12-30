package view;

import controller.RequestHandler;

/**
 * This class generates the servers HTTP response with HTML.
 *
 * @author Alexander Lundqvist & Ramin Shojaei
 */
public class View {
    private final RequestHandler controller;
    
    public View (RequestHandler controller) {
        this.controller = controller;
    }

}
