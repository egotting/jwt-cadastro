package br.com.egotting.simple_api_restful_springboot.domain.Entities.User;

import java.time.LocalDateTime;
import java.util.*;

import br.com.egotting.simple_api_restful_springboot.domain.Entities.Role.Role;
import jakarta.persistence.*;

import br.com.egotting.simple_api_restful_springboot.Validations.Interface.IPasswordValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


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
    @Size(min = 8)
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


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && Objects.equals(createdAccount, user.createdAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, roles, createdAccount);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
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
