package br.com.egotting.simple_api_restful_springboot.domain.Enums;

public enum Roles {
    ADM,
    USER;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
