package no.ntnu.idatt2105.quizbank.response;

/**
 * Class representing the response for login
 * @version 1.0
 * @Author Ole Vik, Sigurd Aune, Jørgen Alvestad
 */
public class LoginResponse {
    private String message;
    private Boolean success;
    private String token; // Legger til et felt for tokenet

    /**
     * Constructor for LoginResponse
     * @param message The message of the response
     * @param success The success of the response
     * @param token The token of the response
     */
    public LoginResponse(String message, Boolean success, String token) {
        this.message = message;
        this.success = success;
        this.token = token; // Setter token-verdien
    }

    /**
     * Method for retrieving the success
     * @return True if the login was successful, false if not
     */
    public Boolean getSuccess() {
        return success;
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
     * Method for retrieving the token
     * @return The token of the response
     */
    public String getToken() {
        return token;
    }

    /**
     * Method for setting the token
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

}
