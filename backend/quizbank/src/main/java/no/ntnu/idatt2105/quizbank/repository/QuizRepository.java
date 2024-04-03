package no.ntnu.idatt2105.quizbank.repository;

import java.util.List;
import no.ntnu.idatt2105.quizbank.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    // finder methods for Quiz
    List<Quiz> findByCategory(String category);

}

