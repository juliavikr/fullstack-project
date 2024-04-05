package no.ntnu.idatt2105.quizbank.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import no.ntnu.idatt2105.quizbank.model.Question;
import no.ntnu.idatt2105.quizbank.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Operation(summary = "Retrieve all questions",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all questions",
                content = @Content(schema = @Schema(implementation = Question.class)))})
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Operation(summary = "Retrieve a question by ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the question",
                content = @Content(schema = @Schema(implementation = Question.class))),
            @ApiResponse(responseCode = "404", description = "The question with the provided ID does not exist")})
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(
        @Parameter(description = "ID of the question to retrieve", required = true)
        @PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a new question",
        responses = {
            @ApiResponse(responseCode = "201", description = "Question created successfully",
                content = @Content(schema = @Schema(implementation = Question.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided for creating a new question")})
    @PostMapping
    public ResponseEntity<Question> createQuestion(
        @Parameter(description = "Question data to create a new question", required = true)
        @RequestBody Question question) {
        Question createdQuestion = questionService.createQuestion(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a question",
        responses = {
            @ApiResponse(responseCode = "200", description = "Question updated successfully",
                content = @Content(schema = @Schema(implementation = Question.class))),
            @ApiResponse(responseCode = "404", description = "The question with the provided ID does not exist"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided for updating the question")})
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(
        @Parameter(description = "ID of the question to update", required = true)
        @PathVariable Long id,
        @Parameter(description = "Updated question data", required = true)
        @RequestBody Question updatedQuestion) {
        Question question = questionService.updateQuestion(id, updatedQuestion);
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a question by ID",
        responses = {
            @ApiResponse(responseCode = "204", description = "Question deleted successfully"),
            @ApiResponse(responseCode = "404", description = "The question with the provided ID does not exist")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionById(
        @Parameter(description = "ID of the question to delete", required = true)
        @PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.noContent().build();
    }
}
