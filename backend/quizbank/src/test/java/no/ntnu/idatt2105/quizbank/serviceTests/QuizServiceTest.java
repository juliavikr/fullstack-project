package no.ntnu.idatt2105.quizbank.serviceTests;
import no.ntnu.idatt2105.quizbank.dto.QuestionDto;
import no.ntnu.idatt2105.quizbank.dto.QuizDto;
import no.ntnu.idatt2105.quizbank.model.Quiz;
import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.repository.QuestionRepository;
import no.ntnu.idatt2105.quizbank.repository.QuizRepository;
import no.ntnu.idatt2105.quizbank.service.QuizService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuizService quizService;

    @Test
    public void whenCreateQuiz_thenQuizIsSaved() {
        User owner = new User();
        owner.setId(1L);

        QuizDto quizDto = new QuizDto("Quiz Title", "Category", "Difficulty", new ArrayList<>());
        Quiz quiz = new Quiz();
        quiz.setTitle("Quiz Title");
        quiz.setCategory("Category");
        quiz.setDifficulty("Difficulty");
        quiz.setOwner(owner);

        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);

        Quiz createdQuiz = quizService.createQuiz(quizDto, owner);

        assertEquals("Quiz Title", createdQuiz.getTitle());
        assertEquals("Category", createdQuiz.getCategory());
        assertEquals("Difficulty", createdQuiz.getDifficulty());
        assertEquals(owner, createdQuiz.getOwner());
    }
    @Test
    public void whenGetAllQuizzes_thenQuizzesReturned() {
        List<Quiz> expectedQuizzes = new ArrayList<>();
        expectedQuizzes.add(new Quiz());
        expectedQuizzes.add(new Quiz());

        when(quizRepository.findAll()).thenReturn(expectedQuizzes);

        List<Quiz> quizzes = quizService.getAllQuizzes();

        assertEquals(2, quizzes.size());
    }
    @Test
    public void whenGetQuizById_thenQuizReturned() {
        Quiz quiz = new Quiz();
        when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));

        Quiz found = quizService.getQuizById(1L);

        assertEquals(quiz, found);
    }

    @Test
    public void whenDeleteQuizById_thenRepositoryDeleteCalled() {
        Long quizId = 1L;
        doNothing().when(quizRepository).deleteById(quizId);

        quizService.deleteQuizById(quizId);

        verify(quizRepository, times(1)).deleteById(quizId);
    }

    @Test
    public void whenUpdateQuiz_thenQuizUpdated() {
        Long quizId = 1L;
        Quiz existingQuiz = new Quiz();
        QuizDto quizDto = new QuizDto("Updated Title", "Updated Category", "Updated Difficulty", new ArrayList<>());

        when(quizRepository.findById(quizId)).thenReturn(Optional.of(existingQuiz));
        when(quizRepository.save(any(Quiz.class))).thenReturn(existingQuiz);

        Quiz updated = quizService.updateQuiz(quizId, quizDto);

        assertEquals("Updated Title", updated.getTitle());
        assertEquals("Updated Category", updated.getCategory());
        assertEquals("Updated Difficulty", updated.getDifficulty());
    }

    @Test
    public void whenGetQuizzesByCategory_thenQuizzesReturned() {
        List<Quiz> expectedQuizzes = new ArrayList<>();
        expectedQuizzes.add(new Quiz());
        expectedQuizzes.add(new Quiz());

        when(quizRepository.findByCategory("Category")).thenReturn(expectedQuizzes);

        List<Quiz> quizzes = quizService.getQuizzesByCategory("Category");

        assertEquals(2, quizzes.size());
    }

    @Test
    public void whenGetQuizzesByUser_thenQuizzesReturned() {
        List<Quiz> expectedQuizzes = new ArrayList<>();
        expectedQuizzes.add(new Quiz());
        expectedQuizzes.add(new Quiz());

        when(quizRepository.findByOwnerId(1L)).thenReturn(expectedQuizzes);

        List<Quiz> quizzes = quizService.getQuizzesByUser(1L);

        assertEquals(2, quizzes.size());
    }
    @Test
    public void whenCreateQuiz_thenQuestionsAreCreatedAndAssigned() {
    User owner = new User();
    owner.setId(1L);

    List<QuestionDto> questionDtos = Arrays.asList(
        new QuestionDto("Question 1", "Answer 1", null),
        new QuestionDto("Question 2", "Answer 2", null)
    );
    QuizDto quizDto = new QuizDto("Quiz Title", "Category", "Difficulty", questionDtos);

    when(quizRepository.save(any(Quiz.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Quiz createdQuiz = quizService.createQuiz(quizDto, owner);

    assertEquals("Quiz Title", createdQuiz.getTitle());
    assertEquals(2, createdQuiz.getQuestions().size());
    assertEquals("Question 1", createdQuiz.getQuestions().get(0).getQuestion_text());
    assertEquals("Question 2", createdQuiz.getQuestions().get(1).getQuestion_text());
}
    @Test
public void whenUpdateQuiz_thenQuestionsAreUpdated() {
    Long quizId = 1L;
    Quiz existingQuiz = new Quiz();
    existingQuiz.setId(quizId);
    existingQuiz.setQuestions(new ArrayList<>());

    List<QuestionDto> questionDtos = Arrays.asList(
        new QuestionDto("Updated Question 1", "Updated Answer 1", null),
        new QuestionDto("Updated Question 2", "Updated Answer 2", null)
    );
    QuizDto quizDto = new QuizDto("Updated Title", "Updated Category", "Updated Difficulty", questionDtos);

    when(quizRepository.findById(quizId)).thenReturn(Optional.of(existingQuiz));
    when(quizRepository.save(any(Quiz.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Quiz updatedQuiz = quizService.updateQuiz(quizId, quizDto);

    assertEquals("Updated Title", updatedQuiz.getTitle());
    assertEquals(2, updatedQuiz.getQuestions().size());
    assertEquals("Updated Question 1", updatedQuiz.getQuestions().get(0).getQuestion_text());
    assertEquals("Updated Question 2", updatedQuiz.getQuestions().get(1).getQuestion_text());
}



}
