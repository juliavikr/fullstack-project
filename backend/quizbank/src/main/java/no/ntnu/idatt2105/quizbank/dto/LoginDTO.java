package no.ntnu.idatt2105.quizbank.dto;

/**
 * Class representing the Data Transfer Object for login
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
public class LoginDTO {
    private String username;
    private String password;

    /**
     * Constructor that initializes the login DTO
     * @param username The username of the user
     * @param password The password of the user
     */
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Default constructor
     */
    public LoginDTO() {
    }

    /**
     * Method for retrieving the username
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method for setting the username
     * @param username The username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method for retrieving the password
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method for setting the password
     * @param password The password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

}

