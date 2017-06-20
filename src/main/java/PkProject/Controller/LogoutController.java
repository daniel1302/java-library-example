package PkProject.Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author daniel
 */
public class LogoutController extends AbstractController {
    @Override
    public void doPOST() {}
    
    
    @Override
    public void doGET() {
        
        this.response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = this.request.getSession(true);
        
        if (session != null) {
            session.invalidate();
        }
        
        try {
            this.response.sendRedirect("login");
        } catch (IOException ex) {
            Logger.getLogger(LogoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
