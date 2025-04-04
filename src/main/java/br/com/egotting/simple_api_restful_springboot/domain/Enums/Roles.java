package br.com.egotting.simple_api_restful_springboot.domain.Enums;

public enum Roles {
    USER("USER"),
    ADMIN("ADMIN");

    private String roles;

    Roles(String roles) {
        this.roles = roles;
    }

    String getRoles() {
        return this.roles;
    }
}
