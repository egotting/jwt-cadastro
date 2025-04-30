package br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto;

import br.com.egotting.simple_api_restful_springboot.Validations.Interface.IPasswordValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class CreateUserRequestDTO {
    @Email
    @NotBlank(message = "Email precisa ser preenchido")
    private String email;
    @IPasswordValidator(message = "Senha precisa ter no mínimo 8 caracteres, uma letra, um número e um caractere especial")
    @NotBlank(message = "Senha precisa ser preenchida")
    private String password;
    @IPasswordValidator(message = "Precisa ser igual a senha passada")
    @NotBlank(message = "Confirmaçao de senha precisa ser preenchido")
    private String confirmPassword;

    public CreateUserRequestDTO(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }


    public String email() {
        return email;
    }

    public void email(String email) {
        this.email = email;
    }

    public String password() {
        return password;
    }

    public void password(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserRequestDTO that = (CreateUserRequestDTO) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(confirmPassword, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, confirmPassword);
    }
}
