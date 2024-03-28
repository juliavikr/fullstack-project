package no.ntnu.idatt2105.quizbank.controller;

import no.ntnu.idatt2105.quizbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class that handles the HTTP requests for the user entity
 * This class uses the UserService to access the business logic and manage the connection to the front-end
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // API endepunkter for brukerregistrering, innlogging, etc.
}
