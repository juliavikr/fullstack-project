package no.ntnu.idatt2105.quizbank.repositoryTests;

import no.ntnu.idatt2105.quizbank.model.Question;
import no.ntnu.idatt2105.quizbank.model.Quiz;
import no.ntnu.idatt2105.quizbank.repository.QuestionRepository;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@ActiveProfiles("test")
public class QuestionRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QuestionRepository questionRepository;

 @Test
public void whenPersistQuestion_thenQuizShouldNotBeNull() {
    // Opprett og persister Quiz først
    Quiz newQuiz = new Quiz();
    newQuiz.setTitle("Sample Quiz");
    entityManager.persist(newQuiz);
    entityManager.flush(); // Sørger for at det blir lagret i databasen

    // Opprett og persister Question med referanse til Quiz
    Question newQuestion = new Question("What is Java?", "Programming Language");
    newQuestion.setQuiz(newQuiz); // Knytt spørsmålet til quizen
    entityManager.persist(newQuestion);
    entityManager.flush();

    // Finn spørsmålet igjen og bekreft at quizen ikke er null
    Question foundQuestion = questionRepository.findById(newQuestion.getId()).orElse(null);
    assertNotNull(foundQuestion);
    assertNotNull(foundQuestion.getQuiz()); // Dette skal ikke være null
}


}

