package br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Enum.ErrorTypes;


public class Error {
    private static final Error None = new Error("", "", ErrorTypes.Failure);

    public Error(String code, String description, ErrorTypes types) {
        Code = code;
        Description = description;
        Types = types;
    }


    private final String Code;
    private final String Description;
    private final ErrorTypes Types;


    public static Error None() {
        return None;
    }

    public static Error Success(String code, String description) {
        return new Error(code, description, ErrorTypes.Success);
    }

    public static Error Failure(String code, String description) {
        return new Error(code, description, ErrorTypes.Failure);
    }

    public static Error Warning(String code, String description) {
        return new Error(code, description, ErrorTypes.Warning);
    }

    public static Error NotFound(String code, String description) {
        return new Error(code, description, ErrorTypes.NotFound);
    }

    public static Error Validation(String code, String description) {
        return new Error(code, description, ErrorTypes.Validation);
    }

    public static Error ServerError(String code, String description) {
        return new Error(code, description, ErrorTypes.ServerError);
    }

    public static Error Unauthorized(String code, String description) {
        return new Error(code, description, ErrorTypes.Authorization);
    }

    public String getCode() {
        return Code;
    }

    public String getDescription() {
        return Description;
    }

    public ErrorTypes getTypes() {
        return Types;
    }
}
