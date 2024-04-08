package no.ntnu.idatt2105.quizbank.ControllerTests;
import no.ntnu.idatt2105.quizbank.model.Question;
import no.ntnu.idatt2105.quizbank.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class for testing QuestionController
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    private Question question1;
    private Question question2;

    /**
     * Method that sets up the test environment
     */
    @BeforeEach
    public void setup() {
        question1 = new Question();
        question1.setId(1L);
        question1.setQuestion_text("Question 1");

        question2 = new Question();
        question2.setId(2L);
        question2.setQuestion_text("Question 2");
    }

    /**
     * Method for testing get all questions
     * @throws Exception if the test fails
     */
    @Test
    @WithMockUser
    public void getAllQuestionsTest() throws Exception {
        List<Question> questions = Arrays.asList(question1, question2);
        when(questionService.getAllQuestions()).thenReturn(questions);

        mockMvc.perform(get("/questions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Method for testing get question by ID
     * @throws Exception if the test fails
     */
  @Test
  @WithMockUser
public void getQuestionByIdTest() throws Exception {
    when(questionService.getQuestionById(1L)).thenReturn(question1);

    mockMvc.perform(get("/questions/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}