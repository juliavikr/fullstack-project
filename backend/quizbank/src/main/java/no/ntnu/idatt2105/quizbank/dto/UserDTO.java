package no.ntnu.idatt2105.quizbank.dto;

/**
 * Class representing the Data Transfer Object for users
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
public class UserDTO {

    private Long id;
    private String username;

    private String password;

    /**
     * Constructor that initializes the user DTO
     * @param username The username of the user
     * @param password The password of the user
     */
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * default constructor
     */
    public UserDTO() {

    }


    /**
     * Method for retrieving the id
     * @return The id of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Method for setting the id
     * @param id The id of the user
     */
    public void setId(Long id) {
        this.id = id;
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
