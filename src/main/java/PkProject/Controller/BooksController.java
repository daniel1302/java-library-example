package PkProject.Controller;

import PkProject.DAO.BookDAO;
import java.util.HashMap;


public class BooksController extends AbstractController {

    protected Boolean needToBeLoggedIn = true;
    HashMap<String, Object> params = new HashMap<>();
    
    @Override
    public void doGET() {        
        HashMap<String, Object> params = new HashMap<>();
        
        String page = this.request.getParameter("page");
        System.out.println(page);
        if (null == page) {
            params.put("books", BookDAO.getAvailableList());
        } else switch (page) {
            case "my-books":
                params.put("books", BookDAO.getAvailableList(getUserSession().getId().intValue()));
                break;            
            default:
                params.put("books", BookDAO.getAvailableList());
                break;
        }
        
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
