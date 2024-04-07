package no.ntnu.idatt2105.quizbank.repository;

import java.util.List;
import java.util.Optional;
import no.ntnu.idatt2105.quizbank.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface for the repository for quizzes
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    /**
     * Method for finding quizzes by owner ID
     * @param ownerId
     * @return The quizzes
     */
    List<Quiz> findByOwnerId(Long ownerId);

    /**
     * Method for finding quizzes by category
     * @param category
     * @return The quizzes
     */
    List<Quiz> findByCategory(String category);

    /**
     * Method for finding all quizzes with questions
     * @return The quizzes with questions
     */
    @Query("SELECT q FROM Quiz q JOIN FETCH q.questions")
    List<Quiz> findAllQuizzesWithQuestions();

    /**
     * Method for finding a quiz with questions by its ID
     * @param id The ID of the quiz
     * @return The quiz with questions
     */
    @Query("SELECT q FROM Quiz q JOIN FETCH q.questions WHERE q.id = :id")
    Optional<Quiz> findQuizWithQuestionsById(Long id);

    /**
     * Method for deleting a quiz with questions by its ID
     * @param id The ID of the quiz
     */
    void deleteQuizWithQuestionsById(@Param("id") Long id);

}

