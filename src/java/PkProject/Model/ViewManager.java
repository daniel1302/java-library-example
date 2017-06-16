package PkProject.Model;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewManager {
    private HttpServletRequest request = null;
    private HttpServletResponse response = null;
    
    public void ViewManager(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    
    
    public void render(String jspFile, Map<String, String> parameters) {
        if (parameters != null) {
            parameters.keySet().forEach((key) -> {
                request.setAttribute(key, parameters.get(key));
            });
        }
        
        try {
            request.getRequestDispatcher(jspFile).forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ViewManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
