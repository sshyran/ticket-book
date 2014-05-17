/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author khoatnd
 */
public class DAO {

    /// Lay Chuoi Ket Noi CSDL
    public static String getConnectionString() {
        return "jdbc:sqlserver://localhost;user=sa;password=123456;database=TripBooking";
        //return "jdbc:sqlserver://PHUONGDNSE61089\\SQLEXPRESS:53968;databaseName = TripBooking;user = sa; password = 123456";
    }

    //Lấy chuỗi driver
    public static String getDriverString() {
        return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    }

    public static Connection makeConnection() {
        Connection conn = null;
        try {
            Class.forName(getDriverString());
            String url = getConnectionString();
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
