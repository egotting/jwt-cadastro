package br.com.egotting.simple_api_restful_springboot.api.Routes;

public class Routes {

    public static final String MAIN_ENDPOINTS = "/v1/api";

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/v1/api/login",
            "/v1/api/register"
    };

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/v1/api/update/user",
            "/v1/api/delete/user"
    };

    public static final String[] ENDPOINTS_ADMIN = {
            "/v1/api/get/users",
            "/v1/api/get/user/**",
    };
}
