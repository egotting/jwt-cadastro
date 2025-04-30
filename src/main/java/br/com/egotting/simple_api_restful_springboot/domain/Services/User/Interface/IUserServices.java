package br.com.egotting.simple_api_restful_springboot.domain.Services.User.Interface;

import java.util.List;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.FindAllDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.FindEmailDTO;
import org.springframework.http.ResponseEntity;

import br.com.egotting.simple_api_restful_springboot.Exceptions.UserServiceLogicException;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.ResponseStatusDTOs.ResponseStatusDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UpdateRequestDTO;

public interface IUserServices {

    ResponseEntity<ResponseStatusDTO<List<FindAllDTO>>> findAll()
            throws UserServiceLogicException;

    ResponseEntity<Result<?>> findEmail(String email, FindEmailDTO data);

    ResponseEntity<Result<?>> updateEmailUser(String email, UpdateRequestDTO data);

    ResponseEntity<Result<?>> deleteUser(String email);


}
