package no.ntnu.idatt2105.quizbank.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import no.ntnu.idatt2105.quizbank.dto.LoginDTO;
import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.response.SignUpResponse;
import no.ntnu.idatt2105.quizbank.response.LoginResponse;
import no.ntnu.idatt2105.quizbank.dto.UserDTO;
import no.ntnu.idatt2105.quizbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Class representing the controller for the User entity
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Method for registering a new user
     * @param userDTO User DTO containing user registration information
     * @return Response entity containing the user's username and a message
     */
    @Operation(summary = "Register a new user",
        responses = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                content = @Content(schema = @Schema(implementation = SignUpResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, unable to register the user")
        })
    @PostMapping("/register")
    public ResponseEntity<SignUpResponse> registerUser(
        @Parameter(description = "User DTO containing user registration information", required = true)
        @RequestBody UserDTO userDTO) {
        try {
            String username = userService.registerNewUserAccount(userDTO);
            SignUpResponse response = new SignUpResponse(username, "User registered successfully", true);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SignUpResponse(null, e.getMessage(), false));
        }
    }

    /**
     * Method for logging in a user
     * @param loginDTO Login DTO containing user login credentials
     * @return Response entity containing the user's username and a message
     */
    @Operation(summary = "Login a user",
        responses = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully",
                content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, login details are incorrect")
        })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
        @Parameter(description = "Login DTO containing user login credentials", required = true)
        @RequestBody LoginDTO loginDTO) {
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

    /**
     * Method for getting the current user's information
     * @param currentUser The current user
     * @return The current user's information
     */
    @Operation(summary = "Get current user's information",
        responses = {
            @ApiResponse(responseCode = "200", description = "Current user's information retrieved successfully",
                content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, user is not authenticated")
        })
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal User currentUser) {
        // The @AuthenticationPrincipal automatically injects the authenticated user
        return ResponseEntity.ok(currentUser);
    }

}
