/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAL.AdminAccount;
import DAL.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bc
 */
public class LogInBean {

    public AdminAccount LogIn(String uname, String pass) {
        Connection c = DAO.makeConnection();
        try {
            PreparedStatement prepare = c.prepareStatement("Select * from AdminAccount where uname=? and upass=?");
            prepare.setString(1, uname);
            prepare.setString(2, pass);
            ResultSet rs = prepare.executeQuery();
            if (rs.next()) {
                AdminAccount admin = new AdminAccount();
                admin.setUsername(rs.getString("uname"));
                admin.setPassword(rs.getString("upass"));
                admin.setFullName(rs.getString("name"));
                return admin;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
