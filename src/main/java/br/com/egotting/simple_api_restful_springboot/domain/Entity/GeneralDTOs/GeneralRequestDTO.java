package br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record GeneralRequestDTO(@Email String email, @Size(min = 8) String password) {
}
