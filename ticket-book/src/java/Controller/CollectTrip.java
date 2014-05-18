/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAL.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bc
 */
public class CollectTrip extends HttpServlet {

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
        String query = "select t.id,t.departTime,t.terminTime,t.price,t.totalSeats,t.availableSeat,t.routeId,s1.sname as [From],s2.sname as [To] from [Route] r,Station s1,Station s2, Trip t where t.routeId=r.id and r.departure=s1.id and r.terminate=s2.id and t.isShow=1";
        Statement stmt;
        ResultSet rs = null;
        ArrayList<Trip> array = new ArrayList<Trip>();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CollectTrip</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CollectTrip at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */

            Connection cn = DriverManager.getConnection(new DAO().getConnectionString());
            stmt = cn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String dTime = rs.getDate("departTime").toString() + " " + rs.getTime("departTime").toString();
                String tTime = rs.getDate("terminTime").toString() + " " + rs.getTime("terminTime").toString();
                float price = rs.getFloat("price");
                int tSeat = rs.getInt("totalSeats");
                int aSeat = rs.getInt("availableSeat");
                int rId = rs.getInt("routeId");
                String dep = rs.getString("From");
                String tem = rs.getString("To");
                Trip trip = new Trip(id, dTime, tTime, price, tSeat, aSeat, rId, dep, tem);
                array.add(trip);
            }
            request.setAttribute("tripList", array);
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CollectTrip.class.getName()).log(Level.SEVERE, null, ex);
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
