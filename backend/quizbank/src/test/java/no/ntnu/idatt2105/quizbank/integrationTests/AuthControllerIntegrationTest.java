package no.ntnu.idatt2105.quizbank.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.ntnu.idatt2105.quizbank.controller.dto.LoginRequest;
import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
        User user = new User("user", passwordEncoder.encode("password"));
        userRepository.save(user);
    }

    @Test
    public void whenValidLogin_thenAuthenticateAndReturnJwt() throws Exception {
        // Her vil du opprette en LoginRequest med gyldige legitimasjonsopplysninger
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user");
        loginRequest.setPassword("password");

        // Utfører en POST-forespørsel mot /api/login og forventer et JWT-token
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.jwt").exists());
    }

    @Test
    public void whenInvalidLogin_thenUnauthorized() throws Exception {
        // Oppretter en LoginRequest med ugyldige legitimasjonsopplysninger
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user");
        loginRequest.setPassword("wrongpassword");

        // Utfører en POST-forespørsel mot /api/login og forventer en 401 Unauthorized status
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }
}


