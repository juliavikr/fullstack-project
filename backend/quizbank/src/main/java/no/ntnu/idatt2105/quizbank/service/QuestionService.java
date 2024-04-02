package no.ntnu.idatt2105.quizbank.service;

import no.ntnu.idatt2105.quizbank.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
}
