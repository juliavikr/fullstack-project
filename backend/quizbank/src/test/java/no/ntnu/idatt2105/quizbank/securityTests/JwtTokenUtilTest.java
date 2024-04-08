package no.ntnu.idatt2105.quizbank.securityTests;

import no.ntnu.idatt2105.quizbank.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class for testing JwtTokenUtil
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
public class JwtTokenUtilTest {

    private JwtTokenUtil jwtTokenUtil;

    /**
     * Method that sets up the test environment
     */
    @BeforeEach
    public void setup() {
        jwtTokenUtil = new JwtTokenUtil();
    }

    /**
     * Method for testing generating token
     */
    @Test
    public void givenUser_whenGenerateToken_thenCorrect() {
        // Arrange
        UserDetails userDetails = new User("user", "password", Collections.emptyList());

        // Act
        String token = jwtTokenUtil.generateToken(userDetails);

        // Assert
        assertThat(token).isNotEmpty();
    }

    /**
     * Method for testing getting username from token
     */
    @Test
    public void givenToken_whenGetUsernameFromToken_thenCorrect() {
        // Arrange
        UserDetails userDetails = new User("user", "password", Collections.emptyList());
        String token = jwtTokenUtil.generateToken(userDetails);

        // Act
        String username = jwtTokenUtil.getUsernameFromToken(token);

        // Assert
        assertThat(username).isEqualTo(userDetails.getUsername());
    }

    /**
     * Method for testing getting expiration date from token
     */
    @Test
    public void givenToken_whenValidateToken_thenCorrect() {
        // Arrange
        UserDetails userDetails = new User("user", "password", Collections.emptyList());
        String token = jwtTokenUtil.generateToken(userDetails);

        // Act
        boolean isValid = jwtTokenUtil.validateToken(token, userDetails);

        // Assert
        assertThat(isValid).isTrue();
    }

    /**
     * Method for testing given expired token
     */
    @Test
    public void givenExpiredToken_whenValidateToken_thenFalse() {
        // Arrange
        UserDetails userDetails = new User("user", "password", Collections.emptyList());
        String token = jwtTokenUtil.generateToken(userDetails);

    }
}
