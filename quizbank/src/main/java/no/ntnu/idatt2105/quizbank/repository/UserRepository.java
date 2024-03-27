package no.ntnu.idatt2105.quizbank.repository;

import no.ntnu.idatt2105.quizbank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User for retrieving and accessing User entities from the database
 * Extends JpaRepository to get access to CRUD methods
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    /**
     * Method for finding a user by username
     * @param username Username of the user to find
     * @return Optional of User containing the user if found, empty Optional otherwise
     */
    Optional<User> findByUsername(String username);
}
