package br.com.egotting.simple_api_restful_springboot.Exceptions;

public class UserServiceLogicException extends RuntimeException {
    public UserServiceLogicException(String message) {
        super(message);
    }


}
