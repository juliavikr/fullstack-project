package no.ntnu.idatt2105.quizbank.controller;

import no.ntnu.idatt2105.quizbank.model.Question;
import no.ntnu.idatt2105.quizbank.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quiz/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public Iterable<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }
}
