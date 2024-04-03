package no.ntnu.idatt2105.quizbank.controller.dto;

public class QuestionDto {
  private String question;
  private String answer;

  public QuestionDto() {
  }

  public QuestionDto(String question, String answer) {
    this.question = question;
    this.answer = answer;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  @Override
  public String toString() {
    return "QuestionDto{" +
      "question='" + question + '\'' +
      ", answer='" + answer + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    QuestionDto that = (QuestionDto) o;

    if (question != null ? !question.equals(that.question) : that.question != null) return false;
    return answer != null ? answer.equals(that.answer) : that.answer == null;
  }

  @Override
  public int hashCode() {
    int result = question != null ? question.hashCode() : 0;
    result = 31 * result + (answer != null ? answer.hashCode() : 0);
    return result;
  }
}
