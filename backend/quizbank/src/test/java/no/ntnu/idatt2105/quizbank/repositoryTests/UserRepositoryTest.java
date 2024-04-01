package no.ntnu.idatt2105.quizbank.repositoryTests;

import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link UserRepository}
 * The database that is used is an in-memory database for testing purposes
 */
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    /**
     * Test {@link UserRepository#findByUsername(String)} for a scenario where the requested user is exists in the database
     * Given a user Andramun is saved in the database
     * When the metyhod findByUsername is called with the username Andramun
     * The returned value should be an Optional containing the user Andramun
     */
    @Test
    public void whenFindByUsername_thenReturnUser() {
        // given
        User alex = new User("Andramun", "password123");
        userRepository.save(alex);

        // when
        Optional<User> found = userRepository.findByUsername(alex.getUsername());

        // then
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getUsername()).isEqualTo(alex.getUsername());
    }

    /**
     * Test {@link UserRepository#findByUsername(String)} for a scenario where the requested user does not exist in the database
     * Given a user "unknown_user" that is not saved in the database
     * When the method findByUsername is called with the username unknown_user
     * The returned value should be an empty Optional and the user should not be found
     */
    @Test
    public void whenFindByUsername_thenReturnEmpty() {
        // given
        String username = "unknown_user";

        // when
        Optional<User> found = userRepository.findByUsername(username);

        // then
        assertThat(found.isPresent()).isFalse();
    }
}


