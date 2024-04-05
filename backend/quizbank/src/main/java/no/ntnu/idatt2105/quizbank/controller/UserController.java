package no.ntnu.idatt2105.quizbank.controller;

/**
 * Class that handles the HTTP requests for the user entity
 * This class uses the UserService to access the business logic and manage the connection to the front-end
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import no.ntnu.idatt2105.quizbank.dto.LoginDTO; // Importer din LoginDTO
import no.ntnu.idatt2105.quizbank.dto.UserDTO; // Importer din UserDTO
import no.ntnu.idatt2105.quizbank.response.LoginResponse; // Importer din LoginResponse
import no.ntnu.idatt2105.quizbank.response.SignUpResponse; // Importer din SignUpResponse
import no.ntnu.idatt2105.quizbank.service.UserService; // Importer din UserService

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<SignUpResponse> registerUser(@RequestBody UserDTO userDTO) {
        try {
            String username = userService.registerNewUserAccount(userDTO);
            SignUpResponse response = new SignUpResponse(username, "User registered successfully", true);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SignUpResponse(null, e.getMessage(), false));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            LoginResponse loginResponse = userService.loginUser(loginDTO);
            if (loginResponse.getSuccess()) {
                return ResponseEntity.ok(loginResponse);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(e.getMessage(), false, null));
        }
    }
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



