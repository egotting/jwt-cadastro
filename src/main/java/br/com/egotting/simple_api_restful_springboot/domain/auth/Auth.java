package br.com.egotting.simple_api_restful_springboot.domain.auth;

import org.springframework.stereotype.Service;

@Service
public class Auth {
    public String autheticate(){
        return "TOKEN";
    }
}
