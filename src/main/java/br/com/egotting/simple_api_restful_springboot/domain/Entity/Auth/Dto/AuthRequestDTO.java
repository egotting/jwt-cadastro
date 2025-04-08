package br.com.egotting.simple_api_restful_springboot.domain.Entity.Auth.Dto;

import br.com.egotting.simple_api_restful_springboot.domain.Enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record AuthRequestDTO(@Email String email, @Size(min = 8) String password, Roles roles) {
}