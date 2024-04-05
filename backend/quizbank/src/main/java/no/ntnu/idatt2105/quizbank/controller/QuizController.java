package no.ntnu.idatt2105.quizbank.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import no.ntnu.idatt2105.quizbank.model.Quiz;
import no.ntnu.idatt2105.quizbank.service.QuizService;
import no.ntnu.idatt2105.quizbank.dto.QuizDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @Operation(summary = "Create a new quiz",
        responses = {
            @ApiResponse(responseCode = "201", description = "Quiz created successfully",
                content = @Content(schema = @Schema(implementation = Quiz.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
        })
    @PostMapping
    public ResponseEntity<Quiz> createQuiz(
        @Parameter(description = "Quiz data transfer object containing the details of the quiz to be created", required = true)
        @RequestBody QuizDto quizDto) {
        return new ResponseEntity<>(quizService.createQuiz(quizDto), HttpStatus.CREATED);
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

    @Operation(summary = "Delete a quiz by ID",
        responses = {
            @ApiResponse(responseCode = "204", description = "Quiz deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Quiz not found")
        })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizById(
        @Parameter(description = "The ID of the quiz to delete", required = true)
        @PathVariable Long id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.noContent().build();
    }
}