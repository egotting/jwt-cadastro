package br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralReponseDTO<T> {
    private String status;
    private T response;
}
