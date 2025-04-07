package br.com.egotting.simple_api_restful_springboot.Exceptions;

public class NullToken extends RuntimeException {
    public NullToken(String msg) {
        super(msg);
    }
}
