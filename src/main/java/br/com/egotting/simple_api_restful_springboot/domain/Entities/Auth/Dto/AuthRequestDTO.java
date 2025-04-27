package br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto;

import br.com.egotting.simple_api_restful_springboot.Validations.Interface.IPasswordValidator;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class AuthRequestDTO {
    @Email
    @NotBlank(message = "Email não pode estar vazio")
    String Email;
    @Size(min = 8)
    @IPasswordValidator
    @NotBlank(message = "Email não pode estar vazio")
    String Password;
    Roles roles;

    public AuthRequestDTO() {
    }

    public AuthRequestDTO(String email, String password) {
        Email = email;
        Password = password;
        this.roles = Roles.USER;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AuthRequestDTO that = (AuthRequestDTO) o;
        return Objects.equals(Email, that.Email) && Objects.equals(Password, that.Password) && roles == that.roles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Email, Password, roles);
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}