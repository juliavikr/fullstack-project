package no.ntnu.idatt2105.quizbank.service;

import no.ntnu.idatt2105.quizbank.model.Question;
import no.ntnu.idatt2105.quizbank.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(() ->
            new RuntimeException("Question not found with id: " + id));
    }

    public Question createQuestion(Question question) {
        // Add additional logic if needed, like validation
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question updatedQuestion) {
        Question existingQuestion = getQuestionById(id);
        existingQuestion.setQuestion_text(updatedQuestion.getQuestion_text());
        existingQuestion.setAnswer(updatedQuestion.getAnswer());
        // Add additional fields to update if needed

        return questionRepository.save(existingQuestion);
    }

    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
    }
}