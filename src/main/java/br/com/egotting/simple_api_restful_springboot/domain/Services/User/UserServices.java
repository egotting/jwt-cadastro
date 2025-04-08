package br.com.egotting.simple_api_restful_springboot.domain.Services.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.egotting.simple_api_restful_springboot.Exceptions.NotFoundUserByEmail;
import br.com.egotting.simple_api_restful_springboot.Exceptions.NullEmail;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.User.Dto.UserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.UserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Services.Security.Token.TokenService;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    public void saveUserDto(UserRequestDTO user) {
        var nUser = new User(user.email(), user.password(), user.role());

        userRepository.save(nUser);
    }

    public Iterable<User> FindAll() {
        return userRepository.findAll();
    }

    public Optional<User> findEmail(String item) {
        if (userRepository.findByItem(item) != null) {
            throw new NotFoundUserByEmail("Not Found User by Email: " + item);
        }
        return userRepository.findByItem(item);
    }

    public void UpdateUser(String email, User user) {
        Optional<User> _user = userRepository.findByItem(email);
        if (!email.equals(_user.get().getEmail())) {
            throw new NotFoundUserByEmail("Not Found User by Email: " + email);
        }
        _user.get().setEmail(user.getEmail());
        userRepository.save(_user.get());
    }

    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    public void Cadastro(AuthRequestDTO data) {
        if (data.email() == null) {
            throw new NullEmail("Email não pode ser nulo");
        }
        String encryptionPassword = new BCryptPasswordEncoder().encode(data.password());
        User nUser = new User(data.email(), encryptionPassword, data.roles());
        userRepository.save(nUser);
    }

    public void Login(GeneralRequestDTO data) {
        if (userRepository.findByEmail(data.email()) == null) {
            throw new NullEmail("Este email não foi encontrado:  " + data.email());
        }
        var userPass = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.manager.authenticate(userPass);

        tokenService.generateToken((User) auth.getPrincipal());
    }
}
