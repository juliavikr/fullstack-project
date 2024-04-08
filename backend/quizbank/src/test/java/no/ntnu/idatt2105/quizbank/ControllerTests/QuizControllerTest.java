package no.ntnu.idatt2105.quizbank.ControllerTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import no.ntnu.idatt2105.quizbank.dto.QuizDto;
import no.ntnu.idatt2105.quizbank.model.Quiz;
import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuizService quizService;

    private List<Quiz> quizList;


    @BeforeEach
    public void setup() {
        Quiz quiz1 = new Quiz();
        quiz1.setId(1L);
        quiz1.setTitle("Quiz 1");
        quiz1.setCategory("Category 1");
        quiz1.setDifficulty("Easy");

        Quiz quiz2 = new Quiz();
        quiz2.setId(2L);
        quiz2.setTitle("Quiz 2");
        quiz2.setCategory("Category 2");
        quiz2.setDifficulty("Medium");

        quizList = Arrays.asList(quiz1, quiz2);
    }

@Test
public void updateQuizTest() throws Exception {
    Long id = 1L;
    Quiz quiz = new Quiz();
    quiz.setId(id);

    // Mock the authentication principal
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("testPassword");
    Authentication auth = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    SecurityContextHolder.getContext().setAuthentication(auth);

    when(quizService.updateQuiz(eq(id), any(QuizDto.class))).thenReturn(quiz);

    mockMvc.perform(put("/quiz/" + id)
            .with(user(user.getUsername())) // Add this line to mock user authentication
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"Updated Quiz\",\"category\":\"Updated Category\",\"difficulty\":\"Updated Difficulty\"}"))
            .andExpect(status().isOk())
            .andExpect(content().json("{'id': 1}"));
}

  @Test
public void deleteQuizByIdTest() throws Exception {
    Long id = 1L;

    // Mock the authentication principal
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("testPassword");
    Authentication auth = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    SecurityContextHolder.getContext().setAuthentication(auth);

    doNothing().when(quizService).deleteQuizById(id);

    mockMvc.perform(delete("/quiz/" + id)
            .with(user(user.getUsername())) // Add this line to mock user authentication
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

    verify(quizService, times(1)).deleteQuizById(id);
}
}