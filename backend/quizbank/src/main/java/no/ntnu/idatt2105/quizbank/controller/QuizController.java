package no.ntnu.idatt2105.quizbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @GetMapping ("/greeting")
    public String greeting() {
        return "Hello, World!";
    }
}
