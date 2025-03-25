package br.com.egotting.simple_api_restful_springboot.domain.Services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.egotting.simple_api_restful_springboot.Exceptions.NotFoundUserByEmail;
import br.com.egotting.simple_api_restful_springboot.domain.Entity.User;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.UserRepository;

@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(@Lazy UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> FindAll() {
        return userRepository.findAll();
    }

    public User findEmail(String email) {
        if (userRepository.findByEmail(email) != null) {
            throw new NotFoundUserByEmail("Not Found User by Email: " + email);
        }
        return userRepository.findByEmail(email);
    }

    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }
}
