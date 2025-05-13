package br.com.egotting.simple_api_restful_springboot.Exceptions.Pattern.ResponseStatus;


public class ResponseStatusDTO<T> {
    private String status;
    private T response;


    public ResponseStatusDTO(String status, T response) {
        this.status = status;
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
