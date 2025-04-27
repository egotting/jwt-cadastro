package br.com.egotting.simple_api_restful_springboot.domain.Services.User.Interface;

import java.util.List;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import org.springframework.http.ResponseEntity;

import br.com.egotting.simple_api_restful_springboot.Exceptions.NotFoundUser;
import br.com.egotting.simple_api_restful_springboot.Exceptions.NotFoundUserByEmail;
import br.com.egotting.simple_api_restful_springboot.Exceptions.UserServiceLogicException;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralReponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.DeleteRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UserResponseDTO;

public interface IUserService {

    ResponseEntity<GeneralReponseDTO<List<UserResponseDTO>>> findAll()
            throws UserServiceLogicException;

    ResponseEntity<Result<?>> findEmail(String item)
            throws NotFoundUserByEmail, UserServiceLogicException;

    ResponseEntity<Result<?>> updateEmailUser(GeneralRequestDTO data)
            throws NotFoundUserByEmail, UserServiceLogicException;

    ResponseEntity<Result<?>> deleteUser(DeleteRequestDTO data)
            throws NotFoundUserByEmail, UserServiceLogicException;


}
