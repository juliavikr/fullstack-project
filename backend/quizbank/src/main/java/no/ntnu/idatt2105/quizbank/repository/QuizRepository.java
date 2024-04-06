package no.ntnu.idatt2105.quizbank.repository;

import java.util.List;
import java.util.Optional;
import no.ntnu.idatt2105.quizbank.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByOwnerId(Long ownerId);

    List<Quiz> findByCategory(String category);

    // Custom method to fetch all quizzes with their questions
    @Query("SELECT q FROM Quiz q JOIN FETCH q.questions")
    List<Quiz> findAllQuizzesWithQuestions();

    // Custom method to fetch a specific quiz with its questions
    @Query("SELECT q FROM Quiz q JOIN FETCH q.questions WHERE q.id = :id")
    Optional<Quiz> findQuizWithQuestionsById(Long id);

    void deleteQuizWithQuestionsById(@Param("id") Long id);

}

