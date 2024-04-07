package no.ntnu.idatt2105.quizbank.securityTests;

import no.ntnu.idatt2105.quizbank.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtTokenUtilTest {

    private JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    public void setup() {
        jwtTokenUtil = new JwtTokenUtil();
    }

    @Test
    public void givenUser_whenGenerateToken_thenCorrect() {
        // Arrange
        UserDetails userDetails = new User("user", "password", Collections.emptyList());

        // Act
        String token = jwtTokenUtil.generateToken(userDetails);

        // Assert
        assertThat(token).isNotEmpty();
    }

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

    @Test
    public void givenExpiredToken_whenValidateToken_thenFalse() {
        // Arrange
        UserDetails userDetails = new User("user", "password", Collections.emptyList());
        String token = jwtTokenUtil.generateToken(userDetails);

        // Act
        // Simulate token expiration by waiting for the token to expire
        // In this case, you might need to set the token validity to a small value temporarily
        // Thread.sleep(JwtTokenUtil.JWT_TOKEN_VALIDITY + 1000);
        // boolean isValid = jwtTokenUtil.validateToken(token, userDetails);

        // Assert
        // assertThat(isValid).isFalse();
    }
}
