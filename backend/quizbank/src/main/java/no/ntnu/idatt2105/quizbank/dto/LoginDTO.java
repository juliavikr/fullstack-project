package no.ntnu.idatt2105.quizbank.dto;

public class LoginDTO {
    private String username;
    private String password;
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public LoginDTO() {
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

    @Override
    public String toString() {
        return "LoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

