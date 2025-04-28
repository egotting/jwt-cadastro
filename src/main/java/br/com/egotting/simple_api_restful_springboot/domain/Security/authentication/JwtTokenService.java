package br.com.egotting.simple_api_restful_springboot.domain.Security.authentication;

import br.com.egotting.simple_api_restful_springboot.domain.Security.userdetails.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {
    @Value("${jwt.private.key}")
    private static String SECRET_KEY;
    private static final String ISSUER = "simple_api_restul-springboot";

    public String GenerateToken(UserDetailsImpl user) {
        try {
            Algorithm algoritm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationAt())
                    .withExpiresAt(expireAt())
                    .withSubject(user.getUsername())
                    .sign(algoritm);
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
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    }

    public Instant expireAt() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(4).toInstant();

    }
}
