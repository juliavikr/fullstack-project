package no.ntnu.idatt2105.quizbank.controller;

import no.ntnu.idatt2105.quizbank.dto.LoginDTO;
import no.ntnu.idatt2105.quizbank.dto.UserDTO;
import no.ntnu.idatt2105.quizbank.response.LoginResponse;
import no.ntnu.idatt2105.quizbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Class that handles the HTTP requests for the user entity
 * This class uses the UserService to access the business logic and manage the connection to the front-end
 */
@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO)
    {
       String id = userService.registerNewUserAccount(userDTO);
        return id;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

    /**
     * Method for registering a new userDTO account
     * Called when a POST request is sent to /api/userDTO/register
     * Takes in a User object and encodes the password before sending it to the UserService
     * @param userDTO User object containing the userDTO details with the password in plain text
     * @return ResponseEntity containing a message that the userDTO was registered successfully
     */

   /* @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        logger.debug("Attempting to register user: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.registerNewUserAccount(user);
         logger.info("User registered successfully: {}", user.getUsername());
        return ResponseEntity.ok("User registered successfully");
    }*/

    /**
     * Method for logging in a user
     * Called when a POST request is sent to /api/user/login
     * Takes in a User object containing the login details and checks if the user exists and the password is correct
     * @param loginDetails User object containing the login details
     * @return ResponseEntity containing a message that the user was logged in successfully or that the credentials are invalid
     */

   /* @PostMapping("/login")
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
}*/

}

