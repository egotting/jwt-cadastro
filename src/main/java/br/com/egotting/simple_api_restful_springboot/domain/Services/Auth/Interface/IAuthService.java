package br.com.egotting.simple_api_restful_springboot.domain.Services.Auth.Interface;

import br.com.egotting.simple_api_restful_springboot.Exceptions.UserServiceLogicException;
import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralReponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.CreateUserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAuthService {
    ResponseEntity<Result<?>> login(AuthRequestDTO data);

    ResponseEntity<Result<?>> register(CreateUserRequestDTO data);


}
