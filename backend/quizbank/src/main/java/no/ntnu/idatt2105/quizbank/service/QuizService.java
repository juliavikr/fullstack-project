package no.ntnu.idatt2105.quizbank.service;

import no.ntnu.idatt2105.quizbank.controller.dto.QuizDto;
import no.ntnu.idatt2105.quizbank.model.Question;
import no.ntnu.idatt2105.quizbank.model.Quiz;
import no.ntnu.idatt2105.quizbank.repository.QuestionRepository;
import no.ntnu.idatt2105.quizbank.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    public Quiz createQuiz(QuizDto quizDto) {
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setCategory(quizDto.getCategory());
        quiz.setDifficulty(quizDto.getDifficulty());

        List<Question> questions = quizDto.getQuestions().stream().map(questionDto -> {
            Question question = new Question();
            question.setQuestion_text(questionDto.getQuestion());
            question.setAnswer(questionDto.getAnswer());
            question.setQuiz(quiz); // Associate question with the quiz
            return question;
        }).collect(Collectors.toList());

        // Save the quiz and its questions to the database
        quiz.setQuestions(questions); // Set the questions to the quiz
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElseThrow(() ->
            new RuntimeException("Quiz not found with id: " + id));
    }

    public void deleteQuizById(Long id) {
        quizRepository.deleteById(id);
    }

    public Quiz updateQuiz(Long id, QuizDto quizDto) {
        Quiz existingQuiz = getQuizById(id);
        existingQuiz.setTitle(quizDto.getTitle());
        existingQuiz.setCategory(quizDto.getCategory());
        existingQuiz.setDifficulty(quizDto.getDifficulty());

        // Update or create new questions
        List<Question> updatedQuestions = quizDto.getQuestions().stream().map(questionDto -> {
            Question question = new Question();
            question.setQuestion_text(questionDto.getQuestion());
            question.setAnswer(questionDto.getAnswer());
            question.setQuiz(existingQuiz);
            return question;
        }).collect(Collectors.toList());

        // You might want to handle deletion of old questions if they are no longer present in the updated quiz
        existingQuiz.setQuestions(updatedQuestions);
        return quizRepository.save(existingQuiz);
    }

    public List<Quiz> getQuizzesByCategory(String category) {
        return quizRepository.findByCategory(category);
    }
}
