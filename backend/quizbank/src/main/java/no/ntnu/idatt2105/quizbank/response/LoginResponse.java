package no.ntnu.idatt2105.quizbank.response;

public class LoginResponse {
    String message;
    Boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public LoginResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
