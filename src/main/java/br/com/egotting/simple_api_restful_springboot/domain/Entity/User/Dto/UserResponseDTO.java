package br.com.egotting.simple_api_restful_springboot.domain.Entity.User.Dto;

import javax.management.relation.Role;

public record UserResponseDTO(String email, Role role) {
}
