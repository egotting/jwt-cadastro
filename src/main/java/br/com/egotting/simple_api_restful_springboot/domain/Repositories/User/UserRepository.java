package br.com.egotting.simple_api_restful_springboot.domain.Repositories.User;

import java.util.Optional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.egotting.simple_api_restful_springboot.domain.Entity.User.User;
import jakarta.transaction.Transactional;

@Repository
@EnableJpaRepositories("br.com.egotting.simple_api_restful_springboot.config.JpaConfig")
@EntityScan(basePackages = "br.com.egotting.simple_api_restful_springboot.config.JpaConfig")
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    <T> Optional<T> findByItem(@Param("email") String item);

    @Modifying()
    @Transactional
    @Query("DELETE FROM User WHERE email = ?1")
    void deleteByEmail(String email);

    UserDetails findByEmail(String email);

}
