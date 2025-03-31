package br.com.egotting.simple_api_restful_springboot.domain.Entity.User.Dto;

import java.time.LocalDateTime;

import br.com.egotting.simple_api_restful_springboot.domain.Enums.Roles;
import lombok.Data;

@Data
public class UserRequestDTO {
    private Long id;
    private String email;
    private String password;
    private Roles role;
    private LocalDateTime createdAccount = LocalDateTime.now();
}
