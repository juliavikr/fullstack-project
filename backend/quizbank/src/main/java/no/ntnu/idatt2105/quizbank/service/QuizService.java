package no.ntnu.idatt2105.quizbank.service;

import jakarta.persistence.EntityManager;
import no.ntnu.idatt2105.quizbank.model.User;
import org.springframework.transaction.annotation.Transactional;
import no.ntnu.idatt2105.quizbank.dto.QuizDto;
import no.ntnu.idatt2105.quizbank.model.Question;
import no.ntnu.idatt2105.quizbank.model.Quiz;
import no.ntnu.idatt2105.quizbank.repository.QuestionRepository;
import no.ntnu.idatt2105.quizbank.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Quiz
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    @Autowired
    private EntityManager entityManager;

    /**
     * Constructor for QuizService
     * @param quizRepository
     * @param questionRepository
     */
    @Autowired
    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    /**
     * Method for creating a quiz
     * @param quizDto The quiz to be created
     * @param owner The owner of the quiz
     * @return The created quiz
     */
    @Transactional
    public Quiz createQuiz(QuizDto quizDto, User owner) {
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setCategory(quizDto.getCategory());
        quiz.setDifficulty(quizDto.getDifficulty());
        quiz.setOwner(owner);

        List<Question> questions = quizDto.getQuestions().stream().map(questionDto -> {
            Question question = new Question();
            question.setQuestion_text(questionDto.getQuestion_text());
            question.setAnswer(questionDto.getAnswer());
            question.setQuiz(quiz);
            return question;
        }).collect(Collectors.toList());

        quiz.setQuestions(questions);
        return quizRepository.save(quiz);
    }

    /**
     * Method for getting all quizzes
     * @return The quizzes in the database
     */
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    /**
     * Method for getting a quiz by its ID
     * @param id The ID of the quiz
     * @return The quiz with the given ID
     */
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElseThrow(() ->
            new RuntimeException("Quiz not found with id: " + id));
    }

    /**
     * Method for updating a quiz
     * @param id The ID of the quiz
     * @param quizDto The updated quiz
     * @return The updated quiz
     */
    public Quiz updateQuiz(Long id, QuizDto quizDto) {
        Quiz existingQuiz = getQuizById(id);
        existingQuiz.setTitle(quizDto.getTitle());
        existingQuiz.setCategory(quizDto.getCategory());
        existingQuiz.setDifficulty(quizDto.getDifficulty());

        List<Question> updatedQuestions = quizDto.getQuestions().stream().map(questionDto -> {
            Question question = new Question();
            question.setQuestion_text(questionDto.getQuestion_text());
            question.setAnswer(questionDto.getAnswer());
            question.setQuiz(existingQuiz);
            return question;
        }).collect(Collectors.toList());

        existingQuiz.setQuestions(updatedQuestions);
        return quizRepository.save(existingQuiz);
    }

    /**
     * Method for getting all quizzes with questions
     * @return The quizzes with questions
     */
    public List<Quiz> getQuizzesByCategory(String category) {
        return quizRepository.findByCategory(category);
    }

    /**
     * Method for getting all quizzes with questions
     * @return The quizzes with questions
     */
    public List<Quiz> getQuizzesByUser(Long userId) {
        return quizRepository.findByOwnerId(userId);
    }

    /**
     * Method for deleting a quiz by its ID
     * @param quizId The ID of the quiz
     */
    @Transactional
    public void deleteQuizById(Long quizId) {
        // Delete questions associated with the quiz
        entityManager.createNativeQuery("DELETE FROM questions WHERE quiz_id = :quizId")
            .setParameter("quizId", quizId)
            .executeUpdate();

        // Delete the quiz itself
        entityManager.createNativeQuery("DELETE FROM quizzes WHERE id = :quizId")
            .setParameter("quizId", quizId)
            .executeUpdate();

        // Ensure all changes are immediately synchronized with the database
        entityManager.flush();
    }
}
