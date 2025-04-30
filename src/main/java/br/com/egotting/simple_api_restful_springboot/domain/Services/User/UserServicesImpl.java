package br.com.egotting.simple_api_restful_springboot.domain.Services.User;

import java.util.List;
import java.util.stream.Collectors;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error;
import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.FindAllDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.FindEmailDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Security.config.SecurityConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.egotting.simple_api_restful_springboot.Exceptions.UserServiceLogicException;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.ResponseStatusDTOs.ResponseStatusDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.UpdateRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.ResponseStatus;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.IUserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.Interface.IUserServices;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error.*;

@Service
public class UserServicesImpl implements IUserServices {

    private static final Logger log = LoggerFactory.getLogger(UserServicesImpl.class);


    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final SecurityConfiguration securityConfiguration;

    public UserServicesImpl(IUserRepository userRepository,
                            PasswordEncoder passwordEncoder, SecurityConfiguration securityConfiguration) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.securityConfiguration = securityConfiguration;
    }


    @Override
    public ResponseEntity<ResponseStatusDTO<List<FindAllDTO>>> findAll()
            throws UserServiceLogicException {
        try {

            var users = userRepository.findAll();

            var userDto = users.stream()
                    .map(user -> new FindAllDTO(user.getEmail(), user.getCreatedAccount()))
                    .collect(Collectors.toList());

            var response = new ResponseStatusDTO<>(ResponseStatus.SUCCESS.name(), userDto);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        } catch (Exception e) {
            log.error("Falha ao buscar todos os usuarios: " + e.getMessage());
            throw new UserServiceLogicException("");
        }
    }

    @Override
    public ResponseEntity<Result<?>> findEmail(String email, FindEmailDTO data) {
        try {
            var user = userRepository.findByEmail(email);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.Success(Success("Usuario.Encontrado",
                            "Usuario Encontrado com Sucesso: { " + user.getEmail() + " | Data da Criaçao: " + user.getCreatedAccount() + " }")));
        } catch (Exception e) {
            log.error("Erro no servidor", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.Failure(ServerError("Server.Error", "Erro no Servidor")));
        }

    }

    @Override
    public ResponseEntity<Result<?>> updateEmailUser(String email, UpdateRequestDTO data) {
        try {
            var user = userRepository.findByEmail(email);
            if (user.getEmail() == null)
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Result.Failure(NotFound("Not.FoundUser", "Usuario nao encontrado")));

            if (!passwordEncoder.matches(data.getOldPassword(), user.getPassword()))
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(Result.Failure(Error.Unauthorized("Unauthorized", "Senha incorreta")));


            if (user.getEmail() != null) user.setEmail(data.getNewEmail());

            if (data.getNewPassword() != null)
                user.setPassword(securityConfiguration.passwordEncoder().encode(data.getNewPassword()));


            userRepository.save(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.Success(Success("Usuario.Atualizaddo",
                            "Usuario Atualizado com Sucesso" + "Sua conta foi atualizada: { " + data.getNewEmail() + " | " + data.getUpdateAccount() + " }")));
        } catch (Exception e) {
            log.error("Falha ao atualizar o usuario: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.Failure(ServerError("Error.Update.User", "Erro interno ao atualizar o usuário")));
        }
    }

    @Override
    public ResponseEntity<Result<?>> deleteUser(String email) {
        try {
            var user = userRepository.findByEmail(email);
            if (user == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Result.Failure(NotFound("Usuario.NaoEncontrado", "Usuario não encontrado")));
            }

            userRepository.deleteByEmail(user.getEmail());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.Success(Success("Usuario.deletado", "Usuario Deletado com Sucesso")));
        } catch (Exception e) {
            log.error("Falha ao deletar o usuario: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.Failure(ServerError("Error.Deletar.User", "Erro interno ao deletar o usuário")));
        }
    }


}
