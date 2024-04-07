package no.ntnu.idatt2105.quizbank.response;

/**
 * Class representing the response for sign up
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
public class SignUpResponse {
    private String username;
    private String message;
    private boolean success;

    /**
     * Constructor for SignUpResponse
     * @param username The username of the response
     * @param message The message of the response
     * @param success The success of the response
     */
    public SignUpResponse(String username, String message, boolean success) {
        this.username = username;
        this.message = message;
        this.success = success;
    }

    /**
     * Method for retrieving the username
     * @return The username of the response
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method for setting the username
     * @param username The username of the response
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method for retrieving the message
     * @return The message of the response
     */
    public String getMessage() {
        return message;
    }

    /**
     * Method for setting the message
     * @param message The message of the response
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Method for retrieving the success
     * @return True if the sign up was successful, false if not
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Method for setting the success
     * @param success The success of the response
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}

