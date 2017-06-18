package PkProject.Controller;

public class ProfileController extends AbstractController {

    @Override
    public void doGET() {
        this.render("Profile:index");
    }

    @Override
    public void doPOST() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
