package br.com.egotting.simple_api_restful_springboot.api.User;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.CreateUserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Services.Auth.AuthServiceImpl;
import org.springframework.web.bind.annotation.RestController;

import br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto.AuthRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthController {


    private final AuthServiceImpl services;

    public AuthController(AuthServiceImpl service) {
        this.services = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Result<?>> login(
            @RequestBody AuthRequestDTO data) {
        return services.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity<Result<?>> register(
            @RequestBody CreateUserRequestDTO data) {
        return services.register(data);
    }
}
