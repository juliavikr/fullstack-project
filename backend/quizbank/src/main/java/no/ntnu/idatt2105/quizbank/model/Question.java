package no.ntnu.idatt2105.quizbank.model;

import jakarta.persistence.*;

/**
 * Class representing the entity for questions
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_text")
    private String question_text;
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    /**
     * Constructor that initializes the question
     * @param questionText The question text
     * @param answer The answer to the question
     */
    public Question(String questionText, String answer) {
    this.question_text = questionText;
    this.answer = answer;
    }

    /**
     * Default constructor
     */
    public Question() {

    }

    /**
     * Method for setting the id
     * @param id The id of the question
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method for retrieving the id
     * @return The id of the question
     */
    public Long getId() {
        return id;
    }

    /**
     * Method for setting the question text
     * @param question The question text
     */
    public void setQuestion_text(String question) {
        this.question_text = question;
    }

    /**
     * Method for retrieving the question text
     * @return The question text
     */
    public String getQuestion_text() {
        return question_text;
    }

    /**
     * Method for setting the answer
     * @param answer The answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Method for retrieving the answer
     * @return The answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Method for setting the quiz
     * @param quiz The quiz
     */
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
