package br.com.egotting.simple_api_restful_springboot.domain.Services.Auth;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error;
import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.CreateUserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.NameRoles;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.Role.IRoleRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.IUserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Security.authentication.JwtTokenService;
import br.com.egotting.simple_api_restful_springboot.domain.Security.config.SecurityConfiguration;
import br.com.egotting.simple_api_restful_springboot.domain.Security.userdetails.UserDetailsImpl;
import br.com.egotting.simple_api_restful_springboot.domain.Services.Auth.Interface.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthServiceImpl implements IAuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final SecurityConfiguration securityConfiguration;

    public AuthServiceImpl(IUserRepository userRepository, IRoleRepository roleRepository, AuthenticationManager authenticationManager,
                           JwtTokenService jwtTokenService, SecurityConfiguration securityConfiguration) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.securityConfiguration = securityConfiguration;
    }

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
            var findRole = roleRepository.findByNameRoles(NameRoles.COMMON).orElseThrow(() ->
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
