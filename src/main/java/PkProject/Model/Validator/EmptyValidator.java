package PkProject.Model.Validator;

import java.util.ArrayList;

public class EmptyValidator extends AbstractValidator {
    private String msg = "Pole %s nie może być puste";
    
    private String fieldRealName;
    private String value;
    
    
    public EmptyValidator(
            String fieldName,
            String value, 
            String fieldRealName,
            String msg
    ) {
        super(fieldName);
        this.msg = msg;
        this.fieldRealName = fieldRealName;
        this.value = value;
    }
    
    public EmptyValidator(
            String fieldName, 
            String value,
            String fieldRealName
    ) {
        super(fieldName);
        this.fieldRealName = fieldRealName;
        this.value = value;
    }
   
    @Override
    public void validate() {
        this.errors = new ArrayList<>();
        if (this.value.isEmpty() || this.value.length() < 1) {
            this.errors.add(String.format(msg, this.fieldRealName));
        }
    }
    
}