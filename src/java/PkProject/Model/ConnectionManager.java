package PkProject.Model;


import java.sql.*;
import java.util.*;

/**
 *
 * @author daniel
 */
public class ConnectionManager {
    static Connection connection = null;
    static String url = null;
    static String DBUser = "root";
    static String DBPass = "Daniel123";
    static String DBUrl  = "jdbc:mysql://localhost:3306/";
    static String DBName = "lib";
    
    
    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        
        try { 
            String url = DBUrl+DBName;


            // assuming "DataSource" is your DataSource name 
                Class.forName("com.mysql.jdbc.Driver"); 
            try { 
                connection = DriverManager.getConnection(url, DBUser, DBPass);
            } catch (SQLException ex) { 
                ex.printStackTrace(); 
            } 
        } catch(ClassNotFoundException e) { 
            System.out.println(e); 
        } 

        return connection; 
    }
}
