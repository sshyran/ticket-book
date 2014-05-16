/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

/**
 *
 * @author khoatnd
 */
public class DAO {

    /// Lay Chuoi Ket Noi CSDL
    public static String getConnectionString() {
        //return "jdbc:sqlserver://localhost;user=sa;password=123456;database=TripBooking";
        return "jdbc:sqlserver://PHUONGDNSE61089\\SQLEXPRESS:53968;databaseName = TripBooking;user = sa; password = 123456";
    }

    //Lấy chuỗi driver
    public static String getDriverString() {
        return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    }
}
