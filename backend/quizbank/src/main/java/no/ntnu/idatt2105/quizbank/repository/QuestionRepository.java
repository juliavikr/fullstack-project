package no.ntnu.idatt2105.quizbank.repository;

import no.ntnu.idatt2105.quizbank.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
  // Find a question by its ID
    Optional<Question> findById(Long id);

  // Delete a question by its ID
  void deleteById(long id);
}
