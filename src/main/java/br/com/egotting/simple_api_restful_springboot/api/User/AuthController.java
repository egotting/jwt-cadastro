package br.com.egotting.simple_api_restful_springboot.api.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.egotting.simple_api_restful_springboot.domain.Entity.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs.GeneralReponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<GeneralReponseDTO<?>> login(@RequestBody @Validated GeneralRequestDTO data) {
        return services.Login(data);
    }

    @PostMapping("/user/register")
    public ResponseEntity<GeneralReponseDTO<?>> register(@RequestBody @Validated AuthRequestDTO data) {
        return services.Cadastro(data);
    }

}
