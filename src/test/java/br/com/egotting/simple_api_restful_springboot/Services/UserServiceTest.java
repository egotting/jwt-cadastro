package br.com.egotting.simple_api_restful_springboot.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.egotting.simple_api_restful_springboot.Exceptions.NullEmail;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.Roles;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.IUserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Services.Security.Token.TokenService;
import br.com.egotting.simple_api_restful_springboot.domain.Services.User.UserServices;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServices userServices;
    @Mock
    IUserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private TokenService tokenService;
    @Mock
    private AuthenticationManager manager;

    @Test
    public void testCadastroUser() {
        String Email = "tes.test@gmail.com";
        String Password = "test1123";
        Roles roles = Roles.USER;
        String encryptionPassword = "senha criptografada";
        AuthRequestDTO data = new AuthRequestDTO(Email, encryptionPassword, roles);

        when(passwordEncoder.encode(Password)).thenReturn(data.password());

        userServices.Cadastro(data);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        assertEquals(Email, data.email());
        assertEquals(encryptionPassword, data.password());
        assertEquals(roles, data.roles());
    }

    @Test
    public void testCadastroUserWithNullEmail() {

        AuthRequestDTO data = new AuthRequestDTO(null, "test", Roles.USER);

        assertThrows(NullEmail.class, () -> userServices.Cadastro(data));
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testLoginUser() {
        String email = "tes.test@gmail.com";
        String password = "test1123";
        var data = new GeneralRequestDTO(email, password);

        var user = new User();
        user.setEmail(email);
        user.setPassword(password);

        when(userRepository.findByEmail(email)).thenReturn(user);
        var authInput = new UsernamePasswordAuthenticationToken(email, password);

        var authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user);
        when(manager.authenticate(authInput)).thenReturn(authentication);

        when(tokenService.generateToken(user)).thenReturn("token falso");
        userServices.Login(data);

        verify(manager).authenticate(authInput);
        verify(tokenService).generateToken(user);
    }

    @Test
    public void testNullLoginEmail() {
        String email = null;
        String password = "test1123";
        var data = new GeneralRequestDTO(email, password);

        assertThrows(NullEmail.class, () -> userServices.Login(data));
    }

    @Test
    public void testTokenService() {
        String email = "test@gmail.com";
        String token_falso = "token_falso";

        var user = new User();
        user.setEmail(email);

        when(tokenService.generateToken(user)).thenReturn(token_falso);

        assertEquals("token_falso", token_falso);
    }

    @Test
    public void testTokenIsNull() {
        String tokenInvalido = null;
        assertNull(tokenService.validateToken(tokenInvalido));
    }

    @Test
    public void testTokenNotFoundIssues() {
        var token = "testestest";
        Algorithm algorithm = Algorithm.HMAC256(token);
        String tokenComOutraIssues = JWT
                .create()
                .withIssuer("issues-invalida")
                .withSubject("test@test.com")
                .sign(algorithm);
        assertNull(tokenComOutraIssues);
    }

}
