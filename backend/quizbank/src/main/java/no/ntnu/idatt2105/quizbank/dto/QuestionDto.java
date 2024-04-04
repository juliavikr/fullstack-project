package no.ntnu.idatt2105.quizbank.dto;

public class QuestionDto {
  private String question_text;
  private String answer;

  private Long id;

  public QuestionDto() {
  }

  public QuestionDto(String question, String answer, Long id) {
    this.question_text = question;
    this.answer = answer;
    this.id = id;
  }

  public String getQuestion_text() {
    return question_text;
  }

  public void setQuestion_text(String question_text) {
    this.question_text = question_text;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "QuestionDto{" +
        "question='" + question_text + '\'' +
        ", answer='" + answer + '\'' +

        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    QuestionDto that = (QuestionDto) o;

    if (question_text != null ? !question_text.equals(that.question_text) : that.question_text != null) return false;
    return answer != null ? answer.equals(that.answer) : that.answer == null;
  }

  @Override
  public int hashCode() {
    int result = question_text != null ? question_text.hashCode() : 0;
    result = 31 * result + (answer != null ? answer.hashCode() : 0);
    return result;
  }
}