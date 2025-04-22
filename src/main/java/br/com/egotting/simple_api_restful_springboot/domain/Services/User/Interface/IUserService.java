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
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UserResponseDTO;

public interface IUserService {
        public ResponseEntity<Result<?>> saveUserDto(UserRequestDTO user);

        public ResponseEntity<GeneralReponseDTO<List<UserResponseDTO>>> findAll()
                        throws UserServiceLogicException;

        public ResponseEntity<Result<?>> findEmail(String item)
                        throws NotFoundUserByEmail, UserServiceLogicException;

        public ResponseEntity<Result<?>> UpdateEmailUser(String email, GeneralRequestDTO user)
                        throws NotFoundUserByEmail, UserServiceLogicException;

        public ResponseEntity<Result<?>> deleteUser(DeleteRequestDTO data)
                        throws NotFoundUserByEmail, UserServiceLogicException;

        public ResponseEntity<Result<?>> Cadastro(AuthRequestDTO data)
                        throws UserServiceLogicException;

        public ResponseEntity<Result<?>> Login(GeneralRequestDTO data)
                        throws NotFoundUser;
}
