package PkProject.Model.Validator;

public class LengthValidator extends AbstractValidator {
    private String minMsg = "Wartość w polu %s jest zbyt krótka";
    private String maxMsg = "Wartość w polu %s jest zbyt długa";
    
    private String fieldRealName;
    private int min = 0; 
    private int max = Integer.MAX_VALUE;
    private String value;
    
    
    public LengthValidator(
            String value,
            String fieldName, 
            String fieldRealName,
            int min, 
            int max, 
            String minMsg,
            String maxMsg
    ) {
        super(fieldName);
        this.minMsg = minMsg;
        this.maxMsg = maxMsg;
        
        this.init(value, fieldRealName, min, max);
        
    }
    
    public LengthValidator(
            String value,
            String fieldName, 
            String fieldRealName,
            int min, 
            int max
    ) {
        super(fieldName);
        
        this.init(value, fieldRealName, min, max);
        
    }
    
    private void init(
            String value,
            String fieldRealName,
            int min, 
            int max
    ) {
        this.value = value;
        this.min = min;
        this.fieldRealName = fieldRealName;
        this.max = max;
    }
    
    @Override
    public void validate() {
        if (this.value.length() < this.min) {
            this.errors.add(
                    String.format(minMsg, this.fieldRealName)
            );
        } else if (this.value.length() > this.max) {
            this.errors.add(
                    String.format(maxMsg, this.fieldRealName)
            );
        } 
    }
    
}