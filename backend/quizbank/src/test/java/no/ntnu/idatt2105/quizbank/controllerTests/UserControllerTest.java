package no.ntnu.idatt2105.quizbank.controllerTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import no.ntnu.idatt2105.quizbank.config.PasswordEncoderConfig;
import no.ntnu.idatt2105.quizbank.config.WebSecurityConfig;
import no.ntnu.idatt2105.quizbank.controller.UserController;
import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.service.CustomUserDetailsService;
import no.ntnu.idatt2105.quizbank.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import({WebSecurityConfig.class, PasswordEncoderConfig.class})
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    private User user;

    @BeforeEach
    public void setup() {
        user = new User("testUser", "password");

    }

    /**
     * Test {@link UserController#registerUser(User)}
     * the test that a POST request to /api/user/register endpoint
     * with a new user object returns status OK
     *
     * @throws Exception if the request fails
     */
    @Test
    @WithMockUser
    public void whenRegisterUser_thenReturns200() throws Exception {
        given(userService.registerNewUserAccount(user)).willReturn(user);

        mockMvc.perform(post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    /**
     * Test {@link UserController#loginUser(User)}
     * the test checks that a POST request to /api/user/login endpoint with a user object
     * where the username and password matches an existing user in the database returns status OK
     * the test does not check encoding of password
     *
     * @throws Exception if the request fails
     */
    @Test
    @WithMockUser
   public void whenLoginUser_thenReturns200() throws Exception {
       String username = "testUser";
    String rawPassword = "rawPassword";
    String encodedPassword = "encodedPassword";

    User user = new User(username, encodedPassword);

    given(userService.findByUsername(username)).willReturn(Optional.of(user));
    given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);

    String jsonBody = "{\"username\":\"" + username + "\",\"password\":\"" + rawPassword + "\"}";

    mockMvc.perform(post("/api/user/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonBody))
            .andExpect(status().isOk());
    }
}