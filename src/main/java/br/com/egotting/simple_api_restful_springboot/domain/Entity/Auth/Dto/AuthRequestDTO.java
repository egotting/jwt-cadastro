package br.com.egotting.simple_api_restful_springboot.domain.Entity.Auth.Dto;

import br.com.egotting.simple_api_restful_springboot.domain.Enums.Roles;

public record AuthRequestDTO(String email, String password, Roles role) {
}