package no.ntnu.idatt2105.quizbank.service;

import no.ntnu.idatt2105.quizbank.model.Question;
import no.ntnu.idatt2105.quizbank.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Question
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    /**
     * Constructor for QuestionService
     * @param questionRepository
     */
    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Method for getting all questions
     * @return The questions in the database
     */
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    /**
     * Method for getting a question by its ID
     * @param id The ID of the question
     * @return The question with the given ID
     */
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(() ->
            new RuntimeException("Question not found with id: " + id));
    }

    /**
     * Method for creating a question
     * @param question The question to be created
     * @return The created question
     */
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    /**
     * Method for updating a question
     * @param id The ID of the question
     * @param updatedQuestion The updated question
     * @return The updated question
     */
    public Question updateQuestion(Long id, Question updatedQuestion) {
        Question existingQuestion = getQuestionById(id);
        existingQuestion.setQuestion_text(updatedQuestion.getQuestion_text());
        existingQuestion.setAnswer(updatedQuestion.getAnswer());

        return questionRepository.save(existingQuestion);
    }

    /**
     * Method for deleting a question by its ID
     * @param id The ID of the question
     */
    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
    }
}