package br.com.egotting.simple_api_restful_springboot.api.User;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.CreateUserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Services.Auth.Interface.IAuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/api")
public class AuthController {

    @Autowired
    IAuthService services;

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
