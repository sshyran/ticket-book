/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAL.DAO;
import DAL.Station;
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
            String sql = "SELECT r.id, r.departure, r.terminate, t.id, t.availableSeat, "
                    + "t.departTime, t.price, t.terminTime, t.totalSeats "
                    + "FROM [Route] r "
                    + "JOIN Trip t "
                    + "ON r.id = t.routeId "
                    + "WHERE CONVERT(VARCHAR(11),t.departTime,105) LIKE ? AND r.departure = ? AND r.terminate = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + date + "%");
            pstmt.setInt(2, departure);
            pstmt.setInt(3, terminate);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int routeId = rs.getInt("id");
                int id = rs.getInt("id");
                String depTime = rs.getString("departTime");
                String terTime = rs.getString("terminTime");
                float price = rs.getFloat("price");
                int totalSeats = rs.getInt("totalSeats");
                int availableSeat = rs.getInt("availableSeat");
                Trip trip = new Trip(routeId, depTime, terTime, price, totalSeats, availableSeat, id);
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

    public Station searchStation(int placeID, String typeS) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Station station = null;
        try {
            conn = DAO.makeConnection();
            String sql = "";
            if (typeS.equals("departure")) {
                sql = "SELECT r.id, r.departure, s.province, s.sname, s.[address] "
                        + "FROM [Route] r "
                        + "JOIN Station s "
                        + "ON s.id = r.departure "
                        + "WHERE r.id=? ";
            } else if (typeS.equals("terminate")) {
                sql = "SELECT r.id, r.departure, s.province, s.sname, s.[address] "
                        + "FROM [Route] r "
                        + "JOIN Station s "
                        + "ON s.id = r.terminate "
                        + "WHERE r.id=? ";
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, placeID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String sname = rs.getString("sname");
                String address = rs.getString("address");
                String province = rs.getString("province");
                station = new Station(sname, address, province);
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
        return station;
    }

    public Trip searchTrip(String id) {
        Connection c = DAO.makeConnection();
        try {
            PreparedStatement prepare = c.prepareStatement("select t.id, t.departTime, t.terminTime, t.price, t.totalSeats, t.availableSeat, t.routeId from Trip t where t.id=?");
            prepare.setString(1, id);
            ResultSet rs = prepare.executeQuery();
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
                        return trip;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
