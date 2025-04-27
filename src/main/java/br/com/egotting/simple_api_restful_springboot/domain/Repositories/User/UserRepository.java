package br.com.egotting.simple_api_restful_springboot.domain.Repositories.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.User;
import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByItem(@Param("email") String item);

    Boolean existsByEmail(String email);

    @Modifying()
    @Transactional
    @Query("DELETE FROM User WHERE email = ?1")
    void deleteByEmail(User email);

    UserDetails findByEmail(String email);
}
