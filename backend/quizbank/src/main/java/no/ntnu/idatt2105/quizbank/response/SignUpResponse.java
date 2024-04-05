package no.ntnu.idatt2105.quizbank.response;

public class SignUpResponse {
    private String username;
    private String message;
    private boolean success;

    public SignUpResponse(String username, String message, boolean success) {
        this.username = username;
        this.message = message;
        this.success = success;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
// Getters and setters
}

