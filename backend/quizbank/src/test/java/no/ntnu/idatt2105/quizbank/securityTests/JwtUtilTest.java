package no.ntnu.idatt2105.quizbank.securityTests;

import no.ntnu.idatt2105.quizbank.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test for {@link JwtUtil} class for testing the generation and extraction of JWT tokens
 * uses a mock authentication object for testing purposes
 */
@SpringBootTest
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Test {@link JwtUtil#generateToken(Authentication)} for a scenario where the token is generated successfully
     * creates a user with a username and password
     * creates an authentication with the created user and generates a token
     * the test verifies that the token is not null
     */
    @Test
    public void whenGenerateToken_thenSuccess() {
        // Sett opp en bruker med brukernavn og passord
        UserDetails userDetails = User.builder()
                .username("user")
                .password("password")
                .authorities("ROLE_USER")
                .build();

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        String token = jwtUtil.generateToken(authentication);

        assertNotNull(token);
    }

    /**
     * Test {@link JwtUtil#extractUsername(String)} for a scenario where the username is extracted successfully
     * creates an authentication with a username
     * generates a token with the authentication
     * the test verifies that the extracted username is the same as the username in the authentication
     */
    @Test
    public void whenParseToken_thenGetCorrectUsername() {
        // Sett opp en bruker med brukernavn og passord
        UserDetails userDetails = User.builder()
                .username("user")
                .password("password")
                .authorities("ROLE_USER")
                .build();

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());


        String token = jwtUtil.generateToken(authentication);

        String username = jwtUtil.extractUsername(token);

        assertEquals("user", username);
    }
}
