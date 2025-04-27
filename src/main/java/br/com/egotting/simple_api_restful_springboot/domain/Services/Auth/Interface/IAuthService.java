package br.com.egotting.simple_api_restful_springboot.domain.Services.Auth.Interface;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralRequestDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<Result<?>> Cadastro(AuthRequestDTO data);

    ResponseEntity<Result<?>> Login(GeneralRequestDTO data);
}
