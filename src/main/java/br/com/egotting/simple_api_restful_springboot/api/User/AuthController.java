package br.com.egotting.simple_api_restful_springboot.api.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.egotting.simple_api_restful_springboot.domain.Entity.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/api")
public class AuthController {

    @Autowired
    private UserServices services;

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody @Validated GeneralRequestDTO data) {
        if (data.password() == null || data.email() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ou senha incorreto");
        }
        services.Login(data);
        return ResponseEntity.status(HttpStatus.OK).body("Login realizado com sucesso");
    }

    @PostMapping("/user/register")
    public ResponseEntity<String> register(@RequestBody @Validated AuthRequestDTO data) {
        if (data.password() == null || data.email() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ou senha não podem ser nulos");
        }
        services.Cadastro(data);
        return ResponseEntity.status(HttpStatus.OK).body("Cadastro realizado com sucesso");
    }

}
