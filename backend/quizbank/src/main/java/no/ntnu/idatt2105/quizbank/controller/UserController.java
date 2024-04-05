package no.ntnu.idatt2105.quizbank.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import no.ntnu.idatt2105.quizbank.dto.LoginDTO;
import no.ntnu.idatt2105.quizbank.response.SignUpResponse;
import no.ntnu.idatt2105.quizbank.response.LoginResponse;
import no.ntnu.idatt2105.quizbank.dto.UserDTO;
import no.ntnu.idatt2105.quizbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

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
}
