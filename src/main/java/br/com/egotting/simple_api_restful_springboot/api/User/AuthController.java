package br.com.egotting.simple_api_restful_springboot.api.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.egotting.simple_api_restful_springboot.domain.Entity.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/api")
public class AuthController {

    @Autowired
    private UserServices services;

    @PostMapping("/user/login")
    public ResponseEntity<Void> login(@RequestBody @Validated GeneralRequestDTO data) {
        services.Login(data);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/user/register")
    public ResponseEntity<Void> register(@RequestBody @Validated AuthRequestDTO data) {
        services.Cadastro(data);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
