package no.ntnu.idatt2105.quizbank.repositoryTests;
import no.ntnu.idatt2105.quizbank.model.Quiz;
import no.ntnu.idatt2105.quizbank.repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class QuizRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QuizRepository quizRepository;

    @Test
    public void whenFindByCategory_thenQuizzesReturned() {
        // Sett opp data
        Quiz quiz1 = createQuiz("Quiz 1", "Science", "Medium");
        entityManager.persist(quiz1);

        Quiz quiz2 = createQuiz("Quiz 2", "Science", "Hard");
        entityManager.persist(quiz2);

        entityManager.flush();

        // Utfør handlingen
        List<Quiz> quizzes = quizRepository.findByCategory("Science");

        // Verifiser resultatet
        assertEquals(2, quizzes.size());
        assertTrue(quizzes.stream().allMatch(quiz -> quiz.getCategory().equals("Science")));
    }

    private Quiz createQuiz(String title, String category, String difficulty) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setCategory(category);
        quiz.setDifficulty(difficulty);
        return quiz;
    }
}





