package br.com.egotting.simple_api_restful_springboot.domain.Security.config;

import br.com.egotting.simple_api_restful_springboot.api.Routes.Routes;
import br.com.egotting.simple_api_restful_springboot.domain.Enums.NameRoles;
import br.com.egotting.simple_api_restful_springboot.domain.Security.authentication.UserAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserAuthenticationFilter filter;

    public SecurityConfiguration(UserAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/listar").hasRole(NameRoles.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/user/deletar/**").hasRole(NameRoles.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/user/atualizar/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/encontrar/**").authenticated()
                        .requestMatchers(HttpMethod.POST, Routes.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConf) throws Exception {
        return authenticationConf.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
