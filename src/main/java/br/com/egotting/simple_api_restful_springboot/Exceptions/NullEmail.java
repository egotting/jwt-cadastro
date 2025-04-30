package br.com.egotting.simple_api_restful_springboot.Exceptions;

public class NullEmail extends RuntimeException {
    public NullEmail(String email) {
        super(email);
    }
}
