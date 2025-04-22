package br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern;

import jakarta.annotation.Nullable;
import lombok.Getter;

@Getter
public class Result<T> {

    public Result(T value) {
        IsSuccess = true;
        Value = value;
        _Error = Error.None();
    }

    public Result(Error error) {
        IsSuccess = false;
        _Error = error;
    }

    public boolean IsSuccess;
    @Nullable
    public T Value;
    public Error _Error;

    public static <T> Result<T> Success(T value) {
        return new Result<>(value);
    }

    public static <T> Result<T> Failure(Error error) {
        return new Result<>(error);
    }
}
