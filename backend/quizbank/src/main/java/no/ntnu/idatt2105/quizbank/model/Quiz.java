package no.ntnu.idatt2105.quizbank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the entity for quizzes
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@Getter
@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String category;
    private String difficulty;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;


    @JsonManagedReference
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    /**
     * Method for setting the id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method for setting the title
     * @param title The title of the quiz
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method for setting the category
     * @param category The category of the quiz
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Method for setting the difficulty
     * @param difficulty The difficulty of the quiz
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Method for setting the questions
     * @param questions The questions in the quiz
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * Method for setting the owner
     * @param owner The owner of the quiz
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }
}

