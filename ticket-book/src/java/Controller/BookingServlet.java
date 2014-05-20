/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.SearchBean;
import DAL.DAO;
import DAL.Route;
import DAL.Station;
import DAL.Trip;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author khoatnd
 */
public class BookingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
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
            String action = request.getParameter("action");
            if (action.equals("Book")) {
                int id = Integer.parseInt(request.getParameter("tripID"));
                int availableSeat = Integer.parseInt(request.getParameter("availableSeat"));
                float price = Float.parseFloat(request.getParameter("price"));
                // dua qua trang booking.jsp
                request.setAttribute("tripID", id);
                request.setAttribute("count", availableSeat);
                request.setAttribute("price", price);
                request.getRequestDispatcher("booking.jsp").forward(request, response);
            } else if (action.equals("Booking")) {
                int id = Integer.parseInt(request.getParameter("tripID"));
                int no = Integer.parseInt(request.getParameter("NumOfTicket"));
                float price = Float.parseFloat(request.getParameter("price"));
                String method = request.getParameter("Method");
                String name = request.getParameter("txtName");
                String email = request.getParameter("txtEmail");
                String phone = request.getParameter("txtPhone");
                float total = price * no;
                // insert DB
                Connection conn = DAO.makeConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO Booking (tripId,name,email,phone,numTicket,total,method,isPaid) Values(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setString(3, email);
                stmt.setString(4, phone);
                stmt.setInt(5, no);
                stmt.setFloat(6, total);
                stmt.setString(7, method);
                stmt.setBoolean(8, false);
                stmt.execute();

                ResultSet res = stmt.getGeneratedKeys();
                int orderID = 0;
                while (res.next()) {
                    orderID = res.getInt(1);
                }
                stmt = conn.prepareStatement("Update Trip set availableSeat = availableSeat - ? where id = ? and availableSeat>?");
                stmt.setInt(1, no);
                stmt.setInt(2, id);
                stmt.setInt(3, no);
                stmt.execute();

                // trang confirm.jsp
                stmt = conn.prepareStatement("SELECT * from Trip where id=?");
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int routeId = rs.getInt("routeId");
                    String depTime = rs.getString("departTime");
                    String terTime = rs.getString("terminTime");

                    Trip trip = new Trip();
                    trip.setRouteId(routeId);
                    trip.setDepTime(depTime);
                    trip.setTerTime(terTime);
                    trip.setPrice(price);

                    SearchBean bean = new SearchBean();
                    Station depStation = bean.searchStation(routeId, "departure");
                    Station terStation = bean.searchStation(routeId, "terminate");


                    request.setAttribute("trip", trip);
                    request.setAttribute("routeStart", depStation);
                    request.setAttribute("routeEnd", terStation);

                    request.setAttribute("name", name);
                    request.setAttribute("total", total);
                    request.setAttribute("numTicket", no);
                    request.setAttribute("email", email);
                    request.setAttribute("phone", phone);
                    request.setAttribute("orderID", orderID);

                    conn.close();
                    request.getRequestDispatcher("confirm.jsp").forward(request, response);
                }



            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
