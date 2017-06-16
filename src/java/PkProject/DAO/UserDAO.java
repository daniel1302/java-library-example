package PkProject.DAO;

import PkProject.Entity.UserEntity;
import PkProject.Model.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    public static UserEntity login(UserEntity user) {
        Statement stmt;
        Connection connection = ConnectionManager.getConnection();
        ResultSet rs;
        user.setValid(false);
        
        String query = "SELECT * FROM user WHERE "
                + " username='"+user.getUsername()+"'"
                + " AND pass='"+user.getPassword()+"'";
        
        try {
            stmt = connection.createStatement();
        
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                user.setEmail(rs.getString("email"));
                user.setFirstname(rs.getString("firstname"));
                user.setSurname(rs.getString("surname"));
                user.setId(rs.getLong("id"));
                user.setPassword(rs.getString("pass"));
                user.setUsername(rs.getString("username"));
                user.setValid(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
         
            return user;
        }
        
        return user;
    }
}
