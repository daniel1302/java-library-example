package PkProject.Controller;

import PkProject.DAO.UserDAO;
import PkProject.Entity.User;
import javax.servlet.http.HttpSession;

/**
 *
 * @author daniel
 */
public class LoginController extends AbstractController {
    @Override
    public void doPOST() {}
    
    
    @Override
    public void doGET() {
        
        this.response.setContentType("text/html;charset=UTF-8");
        
        try {
            User user = new User();
            
            String username = this.request.getParameter("username");
            String password = this.request.getParameter("password");
            
            
            if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
                user.setPassword(this.request.getParameter("password"));
                user.setUsername(this.request.getParameter("username"));
                
                user = UserDAO.login(user);
                
                if (user.isValid()) {
                    HttpSession session = this.request.getSession(true);
                    session.setAttribute("user", user);
                    
                    this.response.sendRedirect("profile");
                    return;
                }   
            }
            
            
            this.render("Login:index");
        } catch (Throwable ex) {
            System.out.print(ex);
        }
    }
}
