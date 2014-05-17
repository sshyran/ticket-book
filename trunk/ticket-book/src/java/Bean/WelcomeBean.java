/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAL.DAO;
import DAL.Station;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Jessie
 */
public class WelcomeBean implements Serializable {

    public List<Station> showForSearch() {
        List<Station> stations = null;
        try {
            java.util.Properties props = new Properties();
            String url = DAO.getConnectionString();
            url = props.getProperty(url);
            String driver = DAO.getDriverString();
            driver = props.getProperty(driver);
            Class.forName(driver);
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                conn = DriverManager.getConnection(url);
                pstmt = conn.prepareStatement("SELECT * FROM Station");
                rs = pstmt.executeQuery();
                stations = new ArrayList<Station>();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String address = rs.getString("address");
                    String province = rs.getString("province");
                    String sname = rs.getString("sname");
                    Station station = new Station(id, sname, address, province);
                    stations.add(station);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                    if (pstmt != null) {
                        pstmt.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stations;
    }
}
