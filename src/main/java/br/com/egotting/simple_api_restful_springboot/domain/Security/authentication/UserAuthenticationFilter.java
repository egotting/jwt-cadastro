package br.com.egotting.simple_api_restful_springboot.domain.Security.authentication;

import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.IUserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Security.userdetails.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final IUserRepository userRepository;
    private final JwtTokenService jwtTokenService;

    public UserAuthenticationFilter(IUserRepository userRepository, JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = recoverToken(request);
            if (token != null) {
                String subject = jwtTokenService.getSubjectToken(token);
                User user = userRepository.findByEmail(subject);
                UserDetailsImpl userDetails = new UserDetailsImpl(user);

                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        filterChain.doFilter(request, response);

    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

}
