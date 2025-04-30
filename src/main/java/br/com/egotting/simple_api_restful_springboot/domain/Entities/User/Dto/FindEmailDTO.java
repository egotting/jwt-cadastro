package br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FindEmailDTO(@Email @NotBlank(message = "Email não pode estar vazio") String email) {
}
