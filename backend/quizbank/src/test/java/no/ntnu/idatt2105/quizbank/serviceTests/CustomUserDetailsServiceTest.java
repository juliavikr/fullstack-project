package no.ntnu.idatt2105.quizbank.serviceTests;

/*import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.repository.UserRepository;
import no.ntnu.idatt2105.quizbank.service.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class for testing {@link CustomUserDetailsService}
 * This class uses Mockito to mock the UserRepository for testing purposes so that the tests can be run without a database
 */
/*

class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customUserDetailsService = new CustomUserDetailsService(userRepository);
    }

    /**
     * Test {@link CustomUserDetailsService#loadUserByUsername(String)} for a scenario where the username is valid
     * A user with the username "validUser" is created and added to the mock UserRepository
     * The method loadUserByUsername is then called with the username "validUser"
     * The method should return a UserDetails object with the same username as the mockUser
     */
/*
    @Test
    void whenValidUsername_thenUserShouldBeFound() {
        // Arrange
        User mockUser = new User("validUser", "password");
        mockUser.setId(1L);
        when(userRepository.findByUsername("validUser")).thenReturn(java.util.Optional.of(mockUser));

        // Act
        UserDetails foundUser = customUserDetailsService.loadUserByUsername("validUser");

        // Assert
        assertNotNull(foundUser);
        assertEquals(mockUser.getUsername(), foundUser.getUsername());
        verify(userRepository).findByUsername(anyString());
    }

    /**
     * Test {@link CustomUserDetailsService#loadUserByUsername(String)} for a scenario where the username is invalid
     * The method loadUserByUsername is called with the username "invalidUser" that is not in the mock UserRepository
     * The method should throw a UsernameNotFoundException
     */
/*
    @Test
    void whenInvalidUsername_thenThrowUsernameNotFoundException() {
        // Arrange
        when(userRepository.findByUsername("invalidUser")).thenThrow(new UsernameNotFoundException("User not found"));

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("invalidUser");
        });
    }
}*/
