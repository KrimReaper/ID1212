package controller;

import Integration.DBhandler;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserBean;

/**
 * This servlet acts like an overarching controller for the webapplication. Since
 * the project is so small it is not necessary to have a controller for each 
 * logic unit.
 * 
 * @author Alexander Lundqvist & Ramin Shojaei
 */
@WebServlet(name = "Controller", urlPatterns = {"/quiz"})
public class ControlServlet extends HttpServlet {
    private DBhandler database;
    
    @Override
    public void init() {
    	database = new DBhandler();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        
        UserBean user = (UserBean) session.getAttribute("UserBean");
        String username = request.getParameter("Username");
        String password = request.getParameter("Password");
        
        // New user!
        if (user == null) {
            user = new UserBean();
            session.setAttribute("UserBean", user);
            response.sendRedirect("login.jsp");
        }
        
        // Logout
        if (session.getAttribute("LogOut").equals("true")) {
            session.setAttribute("UserBean", null);
            response.sendRedirect("login.jsp");
        }
        
        // Login
        // Replace by database access
        if (username.equals("Alexlu@kth.se") && password.equals("Alexlu")) {
            session.setAttribute("UserBean", user);           
            response.sendRedirect("home.jsp");
        }
        else {
            session.setAttribute("SessionStatus", "Error");
            response.sendRedirect("login.jsp");
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
