package no.ntnu.idatt2105.quizbank.model;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import no.ntnu.idatt2105.quizbank.model.Quiz;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class representing the entity for users
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User implements UserDetails {

    @Id
    @Column(name = "id", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;
     @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Quiz> quizzes = new ArrayList<>();


    /**
     * Method for retrieving the quizzes
     * @return The quizzes of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Default constructor
     */
    public User() {

    }

    /**
     * Method for setting the id
     * @param id The id of the user
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method for retrieving the id
     * @return The id of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Method for setting the username
     * @param username The username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method for retrieving the username
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method for checking if the account is non expired
     * @return True if the account is non expired, false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * Method for checking if the account is non locked
     * @return True if the account is non locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * Method for checking if the credentials are non expired
     * @return True if the credentials are non expired, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * Method for checking if the account is enabled
     * @return True if the account is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return false;
    }

    /**
     * Method for setting the password
     * @param password The password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method for retrieving the password
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method for getting the authorities of the user
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    /**
     * Tostring method for the user
     * @return The user as a string, with all the fields
     */
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
