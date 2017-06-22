package PkProject.Controller;

import PkProject.DAO.UserDAO;
import java.util.HashMap;
import PkProject.Entity.User;


public class AdminUserController extends AbstractController {
    
    protected Boolean needToBeLoggedIn = true;
    HashMap<String, Object> params = new HashMap<>();
    @Override
    public void doGET() {
        String userId = this.request.getParameter("toggle-status");
        
        
        params.put("message", null);
        if (userId != null && userId.matches("\\d+")) {
            Integer intUserId = Integer.parseInt(userId);
            User u = UserDAO.getUser(intUserId);
            if (u != null) {            
                HashMap<String, String> uParams = new HashMap<>();
                if (u.getVerified() == 0) {
                    uParams.put("verified", "1");
                } else {
                    uParams.put("verified", "0");
                }
                
                UserDAO.update(intUserId, uParams);
                params.put("message", "Status konta został zmieniony");
            }
        } 
        


        
        params.put("users", UserDAO.getList());        
        this.render("AdminUser:index", params);
    }

    @Override
    public void doPOST() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected Boolean onlyForLoggedIn() {
        return true;
    }
    
    @Override
    protected Boolean onlyForAdmin() {
        return true;
    }
}
