/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAL.DAO;
import javax.sql.DataSource;
import DAL.Trip;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author bc
 */
public class TripBean {

    public boolean updateTrip(String id, String newDTime, String newTTime, float newPrice, int newTotalSeat, int newAvailableSeat) {
        Connection c = DAO.makeConnection();
        Statement stmt;
        try {
            stmt = c.createStatement();
            String querry = "update Trip set departTime='"
                    + newDTime + "',terminTime='" + newTTime
                    + "',price=" + newPrice + ",totalSeats="
                    + newTotalSeat + ",availableSeat="
                    + newAvailableSeat + " where id=" + id;
            stmt.executeUpdate(querry);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteTrip(String id) {
        Connection c = DAO.makeConnection();
        Statement stmt;
        try {
            stmt = c.createStatement();
            String querry = "update Trip set isShow=0 where id=" + id;
            stmt.executeUpdate(querry);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Trip getTrip(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Trip trip = null;
        try {
            conn = DAO.makeConnection();
            stmt = conn.prepareStatement("SELECT * from Trip where id=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int routeId = rs.getInt("routeId");
                String depTime = rs.getString("departTime");
                String terTime = rs.getString("terminTime");
                trip = new Trip(routeId, depTime, terTime);
            }
        } catch (Exception e) {
        }
        return trip;
    }
}
