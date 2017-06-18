package PkProject.Model.Validator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends AbstractValidator {
    private String value;
    private String msg = "Podany email jest niepoprawny";
    
    
    public EmailValidator(String fieldName, String value) {
        super(fieldName);
        this.value = value;
    }
    
    public EmailValidator(String fieldName, String value, String msg) {
        super(fieldName);
        this.value = value;
        this.msg = msg;
    }

    @Override
    public void validate() {
        this.errors = new ArrayList<>();
        
        if (!EmailValidator.isValidEmailAddress(this.value)) {
            this.errors.add(msg);
        }
    }
    
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(email).compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    
}
