package br.com.egotting.simple_api_restful_springboot.domain.Entities.User;

import java.time.LocalDateTime;

import br.com.egotting.simple_api_restful_springboot.domain.Entities.Role.Role;
import jakarta.persistence.*;

import br.com.egotting.simple_api_restful_springboot.Validations.Interface.IPasswordValidator;
import jakarta.validation.constraints.Email;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Email
    @Column(name = "user_email")
    private String email;
    @IPasswordValidator
    @Column(name = "user_password")
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_user", referencedColumnName = "name_roles")
    private Role roles;

    @Column(name = "user_created_account")
    private final LocalDateTime createdAccount = LocalDateTime.now();

    public User() {
    }

    public User(builder builder) {
        this.email = builder.email;
        this.password = builder.password;
        this.roles = builder.roles;
    }


    public void changeEmail(String email) {
        this.email = email;
    }

    public void changePassword(String password) {
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Role getRoles() {
        return roles;
    }

    public LocalDateTime getCreatedAccount() {
        return createdAccount;
    }


    // Builder pattern
    public static class builder {

        private String email;
        private String password;
        private Role roles;

        public builder email(String email) {
            this.email = email;
            return this;
        }

        public builder password(String password) {
            this.password = password;
            return this;
        }

        public builder role(Role role) {
            this.roles = role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}
