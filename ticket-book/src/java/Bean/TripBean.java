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
}
