package br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto;

import br.com.egotting.simple_api_restful_springboot.domain.Enums.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    String email;
    Roles Roles;

    public UserResponseDTO(String email, Roles roles) {
        this.email = email;
        this.Roles = roles;
    }

}
