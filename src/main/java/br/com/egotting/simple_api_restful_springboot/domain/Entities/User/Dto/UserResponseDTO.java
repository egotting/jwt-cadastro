package br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto;

import br.com.egotting.simple_api_restful_springboot.domain.Enums.Roles;


public class UserResponseDTO {
    String email;
    Roles Roles;

    public UserResponseDTO(String email, Roles roles) {
        this.email = email;
        this.Roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRoles() {
        return Roles;
    }

    public void setRoles(Roles roles) {
        Roles = roles;
    }
}
