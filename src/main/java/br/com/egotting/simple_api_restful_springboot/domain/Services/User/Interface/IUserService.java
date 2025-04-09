package br.com.egotting.simple_api_restful_springboot.domain.Services.User.Interface;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.egotting.simple_api_restful_springboot.Exceptions.NotFoundUser;
import br.com.egotting.simple_api_restful_springboot.Exceptions.NotFoundUserByEmail;
import br.com.egotting.simple_api_restful_springboot.Exceptions.UserAlreadyExistsException;
import br.com.egotting.simple_api_restful_springboot.Exceptions.UserServiceLogicException;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs.GeneralReponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.User.Dto.UserRequestDTO;

@Service
public interface IUserService {
    public ResponseEntity<GeneralReponseDTO<?>> saveUserDto(UserRequestDTO user)
            throws UserAlreadyExistsException, UserServiceLogicException;

    public ResponseEntity<GeneralReponseDTO<?>> findAll()
            throws UserServiceLogicException;

    public ResponseEntity<GeneralReponseDTO<?>> findEmail(String item)
            throws NotFoundUserByEmail, UserServiceLogicException;

    public ResponseEntity<GeneralReponseDTO<?>> UpdateEmailUser(String email, GeneralRequestDTO user)
            throws NotFoundUserByEmail, UserServiceLogicException;

    public ResponseEntity<GeneralReponseDTO<?>> deleteUser(String email)
            throws NotFoundUserByEmail, UserServiceLogicException;

    public ResponseEntity<GeneralReponseDTO<?>> Cadastro(AuthRequestDTO data)
            throws UserServiceLogicException;

    public ResponseEntity<GeneralReponseDTO<?>> Login(GeneralRequestDTO data)
            throws NotFoundUser;
}
