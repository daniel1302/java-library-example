package PkProject.Controller;

import PkProject.DAO.UserDAO;
import PkProject.Entity.User;
import PkProject.Model.Validator.EmailValidator;
import PkProject.Model.Validator.EmptyValidator;
import PkProject.Model.Validator.ValidatorChain;
import java.util.ArrayList;
import java.util.HashMap;


public class RegisterController extends AbstractController {
    @Override
    public void doPOST() {
        String login        = this.request.getParameter("username");
        String pass         = this.request.getParameter("pass");
        String pass2        = this.request.getParameter("pass2");
        String email        = this.request.getParameter("email");
        String firstname    = this.request.getParameter("firstname");
        String surname      = this.request.getParameter("surname");
        HashMap<String, Object> params = new HashMap<>();
        
        
        if (login != null && pass != null && pass2 != null && email != null && firstname != null && surname != null) {            
            ValidatorChain validator = new ValidatorChain();
            validator.addValidator(new EmptyValidator("login", login, "Login"));
            validator.addValidator(new EmptyValidator("pass", pass,  "Hasło"));
            validator.addValidator(new EmptyValidator("email", email, "Email"));
            validator.addValidator(new EmailValidator("email", email));
            validator.addValidator(new EmptyValidator("firstname", firstname, "Imię"));
            validator.addValidator(new EmptyValidator("surname", surname, "Nazwisko"));
            
            validator.validate();
            if (!validator.isVlid()) {
                params.put("registerErrors", validator.getErrors());
            } else {
                User u = new User();
                u.setEmail(email);
                u.setFirstname(firstname);
                u.setSurname(surname);
                u.setUsername(login);
                u.setPassword(pass);
                
                ArrayList<String> errors = new ArrayList<>();
                
                if (UserDAO.getByLoginOrEmail(login, email)) {
                    errors.add("Podany login lub email jest już w użyciu");
                } else {
                    UserDAO.insert(u);
                    if (!u.isValid()) {                    
                        errors.add("Nie można zarejestrować");
                    } else {


                        this.render("Register:success", params);
                        return;
                    }
                }
                
                if (!errors.isEmpty()) {
                    params.put("registerErrors", errors);
                }
            }            
        }
        
        
        this.render("Register:index", params);
    }
    
    
    @Override
    public void doGET() {   
        this.render("Register:index");
      
    }
}
