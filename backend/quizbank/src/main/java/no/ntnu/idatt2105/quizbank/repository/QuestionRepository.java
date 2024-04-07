package no.ntnu.idatt2105.quizbank.repository;

import java.util.List;
import no.ntnu.idatt2105.quizbank.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface for the repository for questions
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

  /**
   * Method for finding a question by its ID
   * @param id The ID of the question
   * @return The question
   */
    Optional<Question> findById(Long id);
  // Delete a question by its ID

  /**
   * Method for deleting a question by its ID
   * @param id The ID of the question
   */
    void deleteById(long id);

    /**
     * Method for finding quiz by its ID
     * @param quizId The ID of the quiz
     * @return The quiz
     */
    List<Question> findByQuizId(Long quizId);

    /**
     * Method for deleting questions by quiz ID
     * @param quizId The ID of the quiz
     */
    @Modifying
    @Query("delete from Question q where q.quiz.id = ?1")
    void deleteByQuizId(Long quizId);
}
