/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
                try {
                    // <editor-fold defaultstate="collapsed" desc="delete trip">
                    cn = DriverManager.getConnection(new DAO().getConnectionString());
                    stmt = cn.createStatement();
                    query = "update Trip set isShow=0 where id=" + id;
                    stmt.executeUpdate(query);
                    // </editor-fold>
                } catch (SQLException ex) {
                    Logger.getLogger(tripServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect("CollectTrip");
            }
            if (action.equals("update")) {
                // <editor-fold defaultstate="collapsed" desc="to updateTrip.jsp">
                try {
                    cn = DriverManager.getConnection(new DAO().getConnectionString());
                    stmt = cn.createStatement();
                    //query = "select t.id, t.departTime, t.terminTime, t.price, t.totalSeats, t.availableSeat, r.name from Trip t, [Route] r where t.routeId=r.id and t.id=" + id;
                    query = "select t.id, t.departTime, t.terminTime, t.price, t.totalSeats, t.availableSeat, t.routeId from Trip t where t.id=" + id;
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        int getId = rs.getInt("id");
                        String dTime = rs.getDate("departTime").toString() + " " + rs.getTime("departTime").toString();
                        String tTime = rs.getDate("terminTime").toString() + " " + rs.getTime("terminTime").toString();
                        float price = rs.getFloat("price");
                        int tSeat = rs.getInt("totalSeats");
                        int aSeat = rs.getInt("availableSeat");
                        //String routeName = rs.getString("name");
                        int routeId = rs.getInt("routeId");
                        Trip trip = new Trip();
                        trip.setId(getId);
                        trip.setDepTime(dTime);
                        trip.setTerTime(tTime);
                        trip.setPrice(price);
                        trip.setTotalSeat(tSeat);
                        trip.setAvailableSeat(aSeat);
                        //trip.setRouteName(routeName);
                        trip.setRouteId(routeId);
                        request.setAttribute("updateTrip", trip);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(tripServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                    //String newRoute = request.getParameter("routeName").replaceAll("^(\\d)*\\ \\|\\ ", "");
                    //int newRouteID = Integer.parseInt(request.getParameter("routeName").replaceAll("\\ \\|\\ (.)*$", ""));
                    int newRouteID = Integer.parseInt(request.getParameter("routeId"));
                    //out.print("newRouteID = " + newRouteID + "<br/>");
                    // <editor-fold defaultstate="collapsed" desc="get new route id">
//                    cn = DriverManager.getConnection(new DAO().getConnectionString());
//                    stmt = cn.createStatement();
//                    //out.print("|" + newRoute + "|<br/>");
//                    //query = "select id from [Route] where name=N'" + newRoute + "'";
//                    query = "select id,name from [Route]";
//                    out.print("|" + query + "|<br/>");
//                    rs = stmt.executeQuery(query);
//
//                    while (rs.next()) {
//
//                        out.print(newRoute + "<br/>" + rs.getString("name"));
//                        out.print("<br/>");
//                        out.print(new String(newRoute.getBytes(), "UTF-8").equals(rs.getString("name")));
//                        out.print(new String(newRoute.getBytes(), "UTF-16").equals(rs.getString("name")));
//                        out.print(new String(newRoute.getBytes(), "ISO-8859-1").equals(rs.getString("name")));
//                        out.print(new String(newRoute.getBytes(), "ISO-8859-1"));
//
//                        out.print("<br/>");
//                        out.print("<br/>");
//                    }
//                    out.print(newRouteID);
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="update trip">
                    cn = DriverManager.getConnection(new DAO().getConnectionString());
                    stmt = cn.createStatement();
                    //out.print(id);
                    query = "update Trip set departTime='" + newDTime + "',terminTime='" + newTTime + "',price=" + newPrice + ",totalSeats=" + newTotalSeat + ",availableSeat=" + newAvailableSeat + ",routeId=" + newRouteID + " where id=" + id;
                    //out.print(query);
                    //query = "update Trip set departTime='" + newDTime + "',";
                    stmt.executeUpdate(query);
                    response.sendRedirect("CollectTrip");
                    // </editor-fold>
                    out.print("OK");
                } catch (Exception e) {
                    Logger.getLogger(tripServlet.class.getName()).log(Level.SEVERE, null, e);
                    out.print("139 " + " " + e);
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
