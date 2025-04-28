package br.com.egotting.simple_api_restful_springboot.domain.Entities.Role;

import br.com.egotting.simple_api_restful_springboot.domain.Enums.NameRoles;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name_roles")
    private NameRoles nameRoles;

    public Role() {
    }

    public Role(builder builder) {
        this.nameRoles = builder.roles;
    }

    public NameRoles getNameRoles() {
        return nameRoles;
    }

    public static class builder {
        @Enumerated(EnumType.STRING)
        private NameRoles roles;

        public builder roles(NameRoles roles) {
            this.roles = roles;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }
}
