package br.com.egotting.simple_api_restful_springboot.domain.Services.Auth;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error;
import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.Role.Role;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.CreateUserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.ResponseStatus;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.IUserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Security.authentication.JwtTokenService;
import br.com.egotting.simple_api_restful_springboot.domain.Security.config.SecurityConfiguration;
import br.com.egotting.simple_api_restful_springboot.domain.Security.userdetails.UserDetailsImpl;
import br.com.egotting.simple_api_restful_springboot.domain.Services.Auth.Interface.IAuthService;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.UserServicesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error.*;

@Service
public class AuthServiceImpl implements IAuthService {
    private static final Logger log = LoggerFactory.getLogger(UserServicesImpl.class);

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private SecurityConfiguration securityConfiguration;


    @Override
    public ResponseEntity<Result<?>> login(AuthRequestDTO data) {

        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(data.email(), data.password());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            jwtTokenService.GenerateToken(userDetails);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.Success(Error.Success("Usuario.Logado", "Usuario Logado com Sucesso")));
        } catch (Exception e) {
            log.error("Erro no servidor", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.Failure(Error.ServerError("Server.Error", "Erro no Servidor")));
        }
    }

    @Override
    public ResponseEntity<Result<?>> register(CreateUserRequestDTO data) {
        try {
            User newUser = new User.builder()
                    .email(data.email())
                    .password(securityConfiguration.passwordEncoder().encode(data.password()))
                    .build();

            userRepository.save(newUser);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result.Success(Error.Success("Usuario.Cadastrado", "Usuario cadastrado com Sucesso")));

        } catch (Exception e) {
            log.error("Erro no servidor", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.Failure(Error.ServerError("Server.Error", "Erro no Servidor")));
        }

    }
}
