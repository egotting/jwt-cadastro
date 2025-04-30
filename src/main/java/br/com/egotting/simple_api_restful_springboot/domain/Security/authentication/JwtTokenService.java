package br.com.egotting.simple_api_restful_springboot.domain.Security.authentication;

import br.com.egotting.simple_api_restful_springboot.domain.Security.userdetails.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class JwtTokenService {
    private static final String ISSUER = "simple-api-restul-springboot";
    @Value("${jwt.private.key}")
    private String SECRET_KEY;

    public String GenerateToken(UserDetailsImpl user) {

        try {
            Algorithm algoritm = Algorithm.HMAC256(SECRET_KEY);
            String token = JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationAt())
                    .withExpiresAt(expireAt())
                    .withSubject(user.getUsername())
                    .sign(algoritm);
            System.out.println("Token: " + token);
            return token;
        } catch (JWTCreationException e) {
            throw new JWTCreationException("Erro ao gerar o token", e);
        }
    }

    public String getSubjectToken(String token) {
        try {
            Algorithm algoritm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algoritm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token invalido", e);
        }
    }

    public Instant creationAt() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("-03:00"));
    }

    public Instant expireAt() {
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
    }
}
