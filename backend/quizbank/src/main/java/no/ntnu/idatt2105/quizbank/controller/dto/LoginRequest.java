package no.ntnu.idatt2105.quizbank.controller.dto;

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest() {
    }

    // Standard getters og setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

