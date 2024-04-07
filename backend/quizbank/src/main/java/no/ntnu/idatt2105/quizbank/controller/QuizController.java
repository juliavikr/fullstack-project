package no.ntnu.idatt2105.quizbank.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import no.ntnu.idatt2105.quizbank.model.Quiz;
import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.service.QuizService;
import no.ntnu.idatt2105.quizbank.dto.QuizDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class representing the controller for the Quiz entity
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;

    /**
     * Constructor that injects the QuizService
     * @param quizService The service class for quizzes
     */
    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @Operation(summary = "Get all quizzes",
        responses = {
            @ApiResponse(responseCode = "200", description = "All quizzes retrieved successfully",
                content = @Content(schema = @Schema(implementation = Quiz.class)))
        })
    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
    }

    /**
     * Method for retrieving a quiz by ID
     * @param id ID of the quiz to retrieve
     * @return The quiz with the provided ID
     */
    @Operation(summary = "Get quiz by ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Quiz found",
                content = @Content(schema = @Schema(implementation = Quiz.class))),
            @ApiResponse(responseCode = "404", description = "Quiz not found")
        })
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(
        @Parameter(description = "The ID of the quiz to retrieve", required = true)
        @PathVariable Long id) {
        return new ResponseEntity<>(quizService.getQuizById(id), HttpStatus.OK);
    }

    /**
     * Method for updating a quiz
     * @param id ID of the quiz to update
     * @param quizDto Quiz data to update the quiz
     * @return The updated quiz
     */
    @Operation(summary = "Update quiz details",
        responses = {
            @ApiResponse(responseCode = "200", description = "Quiz updated successfully",
                content = @Content(schema = @Schema(implementation = Quiz.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Quiz not found")
        })
    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(
        @Parameter(description = "The ID of the quiz to update", required = true)
        @PathVariable Long id,
        @Parameter(description = "Quiz data transfer object containing the updated details of the quiz", required = true)
        @RequestBody QuizDto quizDto) {
        return new ResponseEntity<>(quizService.updateQuiz(id, quizDto), HttpStatus.OK);
    }

    /**
     * Method for deleting a quiz by ID
     * @param id ID of the quiz to delete
     */
    @Operation(summary = "Delete a quiz by ID",
        responses = {
            @ApiResponse(responseCode = "204", description = "Quiz deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Quiz not found")
        })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizById(
        @Parameter(description = "The ID of the quiz to delete", required = true)
        @PathVariable Long id, @AuthenticationPrincipal
    User userDetails) {
        //find quiz by id
        Quiz quizById = quizService.getQuizById(id);
        quizService.deleteQuizById(id);
        Quiz quiz = quizService.getQuizById(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Method for retrieving all quizzes for the current user
     * @param userDetails The current user
     * @return List of quizzes for the current user
     */
    @Operation(summary = "Get quizzes for the current user",
        responses = {
            @ApiResponse(responseCode = "200", description = "Quizzes retrieved successfully",
                content = @Content(schema = @Schema(implementation = Quiz.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access")
        })
    @GetMapping("/my")
    public ResponseEntity<List<Quiz>> getQuizzesForCurrentUser(@AuthenticationPrincipal User userDetails) {
        List<Quiz> quizzes = quizService.getQuizzesByUser(userDetails.getId());
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    /**
     * Method for creating a new quiz
     * @param quizDto Quiz data transfer object containing the details of the new quiz
     * @param principal The current user
     * @return The created quiz
     */
    @Operation(summary = "Create a new quiz",
        responses = {
            @ApiResponse(responseCode = "201", description = "Quiz created successfully",
                content = @Content(schema = @Schema(implementation = Quiz.class))),
            @ApiResponse(responseCode = "400", description = "Invalid quiz data provided"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access")
        })
    @PostMapping
    public ResponseEntity<Quiz> createQuiz(
        @Parameter(description = "Quiz data transfer object containing the details of the new quiz", required = true)
        @RequestBody QuizDto quizDto,
        @AuthenticationPrincipal User principal) {
        return new ResponseEntity<>(quizService.createQuiz(quizDto, principal), HttpStatus.CREATED);
    }
}