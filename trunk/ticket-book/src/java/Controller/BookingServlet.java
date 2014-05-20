/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.BookBean;
import Bean.SearchBean;
import Bean.TripBean;
import DAL.Station;
import DAL.Trip;

import java.io.IOException;
import java.io.PrintWriter;
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

                boolean isPaid = false;
                if (method.equals("Bank")) {
                    isPaid = true;
                }
                // call bean
                BookBean bookBean = new BookBean();
                bookBean.insertBooking(id, name, email, phone, no, total, method, isPaid);
                bookBean.updateTrip(no, id);
                TripBean tripBean = new TripBean();
                Trip trip = tripBean.getTrip(id);
                trip.setPrice(price);
                SearchBean bean = new SearchBean();
                Station depStation = bean.searchStation(trip.getRouteId(), "departure");
                Station terStation = bean.searchStation(trip.getRouteId(), "terminate");

                request.setAttribute("trip", trip);
                request.setAttribute("routeStart", depStation);
                request.setAttribute("routeEnd", terStation);

                request.setAttribute("name", name);
                request.setAttribute("total", total);
                request.setAttribute("numTicket", no);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("isPaid", isPaid);
                request.getRequestDispatcher("confirm.jsp").forward(request, response);
            }
        } catch (Exception ex) {
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
