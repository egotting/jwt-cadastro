package br.com.egotting.simple_api_restful_springboot.domain.Entity.User.Dto;

import br.com.egotting.simple_api_restful_springboot.Validations.Interface.IPasswordValidator;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
                Long id,
                @NotBlank(message = "Email não pode estar vazio") @Email String email,
                @Size(min = 8) @IPasswordValidator @NotBlank(message = "Email não pode estar vazio") String password,
                Roles role) {
}
