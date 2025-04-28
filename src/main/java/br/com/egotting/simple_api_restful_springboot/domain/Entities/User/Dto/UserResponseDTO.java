package br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto;


public record UserResponseDTO(String email,
                              java.util.List<br.com.egotting.simple_api_restful_springboot.domain.Entities.Role.Role> roles) {

}
