package PkProject.Controller;

import PkProject.DAO.BookDAO;
import java.util.HashMap;


public class BookController extends AbstractController {

    protected Boolean needToBeLoggedIn = true;
    HashMap<String, Object> params = new HashMap<>();
    @Override
    public void doGET() {        
        HashMap<String, Object> params = new HashMap<>();
        
        String deleteBookId = this.request.getParameter("delete");
        
        if (deleteBookId != null && deleteBookId.matches("\\d+")) {
            Integer bookId = Integer.parseInt(deleteBookId);
            BookDAO.delete(bookId);
            params.put("warning", "Ksiażka została usunieta");
        }
        
        String freekId = this.request.getParameter("free");
        if (freekId != null && freekId.matches("\\d+")) {
            Integer fbookId = Integer.parseInt(freekId);
            HashMap<String, String> data = new HashMap<>();
            data.put("status_id", "null");
            
            BookDAO.update(fbookId, data);
            params.put("warning", "Ksiażka została uwolniona");
        }
        
        
        params.put("books", BookDAO.getList());
        
        this.render("Book:index", params);
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
