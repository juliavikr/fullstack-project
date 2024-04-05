package no.ntnu.idatt2105.quizbank.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class QuizDto {
  @NotBlank(message = "Title cannot be blank")
  private String title;
  private String category;
  private String difficulty;
  private List<QuestionDto> questions;

  private Long userId;

  public QuizDto() {
  }

  public QuizDto(String title, String category, String difficulty, List<QuestionDto> questions) {
    this.title = title;
    this.category = category;
    this.difficulty = difficulty;
    this.questions = questions;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }

  public void setQuestions(List<QuestionDto> questions) {
    this.questions = questions;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }


  @Override
  public String toString() {
    return "QuizDto{" +
      "title='" + title + '\'' +
      ", category='" + category + '\'' +
      ", difficulty='" + difficulty + '\'' +
      ", questions=" + questions +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    QuizDto quizDto = (QuizDto) o;

    if (title != null ? !title.equals(quizDto.title) : quizDto.title != null) return false;
    if (category != null ? !category.equals(quizDto.category) : quizDto.category != null) return false;
    if (difficulty != null ? !difficulty.equals(quizDto.difficulty) : quizDto.difficulty != null) return false;
    return questions != null ? questions.equals(quizDto.questions) : quizDto.questions == null;
  }

  @Override
  public int hashCode() {
    int result = title != null ? title.hashCode() : 0;
    result = 31 * result + (category != null ? category.hashCode() : 0);
    result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
    result = 31 * result + (questions != null ? questions.hashCode() : 0);
    return result;
  }

}

