package org.validationDemo.Utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CannotHaveBlankValidator implements  ConstraintValidator<CannotHaveBlank, String>{
	@Override
	public void initialize(CannotHaveBlank constraintAnnotation) {
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
  
        if (value != null && value.contains(" ")) {
	        
            String defaultConstraintMessageTemplate = context.getDefaultConstraintMessageTemplate();
            System.out.println("default message :" + defaultConstraintMessageTemplate);
            
            context.disableDefaultConstraintViolation();
    
            context.buildConstraintViolationWithTemplate("can not contains blank").addConstraintViolation();
            return false;
        }
        return true;
    }
}
