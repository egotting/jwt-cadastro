package br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto;


import java.time.LocalDateTime;

public record FindAllDTO(String email, LocalDateTime createdAt) {
}
