package PkProject.Controller;

import PkProject.DAO.BookDAO;
import PkProject.DAO.StatusDAO;
import PkProject.Entity.Book;
import PkProject.Entity.Status;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GetBookController extends AbstractController {

    protected Boolean needToBeLoggedIn = true;
    HashMap<String, Object> params = new HashMap<>();
    
    @Override
    public void doGET() {        
        HashMap<String, Object> params = new HashMap<>();
        
        String id = this.request.getParameter("id");
        if (id != null && id.matches("\\d+")) {
            Book book = BookDAO.getById(Integer.parseInt(id));
            
            System.out.println(book.getStatus());
            
            if (book == null || (int)book.getStatus().getType() != Status.TYPE_FREE) {
                params.put("warning", "Książka nie jest dostępna");
            } else {
                
                HashMap<String, String> data = new HashMap<>();
                data.put("user_id", String.valueOf(getUserSession().getId()));
                data.put("type", String.valueOf(Status.TYPE_RESERVED));
                Integer statusId = StatusDAO.insert(data);
                
                data = new HashMap<>();
                data.put("status_id", String.valueOf(statusId));
                
                BookDAO.update(book.getId(), data);
                
                params.put("message", "Książka wypożyczona");
            }
        } else {
            try {
                this.response.sendRedirect("books");
            } catch (IOException ex) {
                Logger.getLogger(GetBookController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        
        this.render("Book:get", params);
    }

    @Override
    public void doPOST() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected Boolean onlyForLoggedIn() {
        return true;
    }
}
