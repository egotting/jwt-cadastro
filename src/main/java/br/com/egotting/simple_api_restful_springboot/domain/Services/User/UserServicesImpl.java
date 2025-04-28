package br.com.egotting.simple_api_restful_springboot.domain.Services.User;

import java.util.List;
import java.util.stream.Collectors;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error;
import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.FindEmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.egotting.simple_api_restful_springboot.Exceptions.NotFoundUserByEmail;
import br.com.egotting.simple_api_restful_springboot.Exceptions.UserServiceLogicException;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralReponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.DeleteRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UserResponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.ResponseStatus;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.IUserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.Interface.IUserServices;
import org.springframework.stereotype.Service;

import static br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error.*;

@Service
public class UserServicesImpl implements IUserServices {

    private static final Logger log = LoggerFactory.getLogger(UserServicesImpl.class);
    @Autowired
    IUserRepository userRepository;


    @Override
    public ResponseEntity<GeneralReponseDTO<List<UserResponseDTO>>> findAll()
            throws UserServiceLogicException {
        try {

            var users = userRepository.findAll();

            var userDto = users.stream().map(user -> new UserResponseDTO(user.getEmail(), user.getRoles()))
                    .collect(Collectors.toList());

            var response = new GeneralReponseDTO<>(ResponseStatus.SUCCESS.name(), userDto);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        } catch (Exception e) {
            log.error("Falha ao buscar todos os usuarios: " + e.getMessage());
            throw new UserServiceLogicException("");
        }
    }

    @Override
    public ResponseEntity<Result<?>> findEmail(FindEmailDTO data)
            throws NotFoundUserByEmail, UserServiceLogicException {
        try {
            var user = userRepository.findByEmail(data.email());


            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.Success(Error.Success("Usuario.Encontrado", "Usuario Encontrado com Sucesso: " + user.get().getEmail())));
        } catch (Exception e) {
            log.error("Erro no servidor", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.Failure(Error.ServerError("Server.Error", "Erro no Servidor")));
        }

    }

    @Override
    public ResponseEntity<Result<?>> updateEmailUser(GeneralRequestDTO data)
            throws NotFoundUserByEmail, UserServiceLogicException {
        try {
            var user = userRepository.findByEmail(data.email());
            User updateUser = new User.builder()
                    .email(data.email())
                    .password(data.password())
                    .build();
            userRepository.save(updateUser);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.Success(Error.Success("Usuario.Atualizaddo", "Usuario Atualizado com Sucesso")));
        } catch (Exception e) {
            log.error("Falha ao atualizar o usuario: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.Failure(ServerError("Error.Update.User", "Erro interno ao atualizar o usuário")));
        }
    }

    @Override
    public ResponseEntity<Result<?>> deleteUser(DeleteRequestDTO data)
            throws NotFoundUserByEmail, UserServiceLogicException {
        try {
            var user = userRepository.findByEmail(data.email());

            userRepository.deleteByEmail(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.Success(Error.Success("Usuario.deletado", "Usuario Deletado com Sucesso")));
        } catch (Exception e) {
            log.error("Falha ao deletar o usuario: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.Failure(ServerError("Error.Deletar.User", "Erro interno ao deletar o usuário")));
        }
    }


}
