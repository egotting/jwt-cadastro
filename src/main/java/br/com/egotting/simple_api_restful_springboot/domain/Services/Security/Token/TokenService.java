package br.com.egotting.simple_api_restful_springboot.domain.Services.Security.Token;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.User;

@Service
public class TokenService {

    @Value("${token.api.jwt}")
    private String secret;

    public String generateToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT
                    .create()
                    .withIssuer("simple-api-restful-springboot")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirateDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro enquanto tentava autenticas");
        }
    }

    public String validateToken(String token) {
        if (token == null) {
            return null;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT
                    .require(algorithm)
                    .withIssuer("simple-api-restful-springboot")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpirateDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
