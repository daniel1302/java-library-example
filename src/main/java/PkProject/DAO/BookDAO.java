package PkProject.DAO;

import PkProject.Entity.Book;
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

public class BookDAO {
    
    
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
            
            
            String query = "UPDATE book SET "+ joinedParams + " WHERE id="+id;
            System.out.println(query);
            
            stmt = connection.createStatement();        
            stmt.executeUpdate(query);
            
            return true;
        } catch (SQLException ex) {            
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
        
    public static Book getById(Integer id) {
        String query = "SELECT * FROM book WHERE id="+id;
        
        return BookDAO.getOne(query);
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
        
        
        String query = "INSERT INTO book("+joinedKeys+") VALUES("+joinedValues+")";
        
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
    
    public static ArrayList<Book> getList() {
        ArrayList<Book> list = new ArrayList<>();
        Statement stmt;
        Connection connection = ConnectionManager.getConnection();
        ResultSet rs;
        try {
            stmt = connection.createStatement();
        
            rs = stmt.executeQuery("SELECT * FROM book ORDER BY id DESC");
            Book book;
            while (rs.next()) {
                book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthors(rs.getString("authors"));
                book.setDescription(rs.getString("description"));
                book.setId(rs.getInt("id"));
                book.setIsbn(rs.getString("isbn"));
                book.setPublicaitonYear(rs.getInt("publication_year"));
                                
                list.add(book);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    
    private static Book getOne(String query) {
        Statement stmt;
        Connection connection = ConnectionManager.getConnection();
        ResultSet rs;
        try {
            stmt = connection.createStatement();
        
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                Book book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthors(rs.getString("authors"));
                book.setDescription(rs.getString("description"));
                book.setId(rs.getInt("id"));
                book.setIsbn(rs.getString("isbn"));
                book.setPublicaitonYear(rs.getInt("publication_year"));
                                
                return book;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
