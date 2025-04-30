package br.com.egotting.simple_api_restful_springboot.domain.Enums;

public enum NameRoles {
    ADMIN("ADMIN"),
    COMMON("COMMON");

    private String roleId;

    NameRoles(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }
}
