package no.ntnu.idatt2105.quizbank.serviceTests;

import no.ntnu.idatt2105.quizbank.dto.UserDTO;
import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.repository.UserRepository;
import no.ntnu.idatt2105.quizbank.service.implementation.UserIMPL;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Bruk Mockito-utvidelsen for JUnit 5
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserIMPL userService;
@Test
public void shouldRegisterNewUserAccount() {
    // Opprett en UserDTO med brukernavn og passord
    UserDTO userDTO = new UserDTO("testUser", "testPass");

    // Opprett et User-objekt som representerer det som ville blitt lagret i databasen
    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    user.setId(1L); // Simuler at databasen har satt en ID for brukeren

    // Når userRepository.save blir kalt med hvilken som helst User,
    // returner brukerobjektet vi opprettet over
    when(userRepository.save(any(User.class))).thenReturn(user);

    // Utfør tjenestekallet
   String savedUserName = userService.registerNewUserAccount(userDTO);

    // Verifiser at den lagrede brukerens ID er den samme som vi satt
    assertEquals(user.getUsername(), savedUserName);
}
}

