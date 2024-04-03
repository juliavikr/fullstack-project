package no.ntnu.idatt2105.quizbank.integrationTests;

import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class DatabaseIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testDatabaseConnection() {
        // Test database connection by performing a simple database operation
        User user = new User("testuserxx", "password");
        userRepository.save(user);

        /*
        assertNotNull(user.getId()); // Ensure that the user has been assigned an ID after saving
         // Retrieve the user by username
        Optional<User> optionalUser = userRepository.findByUsername("testuser");

        assertTrue(optionalUser.isPresent()); // Check if the user is present

        // Get the user from the optional and check its username
        User retrievedUser = optionalUser.get();
        assertEquals("testuser", retrievedUser.getUsername());
        */
    }
}
