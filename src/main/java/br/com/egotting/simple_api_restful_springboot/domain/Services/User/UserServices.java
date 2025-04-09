package br.com.egotting.simple_api_restful_springboot.domain.Services.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.egotting.simple_api_restful_springboot.Exceptions.NotFoundUser;
import br.com.egotting.simple_api_restful_springboot.Exceptions.NotFoundUserByEmail;
import br.com.egotting.simple_api_restful_springboot.Exceptions.NullEmail;
import br.com.egotting.simple_api_restful_springboot.Exceptions.UserAlreadyExistsException;
import br.com.egotting.simple_api_restful_springboot.Exceptions.UserServiceLogicException;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs.GeneralReponseDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.User.Dto.UserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.ResponseStatus;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.IUserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Services.Security.Token.TokenService;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.Interface.IUserService;
import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServices implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseEntity<GeneralReponseDTO<?>> saveUserDto(UserRequestDTO user)
            throws UserAlreadyExistsException, UserServiceLogicException {

        try {
            if (userRepository.findByItem(user.email()) == null) {
                throw new UserAlreadyExistsException("User already exists: " + user.email());
            }

            var nUser = new User(user.email(), user.password(), user.role());

            userRepository.save(nUser);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new GeneralReponseDTO<>(ResponseStatus.SUCCESS.name(), "New user created: " + user.email()));
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        } catch (Exception e) {
            log.error("Falha ao criar o usuario: " + e.getMessage());
            throw new UserServiceLogicException("");
        }

    }

    @Override
    public ResponseEntity<GeneralReponseDTO<?>> findAll() throws UserServiceLogicException {
        try {
            if (userRepository.findAllCadaster() == null) {
                throw new UserServiceLogicException("Nenhum usuario encontrado.");
            }
            Iterable<User> users = userRepository.findAllCadaster();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new GeneralReponseDTO<>(ResponseStatus.SUCCESS.name(), users));
        } catch (Exception e) {
            log.error("Falha ao buscar todos os usuarios: " + e.getMessage());
            throw new UserServiceLogicException("");
        }
    }

    @Override
    public ResponseEntity<GeneralReponseDTO<?>> findEmail(String item)
            throws NotFoundUserByEmail, UserServiceLogicException {
        try {
            if (userRepository.findByItem(item) == null) {
                throw new NotFoundUserByEmail("Usuario não pode ser nulo: " + item);
            }
            Optional<User> user = userRepository.findByItem(item);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new GeneralReponseDTO<>(ResponseStatus.SUCCESS.name(), user));
        } catch (NotFoundUserByEmail e) {
            throw new NotFoundUserByEmail(e.getMessage());
        } catch (Exception e) {
            log.error("Falha ao buscar o usuario: " + e.getMessage());
            throw new UserServiceLogicException("");
        }

    }

    @Override
    public ResponseEntity<GeneralReponseDTO<?>> UpdateEmailUser(String email, GeneralRequestDTO user)
            throws NotFoundUserByEmail, UserServiceLogicException {
        try {
            var _user = userRepository.findByItem(email)
                    .orElseThrow(() -> new NotFoundUser("Email incorreto ou não cadastrado"));

            _user.setEmail(user.email());

            userRepository.save(_user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new GeneralReponseDTO<>(ResponseStatus.SUCCESS.name(),
                            "Seu email foi atualizado com sucesso!"));
        } catch (NotFoundUserByEmail e) {
            throw new NotFoundUserByEmail(e.getMessage());
        } catch (Exception e) {
            log.error("Falha ao atualizar o usuario: " + e.getMessage());
            throw new UserServiceLogicException("");
        }
    }

    @Override
    public ResponseEntity<GeneralReponseDTO<?>> deleteUser(String email)
            throws NotFoundUserByEmail, UserServiceLogicException {
        try {
            var user = userRepository.findByItem(email)
                    .orElseThrow(() -> new NotFoundUser("Email incorreto ou não cadastrado"));
            userRepository.deleteByEmail(user);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new GeneralReponseDTO<>(ResponseStatus.SUCCESS.name(),
                            "Usuario deletado com sucesso!"));
        } catch (NotFoundUserByEmail e) {
            throw new NotFoundUserByEmail(e.getMessage());
        } catch (Exception e) {
            log.error("Falha ao deletar o usuario: " + e.getMessage());
            throw new UserServiceLogicException("");
        }
    }

    @Override
    public ResponseEntity<GeneralReponseDTO<?>> Cadastro(AuthRequestDTO data) throws UserServiceLogicException {
        if (userRepository.findByEmail(data.email()) != null) {
            throw new NullEmail("Este email ja existe não pode ser cadastrado:  " + data.email());
        }
        try {
            String encryptionPassword = new BCryptPasswordEncoder().encode(data.password());
            User nUser = new User(data.email(), encryptionPassword, data.roles());
            userRepository.save(nUser);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new GeneralReponseDTO<>(ResponseStatus.SUCCESS.name(), "Usuario cadastrado com sucesso!"));
        } catch (Exception e) {
            log.error("Falha ao criar o usuario: " + e.getMessage());
            throw new UserServiceLogicException("");
        }

    }

    @Override
    public ResponseEntity<GeneralReponseDTO<?>> Login(GeneralRequestDTO data)
            throws NotFoundUser, UserServiceLogicException {
        if (userRepository.findByEmail(data.email()) == null) {
            throw new NullEmail("Este email não foi encontrado:  " + data.email());
        }
        try {
            var userPass = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.manager.authenticate(userPass);

            tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new GeneralReponseDTO<>(ResponseStatus.SUCCESS.name(), "Usuario logado com sucesso!"));
        } catch (NotFoundUser e) {
            throw new NotFoundUser(e.getMessage());
        } catch (Exception e) {
            log.error("Falha ao logar o usuario: " + e.getMessage());
            throw new UserServiceLogicException("");
        }

    }
}
