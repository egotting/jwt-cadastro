package br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs;


import java.util.Objects;

public class GeneralReponseDTO<T> {
    private String status;
    private T response;

    public GeneralReponseDTO() {
    }

    public GeneralReponseDTO(String status, T response) {
        this.status = status;
        this.response = response;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralReponseDTO<?> that = (GeneralReponseDTO<?>) o;
        return Objects.equals(status, that.status) && Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, response);
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
