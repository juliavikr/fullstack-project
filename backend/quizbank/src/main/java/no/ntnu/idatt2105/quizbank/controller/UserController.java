package no.ntnu.idatt2105.quizbank.controller;

import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Class that handles the HTTP requests for the user entity
 * This class uses the UserService to access the business logic and manage the connection to the front-end
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Method for registering a new user account
     * Called when a POST request is sent to /api/user/register
     * Takes in a User object and encodes the password before sending it to the UserService
     * @param user User object containing the user details with the password in plain text
     * @return ResponseEntity containing a message that the user was registered successfully
     */

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        logger.debug("Attempting to register user: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.registerNewUserAccount(user);
         logger.info("User registered successfully: {}", user.getUsername());
        return ResponseEntity.ok("User registered successfully");
    }

    /**
     * Method for logging in a user
     * Called when a POST request is sent to /api/user/login
     * Takes in a User object containing the login details and checks if the user exists and the password is correct
     * @param loginDetails User object containing the login details
     * @return ResponseEntity containing a message that the user was logged in successfully or that the credentials are invalid
     */

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginDetails) {
        logger.debug("Attempting to login user: {}", loginDetails.getUsername());
        Optional<User> user = userService.findByUsername(loginDetails.getUsername());

        if (user.isPresent() && passwordEncoder.matches(loginDetails.getPassword(), user.get().getPassword())) {
            logger.info("User logged in successfully: {}", loginDetails.getUsername());
            return ResponseEntity.ok("User logged in successfully");
        } else {
            logger.warn("Invalid credentials for user: {}", loginDetails.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }
}

