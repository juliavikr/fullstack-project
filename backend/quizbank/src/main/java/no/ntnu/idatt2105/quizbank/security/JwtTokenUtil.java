package no.ntnu.idatt2105.quizbank.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

/**
 * Class for handling JWT tokens
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@Component
public class JwtTokenUtil {

    private final SecretKey key;
    public static final long JWT_TOKEN_VALIDITY = 3600000;

    /**
     * Constructor for JwtTokenUtil
     */
    public JwtTokenUtil() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    /**
     * Method for generating a token
     * @param userDetails The user details
     * @return The token
     */
    public String generateToken(UserDetails userDetails) {
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("authorities", userDetails.getAuthorities());

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();
    }

    /**
     * Method for validating a token
     * @param token The token
     * @param userDetails The user details
     * @return True if the token is valid, false if not
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Method for retrieving the username from a token
     * @param token The token
     * @return The username of the token
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Method for checking if a token has expired
     * @param token The token
     * @return True if the token has expired, false if not
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Method for retrieving the expiration date from a token
     * @param token The token
     * @return The expiration date of the token
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Method for retrieving a claim from a token
     * @param token The token
     * @param claimsResolver The claims resolver
     * @param <T> The type of the claim
     * @return The claim
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Method for retrieving all claims from a token
     * @param token The token
     * @return All claims from the token
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}



