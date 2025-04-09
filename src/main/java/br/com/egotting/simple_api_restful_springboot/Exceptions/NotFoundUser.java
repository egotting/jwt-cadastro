package br.com.egotting.simple_api_restful_springboot.Exceptions;

public class NotFoundUser extends RuntimeException {
    public NotFoundUser(String message) {
        super(message);
    }
}
