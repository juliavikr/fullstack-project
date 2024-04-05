package no.ntnu.idatt2105.quizbank.serviceTests;
import no.ntnu.idatt2105.quizbank.dto.LoginDTO;
import no.ntnu.idatt2105.quizbank.dto.UserDTO;
import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.repository.UserRepository;
import no.ntnu.idatt2105.quizbank.response.LoginResponse;
import no.ntnu.idatt2105.quizbank.security.JwtTokenUtil;
import no.ntnu.idatt2105.quizbank.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

  @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Test
    public void registerNewUserAccount_WhenUsernameExists_ThrowsException() {
        UserDTO userDTO = new UserDTO("existingUser", "password");
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerNewUserAccount(userDTO));

        assertEquals("Username is already taken", exception.getMessage());
    }

    @Test
    public void registerNewUserAccount_WhenUsernameNotExists_ReturnsUsername() {
        UserDTO userDTO = new UserDTO("newUser", "password");
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        String returnedUsername = userService.registerNewUserAccount(userDTO);

        assertEquals(userDTO.getUsername(), returnedUsername);
    }

    @Test
    public void loginUser_WhenCredentialsAreCorrect_ReturnsLoginResponse() {
    String username = "user";
    String password = "password";
    User mockUser = new User();
    mockUser.setUsername(username);
    mockUser.setPassword(passwordEncoder.encode(password));

    when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
    when(passwordEncoder.matches(password, mockUser.getPassword())).thenReturn(true);
    when(jwtTokenUtil.generateToken(mockUser)).thenReturn("token");

    LoginDTO loginDTO = new LoginDTO(username, password);
    LoginResponse loginResponse = userService.loginUser(loginDTO);

    assertTrue(loginResponse.getSuccess());
    assertEquals("token", loginResponse.getToken());
}

    // Flere tester etter behov ...
}


