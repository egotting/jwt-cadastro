package br.com.egotting.simple_api_restful_springboot.domain.Services.Auth;

import br.com.egotting.simple_api_restful_springboot.Exceptions.Pattern.ResultPattern.Error;
import br.com.egotting.simple_api_restful_springboot.Exceptions.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.CreateUserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.NameRoles;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.IRoleRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.IUserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Security.authentication.JwtTokenService;
import br.com.egotting.simple_api_restful_springboot.domain.Security.config.SecurityConfiguration;
import br.com.egotting.simple_api_restful_springboot.domain.Security.userdetails.UserDetailsImpl;
import br.com.egotting.simple_api_restful_springboot.domain.Services.Auth.Interface.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements IAuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final JwtTokenService jwtTokenService;
    private final SecurityConfiguration securityConfiguration;

    public AuthServiceImpl(IUserRepository userRepository, IRoleRepository roleRepository,
                           JwtTokenService jwtTokenService, SecurityConfiguration securityConfiguration) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtTokenService = jwtTokenService;
        this.securityConfiguration = securityConfiguration;
    }

    @Override
    public ResponseEntity<Result<?>> login(AuthRequestDTO data) {

        try {
            var user = userRepository.findByEmail(data.email());
            if (!user.getEmail().equals(data.email())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Result.Failure(Error.Validation("User.Not.Equal", "Usuario nao cadastrado nao é igual ao usuario passado")));
            }
            UserDetailsImpl userDetails = new UserDetailsImpl(user);
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
            var findRole = roleRepository.findByNameRoles(NameRoles.ADMIN).orElseThrow(() ->
                    new RuntimeException("Role not found"));
            if (userRepository.existsByEmail(data.email())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Result.Failure(Error.Warning("User.Existis", "Usuario ja existe")));
            }
            User newUser = new User.builder()
                    .email(data.email())
                    .password(securityConfiguration.passwordEncoder().encode(data.password()))
                    .role(findRole)
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
