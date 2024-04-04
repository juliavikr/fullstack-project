package no.ntnu.idatt2105.quizbank.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a user in the database
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;
     @Column(name = "password")
    private String password;

    // en bruker kan ha flere quizzer
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz> quizzes = new ArrayList<>();


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", quizzes=" + quizzes +
                '}';
    }
}
