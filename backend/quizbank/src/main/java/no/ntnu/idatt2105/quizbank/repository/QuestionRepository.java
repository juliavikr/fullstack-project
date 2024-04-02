package no.ntnu.idatt2105.quizbank.repository;

import no.ntnu.idatt2105.quizbank.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
