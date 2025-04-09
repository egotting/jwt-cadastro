package br.com.egotting.simple_api_restful_springboot.domain.Repositories.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.egotting.simple_api_restful_springboot.domain.Entity.User.User;
import jakarta.transaction.Transactional;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByItem(@Param("email") String item);

    @Modifying()
    @Transactional
    @Query("DELETE FROM User WHERE email = ?1")
    Optional<User> deleteByEmail(User email);

    Iterable<User> findAllCadaster();

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    UserDetails findByEmail(String email);
}
