package br.com.malldelivery.lojista.seguranca.authentication;

import br.com.malldelivery.lojista.seguranca.userdetails.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {
    private static final String SECRET_KEY = "1234567899876543210ab";
    private static final String ISSUER = "mall-delivery-api";

    public String generateToken(UserDetailsImpl user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(user.getUsuario().getUsername())
                    .sign(algorithm);
        }
        catch (JWTCreationException exception) {
            throw new JWTCreationException("Erro ao gerar o token", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                      .withIssuer(ISSUER)
                      .build()
                      .verify(token)
                      .getSubject();
        }
        catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token invalido", exception);
        }
    }

    public Instant creationDate() {
        return ZonedDateTime.now().toInstant();
    }

    public Instant expirationDate() {
        return ZonedDateTime.now().plusHours(2).toInstant();
    }
}
