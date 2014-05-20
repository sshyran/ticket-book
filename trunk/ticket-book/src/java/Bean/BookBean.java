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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jessie
 */
public class BookBean implements Serializable {

    public int insertBooking(int id, String name, String email, String phone,
            int no, float total, String method, boolean isPaid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int orderID = 0;
        try {
            conn = DAO.makeConnection();
            stmt = conn.prepareStatement("INSERT INTO Booking "
                    + "(tripId,name,email,phone,numTicket,total,method,isPaid) "
                    + "Values(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setInt(5, no);
            stmt.setFloat(6, total);
            stmt.setString(7, method);
            stmt.setBoolean(8, isPaid);
            stmt.execute();
            rs = stmt.getGeneratedKeys();

            while (rs.next()) {
                orderID = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error 1: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.out.println("Error 2: " + e.getMessage());
            }
        }
        return orderID;
    }

    public int updateTrip(int no, int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int orderID = 0;
        try {
            conn = DAO.makeConnection();
            stmt = conn.prepareStatement("Update Trip set availableSeat = "
                    + "availableSeat - ? where id = ? and availableSeat>?");
            stmt.setInt(1, no);
            stmt.setInt(2, id);
            stmt.setInt(3, no);
            stmt.execute();
            rs = stmt.getGeneratedKeys();

            while (rs.next()) {
                orderID = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error 1: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                System.out.println("Error 2: " + e.getMessage());
            }
        }
        return orderID;
    }
    
    
}
