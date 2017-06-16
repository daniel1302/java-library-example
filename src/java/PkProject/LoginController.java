package PkProject;

import PkProject.DAO.UserDAO;
import PkProject.Entity.UserEntity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author daniel
 */
public class LoginController extends AbstractController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
             {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            UserEntity user = new UserEntity();
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
//            
//            
            if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
                user.setPassword(request.getParameter("password"));
                user.setUsername(request.getParameter("username"));
                
                user = UserDAO.login(user);
                
                if (user.isValid()) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    response.sendRedirect("profile.jsp");
                    return;
                }
                
                
            }
//            
            
            
            
            
            response.sendRedirect("login.jsp");
            return;
            
        } catch (Throwable ex) {
            System.out.print(ex);
        }
    }
}
