package br.com.egotting.simple_api_restful_springboot.domain.Security.authentication;

import br.com.egotting.simple_api_restful_springboot.domain.Entities.User.User;
import br.com.egotting.simple_api_restful_springboot.domain.Security.userdetails.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.nimbusds.jose.jwk.JWKException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.stream.Collectors;

@Service
public class JwtTokenService {
    private static final String ISSUER = "simple-api-restul-springboot";
    private JwtEncoder jwtEncoder;

    public JwtTokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }


    public String GenerateToken(UserDetailsImpl user) {

        var scopes = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        var claims = JwtClaimsSet.builder()
                .issuer(ISSUER)
                .issuedAt(creationAt())
                .expiresAt(expireAt())
                .subject(user.getUsername())
                .claim("authorities", scopes)
                .build();
        System.out.println("Token gerado: " + jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public Instant creationAt() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("-03:00"));
    }

    public Instant expireAt() {
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
    }
}
