package no.ntnu.idatt2105.quizbank.service.implementation;

import no.ntnu.idatt2105.quizbank.dto.LoginDTO;
import no.ntnu.idatt2105.quizbank.dto.UserDTO;
import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.repository.UserRepository;
import no.ntnu.idatt2105.quizbank.response.LoginResponse;
import no.ntnu.idatt2105.quizbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

 @Override
public String registerNewUserAccount(UserDTO userDTO) {
    // Sjekk om brukernavnet allerede eksisterer
    if (userRepository.existsByUsername(userDTO.getUsername())) {
        throw new RuntimeException("Username is already taken");
    }

    // create and save new user if username is not taken
    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    userRepository.save(user);

    return user.getUsername(); // Returner brukernavnet når registreringen er vellykket
}

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
    User user = userRepository.findByUsername(loginDTO.getUsername()).orElse(null);
    if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
        // Generer JWT token eller lignende her
        return new LoginResponse("Login successful", true);
    } else {
        return new LoginResponse("Username or password is incorrect", false);
    }
}
}




