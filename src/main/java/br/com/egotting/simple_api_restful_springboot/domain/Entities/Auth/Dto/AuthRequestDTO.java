package br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto;

import br.com.egotting.simple_api_restful_springboot.Validations.Interface.IPasswordValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequestDTO(@Email @NotBlank(message = "Email não pode estar vazio") String email,
                             @Size(min = 8) @IPasswordValidator @NotBlank(message = "Senha não pode estar vazio") String password) {
}

