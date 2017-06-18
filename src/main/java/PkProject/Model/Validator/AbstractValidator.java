package PkProject.Model.Validator;

import java.util.ArrayList;

public abstract class AbstractValidator implements ValidatorInterface {
    protected ArrayList<String> errors;
    protected String fieldName;
    
    public AbstractValidator(String fieldName) {
        this.fieldName = fieldName;
    }
    
    @Override
    public String getFieldName() {
        return this.fieldName;
    }
    
    @Override
    public ArrayList<String> getErrors() {
        return this.errors;
    }
    
    @Override
    public Boolean isValid()
    {
        return this.errors.isEmpty();
    }
}
