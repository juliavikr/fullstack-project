package no.ntnu.idatt2105.quizbank.dto;


import java.util.List;

public class QuizDto {
  private String title;
  private String category;
  private String difficulty;
  private List<QuestionDto> questions;

  public QuizDto() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }

  public List<QuestionDto> getQuestions() {
    return questions;
  }

  public void setQuestions(List<QuestionDto> questions) {
    this.questions = questions;
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

