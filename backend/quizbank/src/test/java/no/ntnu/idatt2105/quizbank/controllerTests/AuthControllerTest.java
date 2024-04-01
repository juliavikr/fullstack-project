package no.ntnu.idatt2105.quizbank.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.ntnu.idatt2105.quizbank.controller.AuthController;
import no.ntnu.idatt2105.quizbank.controller.dto.LoginRequest;
import no.ntnu.idatt2105.quizbank.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for testing the {@link AuthController} class and the interaction between AuthenticationManager and JwtUtil
 * This class uses Mockito to mock the AuthenticationManager and JwtUtil for testing purposes so that the tests can be run without a database
 * the test simulates an actual HTTP request to the controller and checks if the response is as expected
 */
@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtil jwtUtil;

    /**
     * Tests successful authentication process.
     * Given valid user credentials,
     * when POST request to '/api/login' is made,
     * then returns an OK status with a JWT token in the response.
     */
    @Test
    public void whenValidUserCredentials_thenAuthenticateAndReturnJwt() throws Exception {
        // Prepare the login request
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user");
        loginRequest.setPassword("password");

        // Prepare the authentication token
        Authentication auth = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        // Mock the authentication manager to authenticate the token
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(auth);

        // Mock JwtUtil to return a JWT when requested
        String expectedJwt = "some.jwt.token";
        when(jwtUtil.generateToken(auth)).thenReturn(expectedJwt);

        // Perform the login request and assert the results
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").value(expectedJwt));
    }

    /**
     * Tests failed authentication process.
     * Given invalid user credentials,
     * when POST request to '/api/login' is made,
     * then returns UNAUTHORIZED status.
     */
    @Test
    public void whenInvalidUserCredentials_thenUnauthorized() throws Exception {
        // Prepare the login request with invalid credentials
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user");
        loginRequest.setPassword("wrongpassword");

        // Mock the authentication manager to throw an exception for invalid credentials
        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        // Perform the login request and assert the results
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }
}

