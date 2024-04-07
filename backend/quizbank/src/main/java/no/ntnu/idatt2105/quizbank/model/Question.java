package no.ntnu.idatt2105.quizbank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "question_text")
    private String question_text;

    private String answer;
    public Question(String questionText, String answer) {
    this.question_text = questionText;
    this.answer = answer;
}

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Question() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setQuestion_text(String question) {
        this.question_text = question;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getAnswer() {
        return answer;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    public Quiz getQuiz() {
        return quiz;
    }
}
