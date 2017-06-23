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

public class BookDAO {
    
    public static Boolean delete(Integer id)
    {
        Statement stmt;
        Connection connection = ConnectionManager.getConnection();
        
        
        try {            
            String query = "DELETE FROM book WHERE id="+id;
            System.out.println(query);
            stmt = connection.createStatement();        
            stmt.executeUpdate(query);
            
            return true;
        } catch (SQLException ex) {            
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                if (data.get(column).equals("null")) {
                    params.add(column + " = null");
                } else {
                    params.add(column + " = '"+data.get(column)+ "'");
                }
                
                
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
                book.setStatusId(rs.getInt("status_id"));
                list.add(book);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public static ArrayList<Book> getAvailableList(Integer userId) {
        ArrayList<Book> list = new ArrayList<>();
        Statement stmt;
        Connection connection = ConnectionManager.getConnection();
        ResultSet rs;
        try {
            stmt = connection.createStatement();
             
            String query = "SELECT b.id, b.title, b.isbn, b.authors, b.description, b.publisher_id, b.status_id, b.publication_year"
                    + " FROM book AS b "
                    + " LEFT JOIN status AS s ON s.id=b.status_id "
                    + " WHERE 1=1 ";
            
            if (userId != null && userId > 0) {
                query += " AND s.user_id="+userId;
            } else {
                query += " AND b.status_id IS NULL ";
            }
            
            query += " ORDER BY id DESC";
            
            System.out.println(query);
//            
            
            rs = stmt.executeQuery(query);
            
            
            Book book;
            while (rs.next()) {
                book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthors(rs.getString("authors"));
                book.setDescription(rs.getString("description"));
                book.setId(rs.getInt("id"));
                book.setIsbn(rs.getString("isbn"));
                book.setPublicaitonYear(rs.getInt("publication_year"));
                book.setStatusId(rs.getInt("status_id"));
                list.add(book);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
            
    public static ArrayList<Book> getAvailableList() {
        return BookDAO.getAvailableList(null);
    }
            
    private static Book getOne(String query) {
        Statement stmt;
        Connection connection = ConnectionManager.getConnection();
        ResultSet rs;
        try {
            stmt = connection.createStatement();
        
            rs = stmt.executeQuery(query);
            System.out.println(query);
            
            
            if (rs.next()) {
                Book book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthors(rs.getString("authors"));
                book.setDescription(rs.getString("description"));
                book.setId(rs.getInt("id"));
                book.setIsbn(rs.getString("isbn"));
                book.setPublicaitonYear(rs.getInt("publication_year"));
                
                
                Integer statusId = rs.getInt("status_id");
                
                
                Status s = BookDAO.getStatus(statusId);
                System.out.println("STAUTUS");
                System.out.println(s);
                
                book.setStatus(s);
                
                return book;    
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private static Status getStatus(Integer statusId) {
        Status status;
                
        if (statusId != null && statusId > 0) {
            status = StatusDAO.getById(statusId);
        } else {
            status = new Status();
            status.setId(statusId); 
        }
        
        return status;
        
    }
}
