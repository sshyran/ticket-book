/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.ManageRouteBean;
import java.io.IOException;
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
public class RouteCRUD extends HttpServlet {

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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        /*
         *  nhận tham số từ form nhập
         */
        Boolean isShow = true;
        String id = request.getParameter("txtId");
        String departure = request.getParameter("txtDeparture");
        String terminate = request.getParameter("txtTerminate");
        String name = request.getParameter("txtName");

        /*
         * Làm việc với CSDL khi tồn tài các tham số điều khiển tương
         * ứng (btnInsert, btnUpdate, btnDelete, btnSelect, btnSearch)
         */
        try {
            if (request.getParameter("btnInsert") != null) {

                String sql = "INSERT INTO Route (departure, terminate, isShow, name) VALUES(?, ?, ?, (select distinct s1.province + \' - \' + s2.province as name from [Route] r,[Station] s1,[Station] s2 where s1.id=? and s2.id=?))";
                ManageRouteBean.executeUpdate(sql, departure, terminate, isShow, departure, terminate);
                request.setAttribute("errors", "Inserted successfully !");

            } else if (request.getParameter("btnDelete") != null) {
                String sql = "UPDATE Route SET isShow=? WHERE id=?";
                /*
                 * Truy vấn và chia sẻ kết quả trong request
                 */
                ManageRouteBean.executeUpdate(sql, false, id);
                request.setAttribute("errors", "Deleted successfully !");

            } else if (request.getParameter("lnkDelete") != null) {
                String sql = "UPDATE Route SET isShow=? WHERE id=?";
                /*
                 * Truy vấn và chia sẻ kết quả trong request
                 */
                ManageRouteBean.executeUpdate(sql, false, id);
                request.setAttribute("errors", "Deleted successfully !");
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
        String searchSql = "SELECT * FROM Route  WHERE name LIKE ? AND isShow = '1'";
        Result rsSearch = ManageRouteBean.toResult(searchSql, "%" + search + "%");
        request.setAttribute("rts", rsSearch);
        /*
         * Chuyển tiếp sang trang routeAdmin.jsp
         */
        request.getRequestDispatcher("routeAdmin.jsp").forward(request, response);

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
