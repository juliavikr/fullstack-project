package no.ntnu.idatt2105.quizbank.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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

    // Establish a relationship with the User entity
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    // One quiz can have many questions
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
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

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

