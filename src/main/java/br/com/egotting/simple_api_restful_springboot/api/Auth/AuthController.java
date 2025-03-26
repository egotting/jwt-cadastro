package br.com.egotting.simple_api_restful_springboot.api.Auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.egotting.simple_api_restful_springboot.domain.auth.Auth;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    private final Auth auth;

    public AuthController(Auth auth) {
        this.auth = auth;
    }

    @PostMapping("/authenticate")
    public String postMethodName() {
        return auth.autheticate();
    }
    

}
