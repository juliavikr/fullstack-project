package no.ntnu.idatt2105.quizbank.service;

import no.ntnu.idatt2105.quizbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
    * Class that handles the business logic for the user entity
    * This class uses the UserRepository to access the database
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
}
