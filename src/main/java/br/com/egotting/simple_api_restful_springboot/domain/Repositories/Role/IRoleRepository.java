package br.com.egotting.simple_api_restful_springboot.domain.Repositories.Role;

import br.com.egotting.simple_api_restful_springboot.domain.Entities.Role.Role;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.NameRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNameRoles(NameRoles role);
}
