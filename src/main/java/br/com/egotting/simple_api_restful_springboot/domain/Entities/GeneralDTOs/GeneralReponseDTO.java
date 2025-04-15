package br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralReponseDTO<T> {
    private String status;
    private T response;
}
