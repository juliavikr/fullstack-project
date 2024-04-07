package no.ntnu.idatt2105.quizbank.repository;

import no.ntnu.idatt2105.quizbank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface for the repository for users
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    /**
     * Method for finding a user by its username
     * @param username The username of the user
     * @return The user
     */
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findByUsername(String username);

    /**
     * Method for checking if a user exists by its username
     * @param username The username of the user
     * @return True if the user exists, false if not
     */
    boolean existsByUsername(String username);

}
