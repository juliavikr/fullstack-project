package no.ntnu.idatt2105.quizbank.response;

public class LoginResponse {
    private String message;
    private Boolean success;
    private String token; // Legger til et felt for tokenet

    public LoginResponse(String message, Boolean success, String token) {
        this.message = message;
        this.success = success;
        this.token = token; // Setter token-verdien
    }

    public Boolean getSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Eksisterende konstruktør uten token-parameter, for bakoverkompatibilitet
    public LoginResponse(String message, Boolean success) {
        this(message, success, null);
    }
}
