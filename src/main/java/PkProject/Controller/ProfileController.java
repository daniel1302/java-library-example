package PkProject.Controller;

public class IndexController extends AbstractController {

    @Override
    public void doGET() {
        this.request.setAttribute("test", "dadsadasdasdasdada");
        this.render("main");
    }

    @Override
    public void doPOST() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
