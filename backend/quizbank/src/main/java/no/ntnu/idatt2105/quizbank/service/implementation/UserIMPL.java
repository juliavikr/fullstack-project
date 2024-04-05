package no.ntnu.idatt2105.quizbank.service.implementation;

import no.ntnu.idatt2105.quizbank.dto.LoginDTO;
import no.ntnu.idatt2105.quizbank.dto.UserDTO;
import no.ntnu.idatt2105.quizbank.model.User;
import no.ntnu.idatt2105.quizbank.repository.UserRepository;
import no.ntnu.idatt2105.quizbank.response.LoginResponse;
import no.ntnu.idatt2105.quizbank.security.JwtTokenUtil;
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

    @Autowired
    private JwtTokenUtil jwtTokenUtil; // Legg til JwtTokenUtil

    @Override
    public String registerNewUserAccount(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);

        return user.getUsername();
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElse(null);
        if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            // Generere og returnere JWT token
            final String token = jwtTokenUtil.generateToken(user);
            return new LoginResponse("Login successful", true, token);
        } else {
            return new LoginResponse("Username or password is incorrect", false, null);
        }
    }
}



