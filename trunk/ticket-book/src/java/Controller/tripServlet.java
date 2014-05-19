/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.SearchBean;
import Bean.TripBean;
import DAL.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAL.Trip;

/**
 *
 * @author bc
 */
public class tripServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Connection cn;
            Statement stmt;
            String id = request.getParameter("id");
            String action = request.getParameter("action");
            String query;
            ResultSet rs = null;
            if (action.equals("delete")) {
                // <editor-fold defaultstate="collapsed" desc="delete trip">
                TripBean tb = new TripBean();
                tb.deleteTrip(id);
                // </editor-fold>
                response.sendRedirect("CollectTrip");
            }
            if (action.equals("update")) {
                // <editor-fold defaultstate="collapsed" desc="to updateTrip.jsp">
                SearchBean sb = new SearchBean();
                Trip t = sb.searchTrip(id);
                request.setAttribute("updateTrip", t);
                RequestDispatcher rd = request.getRequestDispatcher("updateTrip.jsp");
                rd.forward(request, response);
                // </editor-fold>
            }
            if (action.equals("confirmUpdate")) {
                // <editor-fold defaultstate="collapsed" desc="really update a trip">
                try {
                    String newDTime = request.getParameter("dTime");
                    String newTTime = request.getParameter("tTime");
                    float newPrice = Float.parseFloat(request.getParameter("price"));
                    int newTotalSeat = Integer.parseInt(request.getParameter("tSeat"));
                    int newAvailableSeat = Integer.parseInt(request.getParameter("aSeat"));
                    TripBean tb = new TripBean();
                    tb.updateTrip(id, newDTime, newTTime, newPrice, newTotalSeat, newAvailableSeat);
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    response.sendRedirect("CollectTrip");
                }
                // </editor-fold>
            }
            if (action.equals("add")) {

                try {
                    String newDTime = request.getParameter("dTime");
                    String newTTime = request.getParameter("tTime");
                    float newPrice = Float.parseFloat(request.getParameter("price"));
                    int newTotalSeat = Integer.parseInt(request.getParameter("tSeat"));
                    int newAvailableSeat = Integer.parseInt(request.getParameter("aSeat"));
                    int newRouteID = Integer.parseInt(request.getParameter("routeId"));
                    cn = DriverManager.getConnection(new DAO().getConnectionString());
                    stmt = cn.createStatement();
                    query = "insert into Trip(departTime,terminTime,price,totalSeats,availableSeat,routeId,isShow) values ("
                            + "'" + newDTime + "','" + newTTime + "'," + newPrice + ","
                            + newTotalSeat + "," + newAvailableSeat + "," + newRouteID + ",1);";
                    stmt.executeUpdate(query);
                } catch (Exception e) {
                }
                response.sendRedirect("CollectTrip");
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
