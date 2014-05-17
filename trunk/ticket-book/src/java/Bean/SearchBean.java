/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAL.DAO;
import DAL.Trip;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jessie
 */
public class SearchBean implements Serializable {

    public List<Trip> searchBook(String date, int departure, int terminate) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Trip> trips = new ArrayList<Trip>();
        try {
            conn = DAO.makeConnection();
            String sql = "SELECT r.id, r.departure, r.terminate, t.id, t.availableSeat,"
                    + "t.departTime, t.price, t.terminTime, t.totalSeats"
                    + "FROM [Route] r"
                    + "JOIN Trip t"
                    + "ON r.id = t.routeId"
                    + "WHERE CONVERT(VARCHAR(11),t.departTime,105) = ? AND r.departure = ? AND r.terminate = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            pstmt.setInt(2, departure);
            pstmt.setInt(3, terminate);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String depTime = rs.getString("departTime");
                String terTime = rs.getString("terminTime");
                float price = rs.getFloat("price");
                int totalSeats = rs.getInt("totalSeats");
                int availableSeat = rs.getInt("availableSeat");
                Trip trip = new Trip(terminate, depTime, terTime, price, totalSeats, availableSeat);
                trips.add(trip);
            }
        } catch (Exception e) {
            System.out.println("Error 1: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.out.println("Error 2: " + e.getMessage());
            }
        }
        return trips;
    }
}
