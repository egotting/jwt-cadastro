package br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto;

import br.com.egotting.simple_api_restful_springboot.Validations.Interface.IPasswordValidator;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class UserRequestDTO {

    @NotBlank(message = "Email não pode estar vazio")
    @Email
    String Email;
    @Size(min = 8)
    @IPasswordValidator
    @NotBlank(message = "Email não pode estar vazio")
    String password;


    public UserRequestDTO() {
    }

    public UserRequestDTO(String email, String password) {
        Email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserRequestDTO that = (UserRequestDTO) o;
        return Objects.equals(Email, that.Email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Email, password);
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
