package br.com.egotting.simple_api_restful_springboot.domain.Services.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error;
import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.egotting.simple_api_restful_springboot.Exceptions.NotFoundUser;
import br.com.egotting.simple_api_restful_springboot.Exceptions.NotFoundUserByEmail;
import br.com.egotting.simple_api_restful_springboot.Exceptions.NullEmail;
import br.com.egotting.simple_api_restful_springboot.Exceptions.UserAlreadyExistsException;
import br.com.egotting.simple_api_restful_springboot.Exceptions.UserServiceLogicException;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralReponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.DeleteRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UserResponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.ResponseStatus;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.UserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Services.Security.Token.TokenService;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.Interface.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServices implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseEntity<Result<?>> saveUserDto(UserRequestDTO data) {
        try {
            var nUser = new User(data.email(), data.password(), data.roles());
            userRepository.save(nUser);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new Result<>(Error.Success("New.User.Created", data.email())));
        } catch (Exception e) {
            log.error("Falha ao criar o usuario: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Result<>(Error.Failure("Error.Created.User", "Erro interno ao criar o usuário")));
        }

    }

    @Override
    public ResponseEntity<GeneralReponseDTO<List<UserResponseDTO>>> findAll() throws UserServiceLogicException {
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
                    .body(new Result<>(Error.Success("New.User.Created", user.getEmail())));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Result<>(Error.Failure("Error.NotFound.User", "Erro interno ao tentar encontrar o usuário")));
        }

    }

    @Override
    public ResponseEntity<Result<?>> UpdateEmailUser(String email, GeneralRequestDTO user)
            throws NotFoundUserByEmail, UserServiceLogicException {
        try {
            var _user = userRepository.findByItem(email)
                    .orElseThrow(() -> new NotFoundUserByEmail("Email incorreto ou não cadastrado"));
            if (_user == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new Result<>(Error.NotFound("Error.NotFound.User", "Usuario não encontrado")));
            }

            _user.setEmail(user.email());


            userRepository.save(_user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Result<>(Error.Success("Error.Update.User", "Usuario atualizado com sucesso")));
        } catch (Exception e) {
            log.error("Falha ao atualizar o usuario: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Result<>(Error.Failure("Error.Update.User", "Erro interno ao atualizar o usuário")));
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
                    .body(new Result<>(Error.Success("Error.Delete.User", "Usuario deletado com sucesso")));
        } catch (Exception e) {
            log.error("Falha ao deletar o usuario: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Result<>(Error.Failure("Error.Deletar.User", "Erro interno ao deletar o usuário")));
        }
    }

    @Override
    public ResponseEntity<Result<?>> Cadastro(AuthRequestDTO data)
            throws UserServiceLogicException, NullEmail {

        try {
            if (userRepository.existsByEmail(data.email())) {
                return ResponseEntity
                        .status(HttpStatus.NOT_IMPLEMENTED)
                        .body(new Result<>(Error.NotFound("Error.Validation.User", "Usuario este usuario ja existe")));
            }
            String encryptionPassword = new BCryptPasswordEncoder().encode(data.password());
            User nUser = new User(data.email(), encryptionPassword, data.roles());
            userRepository.save(nUser);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Result<>(Error.Success("Error.Cadastras.User", "Usuario cadastrar com sucesso")));
        } catch (Exception e) {
            log.error("Falha ao cadastrar o usuario: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Result<>(Error.Failure("Error.Cadastras.User", "Erro interno ao cadastras o usuário")));
        }
    }

    @Override
    public ResponseEntity<Result<?>> Login(GeneralRequestDTO data)
            throws NotFoundUser, UserServiceLogicException {
        try {
            if (!userRepository.existsByEmail(data.email())) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new Result<>(Error.NotFound("Error.NotFound.User", "Usuario nao encontrado")));
            }
            var userPass = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.manager.authenticate(userPass);

            tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Result<>(Error.Success("Error.Logar.User", "Usuario logado com sucesso")));
        } catch (Exception e) {
            log.error("Falha ao logar o usuario: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Result<>(Error.Failure("Error.Logar.User", "Erro interno ao logar o usuário")));
        }

    }
}
