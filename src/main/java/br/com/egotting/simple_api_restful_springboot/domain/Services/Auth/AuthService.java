package br.com.egotting.simple_api_restful_springboot.domain.Services.Auth;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error;
import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.UserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Services.Auth.Interface.IAuthService;
import br.com.egotting.simple_api_restful_springboot.domain.Services.Token.TokenService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error.NotFound;
import static br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error.Success;

@Service
public class AuthService implements IAuthService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    AuthenticationManager manager;


    @Override
    public ResponseEntity<Result<?>> Cadastro(AuthRequestDTO data) {

        try {
            var user = userRepository.findByEmail(data.getEmail());
            if (user.getUsername().equals(data.getEmail())) {
                return ResponseEntity
                        .status(HttpStatus.NOT_IMPLEMENTED)
                        .body(Result.Failure(NotFound("Error.Validation.User", "Usuario este usuario ja existe")));
            }
            String encryptionPassword = new BCryptPasswordEncoder().encode(data.getPassword());
            User nUser = new User(data.getEmail(), encryptionPassword);
            userRepository.save(nUser);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.Success(Success("Usuario.Cadastrado", "Usuario Cadastrado com Sucesso")));
        } catch (Exception e) {
            log.error("Falha ao cadastrar o usuario: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.Failure(br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error.Failure("Error.Cadastras.User", "Erro interno ao cadastras o usuário")));
        }
    }


    @Override
    public ResponseEntity<Result<?>> Login(GeneralRequestDTO data) {
        try {
            var user = userRepository.findByEmail(data.email());
            if (!user.getUsername().equals(data.email())) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Result.Failure(NotFound("Error.NotFound.User", "Usuario nao encontrado")));
            }
            var userPass = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.manager.authenticate(userPass);

            tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.Success(Success("Usuario.Logado", "Usuario Logado com Sucesso")));
        } catch (Exception e) {
            log.error("Falha ao logar o usuario: " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.Failure(Error.Failure("Error.Logar.User", "Erro interno ao logar o usuário")));
        }

    }
}
