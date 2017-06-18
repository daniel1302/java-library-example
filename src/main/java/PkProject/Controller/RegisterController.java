package PkProject.Controller;


public class RegisterController extends AbstractController {
    @Override
    public void doPOST() {
        String login        = request.getParameter("firstname");
        String pass         = request.getParameter("pass");
        String pass2        = request.getParameter("pass2");
        String email        = request.getParameter("email");
        String firstname    = request.getParameter("firstname");
        String surname      = request.getParameter("surname");
        
        if (login != null && pass != null && pass2 != null && email != null && firstname != null && surname != null) {
            
        }
        
        
        this.render("Register:index");
    }
    
    
    @Override
    public void doGET() {
   
        this.render("Register:index");
      
    }
}
