package br.com.egotting.simple_api_restful_springboot.Services;

import br.com.egotting.simple_api_restful_springboot.Exceptions.NullEmail;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.Role.Role;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.Dto.CreateUserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.NameRoles;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.Role.IRoleRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.IUserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Security.authentication.JwtTokenService;
import br.com.egotting.simple_api_restful_springboot.domain.Security.userdetails.UserDetailsImpl;
import br.com.egotting.simple_api_restful_springboot.domain.Services.Auth.AuthServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
//    @InjectMocks
//    AuthServiceImpl authService;
//    @Mock
//    IUserRepository userRepository;
//    @Mock
//    IRoleRepository roleRepository;
//    @Mock
//    private BCryptPasswordEncoder passwordEncoder;
//    @Mock
//    private JwtTokenService tokenService;
//    @Mock
//    private AuthenticationManager manager;
//
//    @Test
//    public void testCadastroUser() {
//        String Email = "tes.test@gmail.com";
//        String Password = "!Test1123342532452";
//        String ConfirmPassword = "!Test1123342532452";
//        CreateUserRequestDTO data = new CreateUserRequestDTO(Email, Password, ConfirmPassword);
//
//        authService.register(data);
//
//        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
//        verify(userRepository).save(userCaptor.capture());
//
//        assertEquals(Email, data.email());
//        assertEquals(Password, data.password());
//
//    }

    @Test
    public void testCadastroUserWithNullEmail() {
//
//        CreateUserRequestDTO data = new CreateUserRequestDTO(null, "test", "test");
//
//        assertThrows(NullEmail.class, () -> authService.register(data));
//        verify(userRepository, never()).save(any());
//    }
//
//    @Test
//    public void testLoginUser() {
//        String email = "user.admin@dev.com";
//        String password = "Test123123@_--";
//        var data = new AuthRequestDTO(email, password);
//
//        var user = new User.builder().email(email).password(password).build();
//        user.setEmail(email);
//        user.setPassword(password);
//        UserDetailsImpl userDetails = new UserDetailsImpl(user);
//        var authInput = new UsernamePasswordAuthenticationToken(email, password);
//
//        var authentication = mock(Authentication.class);
//        when(authentication.getPrincipal()).thenReturn(user);
//        when(manager.authenticate(authInput)).thenReturn(authentication);
//
//        when(tokenService.GenerateToken(userDetails)).thenReturn("token falso");
//        authService.login(data);
//
//        verify(manager).authenticate(authInput);
//        verify(tokenService).GenerateToken(userDetails);
//    }
//
//    @Test
//    public void testNullLoginEmail() {
//        String email = null;
//        String password = "test1123";
//        var data = new AuthRequestDTO(email, password);
//
//        assertThrows(RuntimeException.class, () -> authService.login(data));
//    }
//
//    @Test
//    public void testTokenService() {
//        String email = "user.admin@dev.com";
//        String password = "Test123123@_--@";
//
//
//        var findRole = roleRepository.findByNameRoles(NameRoles.COMMON).orElseThrow(() ->
//                new RuntimeException("Role not found"));
//        var user = new User.builder()
//                .email(email)
//                .password(password)
//                .role(findRole)
//                .build();
//
//        UserDetailsImpl userDetails = new UserDetailsImpl(user);
//        String token = tokenService.GenerateToken(userDetails);
//
//        Algorithm algorithm = Algorithm.HMAC256("seu-segredo-aqui");
//        String subject = JWT.require(algorithm)
//                .withIssuer("issuer-correto")
//                .build()
//                .verify(token)
//                .getSubject();
//
//        assertEquals(email, subject);
//    }
//
//    @Test
//    public void testTokenIsNull() {
//        String tokenInvalido = null;
//        assertNull(tokenService.getSubjectToken(tokenInvalido));
//    }


    }
}
