package br.com.egotting.simple_api_restful_springboot.domain.Security.config;

import br.com.egotting.simple_api_restful_springboot.domain.Repositories.User.IUserRepository;
import br.com.egotting.simple_api_restful_springboot.domain.Security.authentication.JwtTokenService;
import br.com.egotting.simple_api_restful_springboot.domain.Security.userdetails.UserDetailsImpl;
import br.com.egotting.simple_api_restful_springboot.domain.Security.userdetails.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterUserSecurity extends OncePerRequestFilter {


    private final JwtTokenService jwtTokenService;
    private final IUserRepository repository;

    public FilterUserSecurity(JwtTokenService jwtTokenService, IUserRepository iUserRepository) {
        this.jwtTokenService = jwtTokenService;
        this.repository = iUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoverToken(request);
        if (token == null)
            throw new ServletException("Token not found");

        if (token != null) {
            var sub = jwtTokenService.validateToken(token);

            var userDb = repository.findByEmail(sub);
            UserDetailsImpl userDetails = new UserDetailsImpl(userDb);
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    public String recoverToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) return null;
        return header.replace("Bearer ", "");
    }
}
