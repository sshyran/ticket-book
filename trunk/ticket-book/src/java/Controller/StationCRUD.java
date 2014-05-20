/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.ManageRouteBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;

/**
 *
 * @author Dell
 */
public class StationCRUD extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        /*
         *  nhận tham số từ form nhập
         */
        String id = request.getParameter("txtId");
        String sname = request.getParameter("txtSName");
        String address = request.getParameter("txtAddress");
        String province = request.getParameter("txtProvince");

        /*
         * Làm việc với CSDL khi tồn tài các tham số điều khiển tương
         * ứng (btnInsert, btnUpdate, btnDelete, btnSelect, btnSearch)
         */
        try {
            if ((request.getParameter("btnInsert") != null) /* && (request.getParameter("btnInsert") != "") */ ) {
                String sql = "INSERT INTO Station(sname, address, province) VALUES(?, ?, ?)";
                ManageRouteBean.executeUpdate(sql, sname, address, province);
                request.setAttribute("errors", "Inserted successfully !");
            } else if (request.getParameter("btnUpdate") != null) {
                String sql = "UPDATE Station SET sname=?, address=?, province=? WHERE Id=?";
                ManageRouteBean.executeUpdate(sql, sname, address, province, id);
                request.setAttribute("errors", "Updated successfully !");
            } else if (request.getParameter("lnkDelete") != null) {
                String sql = "DELETE FROM Station WHERE Id=?";
                ManageRouteBean.executeUpdate(sql, id);
                request.setAttribute("errors", "Deleted successfully !");
            } else if (request.getParameter("lnkEdit") != null) {
                String sql = "SELECT * FROM Station WHERE Id=?";
                /*
                 * Truy vấn và chia sẻ kết quả trong request
                 */
                Result result = ManageRouteBean.toResult(sql, id);
                @SuppressWarnings("rawtypes")
                SortedMap station = result.getRows()[0];
                request.setAttribute("st", station);
                request.setAttribute("errors", "Loaded successfully !");
            }
        } catch (Exception e) {
            request.setAttribute("errors", "Data manipilation error !");
        }
        /*
         * Duy trì chuỗi tìm kiếm của câu lệnh sql trong session
         */
        HttpSession session = request.getSession();
        if (request.getParameter("btnSearch") != null) {
            session.setAttribute("search", request.getParameter("txtSearch"));
        } else if (session.getAttribute("search") == null) {
            session.setAttribute("search", "");
        }
        String search = (String) session.getAttribute("search");

        /*
         * Tìm kiếm theo tên và chia sẽ kết quả trong request
         */
        String searchSql = "SELECT * FROM Station  WHERE sname LIKE ?";
        Result stSearch = ManageRouteBean.toResult(searchSql, "%" + search + "%");
        request.setAttribute("sts", stSearch);
        /*
         * Chuyển tiếp sang trang stationAdmin.jsp
         */
        request.getRequestDispatcher("stationAdmin.jsp").forward(request, response);
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
