package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.GuessBean;

/**
 * Servlet that acts as a controller for the application. Handles incoming 
 * HTTP GET requests and creates the HTTP response.
 *
 * @author Alexander Lundqvist & Ramin Shojaei
 */
@WebServlet(name = "HTTPHandler", urlPatterns = {"/HTTPHandler"})
public class HTTPHandler extends HttpServlet {
    
    /**
     * Processes requests for HTTP <code>GET</code> method.
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
        
        GuessBean bean = new GuessBean();
        
        if(!session.isNew()) {
            bean = (GuessBean) session.getAttribute("GuessBean");
        }
        
        String guess = request.getParameter("guess");
        String answer = bean.handleGuess(guess);
        int amount = bean.getAmountOfGuesses();
        session.setAttribute("Answer", answer);
        session.setAttribute("AmountOfGuesses", amount);
        session.setAttribute("GuessBean", bean);
        
	response.sendRedirect("Quiz.jsp");
    }

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

}
