package no.ntnu.idatt2105.quizbank.dto;

/**
 * Class representing the Data Transfer Object for questions
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
public class QuestionDto {
  private String question_text;
  private String answer;

  private Long id;

  /**
   * Default constructor
   */
  public QuestionDto() {
  }

  /**
   * Constructor that initializes the question DTO
   * @param question The question text
   * @param answer The answer to the question
   */
  public QuestionDto(String question, String answer, Long id) {
    this.question_text = question;
    this.answer = answer;
    this.id = id;
  }

  /**
   * Method for retrieving the question text
   * @return The question text
   */
  public String getQuestion_text() {
    return question_text;
  }

  /**
   * Method for setting the question text
   * @param question_text The question text
   */
  public void setQuestion_text(String question_text) {
    this.question_text = question_text;
  }

  /**
   * Method for retrieving the answer
   * @return The answer
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * Method for setting the answer
   * @param answer The answer
   */
  public void setAnswer(String answer) {
    this.answer = answer;
  }

  /**
   * Method for retrieving the ID
   * @return The ID
   */
  public Long getId() {
    return id;
  }

  /**
   * Method for setting the ID
   * @param id The ID
   */
  public void setId(Long id) {
    this.id = id;
  }
}