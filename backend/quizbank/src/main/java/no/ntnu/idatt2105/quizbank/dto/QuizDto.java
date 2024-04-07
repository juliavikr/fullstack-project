package no.ntnu.idatt2105.quizbank.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

/**
 * Class representing the Data Transfer Object for quizzes
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@Getter
public class QuizDto {
  @NotBlank(message = "Title cannot be blank")
  private String title;
  private String category;
  private String difficulty;
  private List<QuestionDto> questions;

  private Long userId;

  /**
   * Default constructor
   */
  public QuizDto() {
  }

  /**
   * Constructor that initializes the quiz DTO
   * @param title The title of the quiz
   * @param category The category of the quiz
   * @param difficulty The difficulty of the quiz
   * @param questions The questions in the quiz
   */
  public QuizDto(String title, String category, String difficulty, List<QuestionDto> questions) {
    this.title = title;
    this.category = category;
    this.difficulty = difficulty;
    this.questions = questions;
  }

  /**
   * Method for setting the title of the quiz
   * @param title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Method for setting the category of the quiz
   * @param category
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Method for setting the difficulty of the quiz
   * @param difficulty
   */
  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * Method for setting the questions of the quiz
   * @param questions
   */
  public void setQuestions(List<QuestionDto> questions) {
    this.questions = questions;
  }

  /**
   * Method for retrieving the user ID
   * @return The user ID
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * Method for setting the user ID
   * @param userId The user ID
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

}

