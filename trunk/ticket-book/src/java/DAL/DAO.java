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
        return "jdbc:sqlserver://localhost;user=sa;password=123;database=TripBooking";
    }
}
