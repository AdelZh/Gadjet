package peaksoft.request;

import jakarta.validation.constraints.Email;
import peaksoft.enums.Role;
import peaksoft.validation.EmailValidation;
import peaksoft.validation.PasswordValid;

public record SignUpRequest(
        String firstName,
        String lastName,
        @EmailValidation
        String email,

        @PasswordValid
        String password,
        Role role


) {
}
