package br.com.egotting.simple_api_restful_springboot.domain.Services.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.egotting.simple_api_restful_springboot.Exceptions.NotFoundUserByEmail;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.Auth.Dto.AuthRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.GeneralDTOs.GeneralRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.User.Dto.UserRequestDTO;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.UserRepository;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;

    public void saveUserDto(UserRequestDTO user) {
        var nUser = new User(user.getEmail(), user.getPassword(), user.getRole());

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

    public void Cadastro(AuthRequestDTO user) {
        if (userRepository.findByEmail(user.email()) != null) {
            throw new NotFoundUserByEmail("Not Found User by Email" + user.email());
        }
        String encryptionPassword = new BCryptPasswordEncoder().encode(user.password());
        User nUser = new User(user.email(), encryptionPassword, user.roles());
        userRepository.save(nUser);
    }

    public void Login(GeneralRequestDTO data) {
        var userPass = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        this.manager.authenticate(userPass);
    }
}
