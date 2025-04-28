package br.com.egotting.simple_api_restful_springboot.domain.Repositories.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    void deleteByEmail(Optional<User> email);
}
