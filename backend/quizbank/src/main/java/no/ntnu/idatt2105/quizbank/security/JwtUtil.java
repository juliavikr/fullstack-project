package no.ntnu.idatt2105.quizbank.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * This class is responsible for generating and verifying JWT tokens
 */
@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long tokenValidity;

    public String generateToken(Authentication authentication) {
        if (authentication.getPrincipal() instanceof User) {
            User principal = (User) authentication.getPrincipal();
            return JWT.create()
                    .withSubject(principal.getUsername())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + tokenValidity))
                    .sign(Algorithm.HMAC256(secretKey));
        } else {
            throw new IllegalArgumentException("authentication object is not of type User");
        }
    }

    public DecodedJWT verifyToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        return verifier.verify(token);
    }

    public String extractUsername(String token) {
        DecodedJWT jwt = verifyToken(token);
        return jwt.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            DecodedJWT jwt = verifyToken(token);
            return jwt.getExpiresAt().after(new Date());
        } catch (Exception e) {
            // Log og håndter unntak etter behov
        }
        return false;
    }
}
