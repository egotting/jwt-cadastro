package br.com.egotting.simple_api_restful_springboot.domain.Entities.ResponseStatusDTOs;


public class ResponseStatusDTO<T> {
    private String status;
    private T response;


    public ResponseStatusDTO(String status, T response) {
        this.status = status;
        this.response = response;
    }

}
