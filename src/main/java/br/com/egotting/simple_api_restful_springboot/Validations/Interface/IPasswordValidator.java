package br.com.egotting.simple_api_restful_springboot.Validations.Interface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.auth0.jwt.interfaces.Payload;

import br.com.egotting.simple_api_restful_springboot.Validations.PasswordValidator;
import jakarta.validation.Constraint;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface IPasswordValidator {
    String message() default "Senha inválida. Deve conter pelo menos 8 caracteres, incluindo letras, números e caracteres especiais.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
