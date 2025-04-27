package br.com.egotting.simple_api_restful_springboot.domain.Enums;

public enum Roles {
    ADMIN("ADMIN"),
    USER("USER");

    String roleId;

    Roles(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }
}
