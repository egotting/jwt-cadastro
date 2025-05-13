package br.com.egotting.simple_api_restful_springboot.Exceptions.Pattern.ResultPattern;


public class Result<T> {

    public Result(T value) {
        IsSuccess = true;
        Value = value;
    }

    public Result(Error error) {
        IsSuccess = false;
        _Error = error;
    }

    public boolean IsSuccess;
    public T Value;
    public Error _Error;


    public static <T> Result<T> Success(T value) {
        return new Result<>(value);
    }

    public static <T> Result<T> Failure(Error error) {
        return new Result<>(error);
    }
}
