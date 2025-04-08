package br.com.egotting.simple_api_restful_springboot.Validations;

import java.util.regex.Pattern;

import br.com.egotting.simple_api_restful_springboot.Validations.Interface.IPasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<IPasswordValidator, String> {
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()]).{8,}$";
    private Pattern pattern;

    @Override
    public void initialize(IPasswordValidator constraintAnnotation) {
        pattern = Pattern.compile(PASSWORD_REGEX);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }
        return pattern.matcher(password).matches();
    }

}