package PkProject.Controller;

import PkProject.DAO.BookDAO;
import java.util.HashMap;


public class BooksController extends AbstractController {

    protected Boolean needToBeLoggedIn = true;
    HashMap<String, Object> params = new HashMap<>();
    
    @Override
    public void doGET() {        
        HashMap<String, Object> params = new HashMap<>();
        
        params.put("books", BookDAO.getAvailableList());
        
        this.render("Book:list", params);
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
