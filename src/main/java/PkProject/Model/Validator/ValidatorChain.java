package PkProject.Model.Validator;

import java.util.ArrayList;

public class ValidatorChain {
    private ArrayList<ValidatorInterface> validators;
    
    public void addValidator(ValidatorInterface e) {
        this.validators.add(e);
    }
    
    public void validate() {
        for (ValidatorInterface v : this.validators) {
            v.validate();
        }
    }
    
    public Boolean isVlid() {
        for (ValidatorInterface v : this.validators) {
            if (!v.getErrors().isEmpty()) {
                return false;
            }
        }
        
        return true;
    }
    
    public ArrayList<String> getInvalidFields() {
        ArrayList<String> fields;
        fields = new ArrayList<>();
        
        for (ValidatorInterface v : this.validators) {
            if (!v.isValid()) {
                fields.add(v.getFieldName());
            }
        }
        
        return fields;
    }
    
    public ArrayList<String> getErrors() {
        ArrayList<String> errors = new ArrayList<>();
        
        for (ValidatorInterface v : this.validators) {
            for (String error : v.getErrors()) {
                errors.add(error);
            }
        }
        
        return errors;
    }
}