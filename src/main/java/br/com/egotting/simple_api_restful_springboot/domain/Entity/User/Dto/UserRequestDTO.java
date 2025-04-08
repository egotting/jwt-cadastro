package br.com.egotting.simple_api_restful_springboot.domain.Entity.User.Dto;

import br.com.egotting.simple_api_restful_springboot.domain.Enums.Roles;

public record UserRequestDTO(
        Long id,
        String email,
        String password,
        Roles role) {
}
