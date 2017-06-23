package PkProject.DAO;

import PkProject.Entity.Book;
import PkProject.Entity.Status;
import PkProject.Model.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StatusDAO {
    
    public static Boolean delete(Integer id)
    {
        Statement stmt;
        Connection connection = ConnectionManager.getConnection();
        
        
        try {            
            String query = "DELETE FROM status WHERE id="+id;
            System.out.println(query);
            stmt = connection.createStatement();        
            stmt.executeUpdate(query);
            
            return true;
        } catch (SQLException ex) {            
            Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static Boolean update(Integer id, HashMap<String, String> data) {
        if (data.isEmpty()) {
            return true;
        }
        
        Statement stmt;
        Connection connection = ConnectionManager.getConnection();
        
        
        try {
            List<String> params = new ArrayList<>();
            
            for (String column : data.keySet()) {
                params.add(column + " = '"+data.get(column)+ "'");
            }
            String joinedParams = params.stream().collect(Collectors.joining(", "));
            
            
            String query = "UPDATE status SET "+ joinedParams + " WHERE id="+id;
            System.out.println(query);
            
            stmt = connection.createStatement();        
            stmt.executeUpdate(query);
            
            return true;
        } catch (SQLException ex) {            
            Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
        
    public static Status getById(Integer id) {
        String query = "SELECT * FROM status WHERE id="+id;
        
        return StatusDAO.getOne(query);
    }
    
    public static Integer insert(HashMap<String, String> data) {
        
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        
        for (String x : data.keySet()) {
            keys.add(x);
            values.add("'"+data.get(x)+"'");
        }
        String joinedKeys = keys.stream().collect(Collectors.joining(", "));
        String joinedValues = values.stream().collect(Collectors.joining(", "));
        
        
        String query = "INSERT INTO status("+joinedKeys+") VALUES("+joinedValues+")";
        
        Statement stmt;
        Connection connection = ConnectionManager.getConnection();
        
        System.out.println(query);
        try {
            stmt = connection.createStatement();
        
            Integer bookId = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            return bookId;
        } catch (SQLException ex) {
            
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
            
    private static Status getOne(String query) {
        Statement stmt;
        Connection connection = ConnectionManager.getConnection();
        ResultSet rs;
        try {
            stmt = connection.createStatement();
        
            rs = stmt.executeQuery(query);
            System.out.println(query);
            
            
            if (rs.next()) {
                Status status = new Status();
                
                status.setId(rs.getInt("id"));
                status.setType(rs.getInt("type"));
                status.setUserId(rs.getInt("user_id"));
                
                return status;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
