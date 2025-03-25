package br.com.egotting.simple_api_restful_springboot.Exceptions;

public class NotFoundUserByEmail extends RuntimeException {
    public NotFoundUserByEmail(String email) {
        super(email);
    }
}
