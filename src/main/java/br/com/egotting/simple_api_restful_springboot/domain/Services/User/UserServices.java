package br.com.egotting.simple_api_restful_springboot.domain.Services.User;

import java.util.List;
import java.util.stream.Collectors;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error;
import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
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
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.UserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.Interface.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServices.class);
    @Autowired
    UserRepository userRepository;


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
    public ResponseEntity<Result<?>> findEmail(String item)
            throws NotFoundUserByEmail, UserServiceLogicException {
        try {
            var user = userRepository.findByItem(item)
                    .orElseThrow(() -> new NotFoundUserByEmail("Email incorreto ou não cadastrado"));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.Success(Error.Success("Usuario.Encontrado", "Usuario Encontrado com Sucesso: " + user.getEmail())));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Result.Failure(Error.Failure("Error.NotFound.User", "Erro interno ao tentar encontrar o usuário")));
        }

    }

    @Override
    public ResponseEntity<Result<?>> updateEmailUser(GeneralRequestDTO data)
            throws NotFoundUserByEmail, UserServiceLogicException {
        try {
            var _user = userRepository.existsByEmail(data.email());
            if (!_user) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Result.Failure(Error.NotFound("Error.NotFound.User", "Usuario não encontrado")));
            }
            var updateUser = new User(data.email(), data.password());
            userRepository.save(updateUser);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.Success(Error.Success("Usuario.Atualizaddo", "Usuario Atualizado com Sucesso")));
        } catch (Exception e) {
            log.error("Falha ao atualizar o usuario: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.Failure(Error.Failure("Error.Update.User", "Erro interno ao atualizar o usuário")));
        }
    }

    @Override
    public ResponseEntity<Result<?>> deleteUser(DeleteRequestDTO data)
            throws NotFoundUserByEmail, UserServiceLogicException {
        try {
            var user = userRepository.findByItem(data.email())
                    .orElseThrow(() -> new NotFoundUserByEmail("Email incorreto ou não cadastrado"));
            userRepository.deleteByEmail(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.Success(Error.Success("Usuario.deletado", "Usuario Deletado com Sucesso")));
        } catch (Exception e) {
            log.error("Falha ao deletar o usuario: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.Failure(Error.Failure("Error.Deletar.User", "Erro interno ao deletar o usuário")));
        }
    }


}
