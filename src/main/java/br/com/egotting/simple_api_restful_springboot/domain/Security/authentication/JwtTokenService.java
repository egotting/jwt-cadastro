package br.com.egotting.simple_api_restful_springboot.domain.Security.authentication;

import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Error;
import br.com.egotting.simple_api_restful_springboot.Pattern.ResultPattern.Result;
import br.com.egotting.simple_api_restful_springboot.domain.Security.userdetails.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.stream.Collectors;


@Service
public class JwtTokenService {
    private static final String ISSUER = "simple-api-restul-springboot";
    @Value("${jwt.private.key}")
    private String key;

    public String GenerateToken(UserDetailsImpl user) {
        if (user != null) {
            new Result<>(Result.Failure(Error.Failure("User.Invalid", "Usuario invalido")));
        }

        Algorithm algorithm = Algorithm.HMAC256(key);
        String token = JWT.create()
                .withIssuer(ISSUER)
                .withSubject(user.getUsername())
                .withExpiresAt(expireAt())
                .sign(algorithm);
        System.out.println(token);
        return token;
    }

    public String validateToken(String token) {
        if (token == null || token.isEmpty()) {
            new Result<>(Result.Failure(Error.Failure("Token.Invalid", "token vazio")));
        }
        Algorithm algorithm = Algorithm.HMAC256(key);
        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();
    }

    public Instant expireAt() {
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
    }
}
