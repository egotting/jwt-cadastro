package br.com.egotting.simple_api_restful_springboot.domain.Services.Auth.Interface;

import br.com.egotting.simple_api_restful_springboot.Exceptions.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.CreateUserRequestDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<Result<?>> login(AuthRequestDTO data);

    ResponseEntity<Result<?>> register(CreateUserRequestDTO data);


}
