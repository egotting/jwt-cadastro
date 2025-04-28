package br.com.egotting.simple_api_restful_springboot.domain.Services.User.Interface;

import java.util.List;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.FindEmailDTO;
import org.springframework.http.ResponseEntity;

import br.com.egotting.simple_api_restful_springboot.Exceptions.NotFoundUserByEmail;
import br.com.egotting.simple_api_restful_springboot.Exceptions.UserServiceLogicException;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralReponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.DeleteRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UserResponseDTO;

public interface IUserServices {

    ResponseEntity<GeneralReponseDTO<List<UserResponseDTO>>> findAll()
            throws UserServiceLogicException;

    ResponseEntity<Result<?>> findEmail(FindEmailDTO data)
            throws NotFoundUserByEmail, UserServiceLogicException;

    ResponseEntity<Result<?>> updateEmailUser(GeneralRequestDTO data)
            throws NotFoundUserByEmail, UserServiceLogicException;

    ResponseEntity<Result<?>> deleteUser(DeleteRequestDTO data)
            throws NotFoundUserByEmail, UserServiceLogicException;


}
