/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.SearchBean;
import DAL.Station;
import DAL.Trip;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jessie
 */
public class SearchServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String departDate = request.getParameter("txtDate");
        String depatureS = request.getParameter("txtDeparture");
        String terminateS = request.getParameter("txtTerminate");
        int departID = Integer.parseInt(depatureS);
        int terminID = Integer.parseInt(terminateS);
        SearchBean bean = new SearchBean();
        List<Trip> trips = bean.searchBook(departDate, departID, terminID);
        if (trips.size() > 0) {
            int routeID = trips.get(0).getRouteId();
            Station depStation = bean.searchStation(routeID, "departure");
            Station terStation = bean.searchStation(routeID, "terminate");
            request.setAttribute("trips", trips);
            request.setAttribute("depS", depStation);
            request.setAttribute("terS", terStation);
        } else {
            request.setAttribute("check", "check");
        }
        String url = "afterSearching.jsp?txtDeparture=" + depatureS + "&txtTerminate=" + terminateS;
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
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
