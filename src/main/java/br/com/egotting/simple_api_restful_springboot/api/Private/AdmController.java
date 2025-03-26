package br.com.egotting.simple_api_restful_springboot.api.Private;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/v1/api/private")
public class AdmController {



    @GetMapping("/test")
    public String getMethodName() {
        return "TEST ROTA PRIVADA";
    }
}
