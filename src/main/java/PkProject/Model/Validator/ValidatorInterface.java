package PkProject.Model.Validator;

import java.util.ArrayList;

public interface ValidatorInterface {
    public void validate();
    
    public ArrayList<String> getErrors();
    
    public Boolean isValid();
    
    public String getFieldName();
}
