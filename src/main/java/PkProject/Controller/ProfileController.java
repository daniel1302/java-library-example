package PkProject.Controller;

import PkProject.DAO.UserDAO;
import PkProject.Entity.User;
import PkProject.Model.Validator.EmptyValidator;
import PkProject.Model.Validator.ValidatorChain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfileController extends AbstractController {

    @Override
    public void doGET() {
        if (!this.getUserSession().isValid()) {
            try {
                this.response.sendRedirect("login");
                return;
            } catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.render("Profile:index");
    }

    @Override
    public void doPOST() {
        if (!this.getUserSession().isValid()) {
            try {
                this.response.sendRedirect("login");
                return;
            } catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        HashMap<String, Object> params = new HashMap<>();
        
        if (this.request.getParameter("userDataSubmit") != null) {
            //User data update
            String firstname    = this.request.getParameter("firstname");
            String surname      = this.request.getParameter("surname");
            
            
            ValidatorChain validator = new ValidatorChain();
            validator.addValidator(new EmptyValidator("firstname", firstname, "Imię"));
            validator.addValidator(new EmptyValidator("surname", surname, "Nazwisko"));
            
            validator.validate();
            if (!validator.isVlid()) {
                params.put("profileErrors", validator.getErrors());
            } else {
                HashMap<String, String> data = new HashMap<>();
                data.put("firstname", firstname);
                data.put("surname", surname);
                
                User user = this.getUserSession();
                user.setFirstname(firstname);
                user.setSurname(surname);
                
                UserDAO.update(
                        this.getUserSession().getId().intValue(),
                        data
                );
                
                params.put("profileMessage", "Dane zostały poprawnie zaktualizowane");
            }
        } else {
            //User password update
            String oldPass         = this.request.getParameter("pass");
            String pass         = this.request.getParameter("newpass");
            String pass2        = this.request.getParameter("newpass2");
            
            ValidatorChain validator = new ValidatorChain();
            validator.addValidator(new EmptyValidator("pass", pass,  "Hasło"));
            validator.validate();
            ArrayList<String> list = new ArrayList<>();
            
            if (!validator.isVlid()) {
                params.put("profileErrors", validator.getErrors());
            } else if (!oldPass.equals(this.getUserSession().getPassword()) ) {
                list.add("Podałeś złe obecne hasło");
            } else if (!pass.equals(pass2))  {
                list.add("Hasła nie pasują do siebie");
            } else {
                HashMap<String, String> data = new HashMap<>();
                data.put("pass", pass);
                
                User user = this.getUserSession();
                user.setPassword(pass);
                
                UserDAO.update(
                        this.getUserSession().getId().intValue(),
                        data
                );
                
                params.put("profileMessage", "Hasło zostało zmienione");
            }
            
            if (!list.isEmpty()) {
                params.put("profileErrors", list);
            }
        }
       
        this.render("Profile:index", params);
    }
    
}
