package peaksoft.request;

import peaksoft.validation.EmailValidation;
import peaksoft.validation.PasswordValid;

public record SignInRequest(

        @EmailValidation
        String email,

        @PasswordValid
        String password
) {
}
