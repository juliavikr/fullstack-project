package no.ntnu.idatt2105.quizbank.serviceTests;
import no.ntnu.idatt2105.quizbank.model.Question;
import no.ntnu.idatt2105.quizbank.repository.QuestionRepository;
import no.ntnu.idatt2105.quizbank.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    @Test
    public void whenGetAllQuestions_thenQuestionListReturned() {
        Question question1 = new Question();
        question1.setQuestion_text("Question 1");
        Question question2 = new Question();
        question2.setQuestion_text("Question 2");

        when(questionRepository.findAll()).thenReturn(Arrays.asList(question1, question2));

        List<Question> questions = questionService.getAllQuestions();

        assertEquals(2, questions.size());
        assertEquals("Question 1", questions.get(0).getQuestion_text());
        assertEquals("Question 2", questions.get(1).getQuestion_text());
    }

   @Test
    public void whenGetQuestionById_thenQuestionReturned() {
    Question question = new Question();
    question.setId(1L);
    question.setQuestion_text("Test Question");

    when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

    Question found = questionService.getQuestionById(1L);

    assertEquals("Test Question", found.getQuestion_text());
}

    @Test
    public void whenCreateQuestion_thenQuestionIsSaved() {
        Question question = new Question();
        question.setQuestion_text("New Question");

        when(questionRepository.save(any(Question.class))).thenReturn(question);

        Question created = questionService.createQuestion(question);

        assertEquals("New Question", created.getQuestion_text());
    }

    @Test
    public void whenUpdateQuestion_thenQuestionIsUpdated() {
        Question existingQuestion = new Question();
        existingQuestion.setId(1L);
        existingQuestion.setQuestion_text("Old Question");

        Question updatedQuestion = new Question();
        updatedQuestion.setQuestion_text("Updated Question");

        when(questionRepository.findById(1L)).thenReturn(Optional.of(existingQuestion));
        when(questionRepository.save(any(Question.class))).thenReturn(updatedQuestion);


        Question updated = questionService.updateQuestion(1L, updatedQuestion);

        assertEquals("Updated Question", updated.getQuestion_text());
    }

    @Test
    public void whenDeleteQuestionById_thenRepositoryDeleteIsCalled() {
        Long questionId = 1L;

        questionService.deleteQuestionById(questionId);

        verify(questionRepository, times(1)).deleteById(questionId);
    }
}


