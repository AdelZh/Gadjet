package peaksoft.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Email;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {


    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        return string.endsWith("@gmail.com");
    }
}
