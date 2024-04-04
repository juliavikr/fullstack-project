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

import java.util.Optional;

@Service
public class UserIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String registerNewUserAccount(UserDTO userDTO) {
        User user = new User(

                userDTO.getUsername(),
                userDTO.getPassword());
                this.passwordEncoder.encode(userDTO.getPassword());

       userRepository.save(user);
         return user.getUsername();
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        String message = "";
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (user != null) {
            String password = user.getPassword();
            String encryptedPassword = passwordEncoder.encode(loginDTO.getPassword());
            Boolean correctPassword = passwordEncoder.matches(password, encryptedPassword);
            if (correctPassword) {
                Optional<User> user1 = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
                if (user1.isPresent()) {
                    return new LoginResponse("Login successful", true);
                } else {
                    return new LoginResponse("Login failed", false);
                }
            } else {
                return new LoginResponse("Wrong password", false);
            }
        } else { return new LoginResponse("User not found", false);
        }
}
}




