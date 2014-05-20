/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jessie
 */
public class ActionServlet extends HttpServlet {

    private final String WelcomeServlet = "WelcomeServlet";
    private final String SearchServlet = "SearchServlet";
    private final String BookingServlet = "BookingServlet";
    private final String LoginServlet = "LoginServlet";
    private final String LogoutServlet = "LogoutServlet";
    private final String tripServlet = "tripServlet";
    private final String RouteCRUD = "RouteCRUD";
    private final String StationCRUD = "StationCRUD";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "";
        try {
            String action = request.getParameter("btAction");
            if (action.equals("Login")) {
                url = LoginServlet;
            } else if (action.equals("Logout")) {
                url = LogoutServlet;
            } else if (action.equals("Search ticket")) {
                url = SearchServlet;
            } else if (action.equals("Home")) {
                url = WelcomeServlet;
            } else if (action.equals("Book ticket")) {
                url = BookingServlet;
            } else if (action.equals("Manage Trip")) {
                request.setAttribute("action", request.getParameter("action"));
                request.setAttribute("id", request.getParameter("id"));
                url = tripServlet;
            } else if (action.equals("Manage Route")) {
                url = RouteCRUD;
            } else if (action.equals("Manage Station")) {
                url = StationCRUD;
            } else if (action.equals("Update Trip")) {
                /*request.setAttribute("action", "confirmUpdate");
                request.setAttribute("dTime", request.getParameter("dTime"));
                request.setAttribute("tTime", request.getParameter("tTime"));
                request.setAttribute("price", request.getParameter("price"));
                request.setAttribute("tSeat", request.getParameter("tSeat"));
                request.setAttribute("aSeat", request.getParameter("aSeat"));*/
                url = tripServlet;
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            System.out.println("e");
        } catch (Exception e) {
            System.out.println("f");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
