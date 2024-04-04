package no.ntnu.idatt2105.quizbank.controller;

import no.ntnu.idatt2105.quizbank.dto.QuizDto;
import no.ntnu.idatt2105.quizbank.model.Quiz;
import no.ntnu.idatt2105.quizbank.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizDto) {
        return new ResponseEntity<>(quizService.createQuiz(quizDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        return new ResponseEntity<>(quizService.getQuizById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizById(@PathVariable Long id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody QuizDto quizDto) {
        return new ResponseEntity<>(quizService.updateQuiz(id, quizDto), HttpStatus.OK);
    }
}