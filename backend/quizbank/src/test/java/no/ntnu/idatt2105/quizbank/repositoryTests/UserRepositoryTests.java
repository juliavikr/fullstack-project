package no.ntnu.idatt2105.quizbank.repositoryTests;

import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.repository.UserRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;
    @Test
    public void testFindByUsername() {
        // Arrange
        User user = new User("testuser", "password");
        userRepository.saveAndFlush(user);

        // Act
        Optional<User> foundUserOptional = userRepository.findByUsername("testuser");

        // Assert
        assertThat(foundUserOptional).isPresent();
        assertThat(foundUserOptional.get().getUsername()).isEqualTo("testuser");
    }

    @Test
    public void testExistsByUsername() {
        // Arrange
        User user = new User("existinguser", "password");
        userRepository.saveAndFlush(user);

        // Act
        boolean exists = userRepository.existsByUsername("existinguser");

        // Assert
        assertThat(exists).isTrue();
    }
}

