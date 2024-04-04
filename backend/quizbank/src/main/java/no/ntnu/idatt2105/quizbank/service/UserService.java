package no.ntnu.idatt2105.quizbank.service;

import no.ntnu.idatt2105.quizbank.dto.LoginDTO;
import no.ntnu.idatt2105.quizbank.dto.UserDTO;
import no.ntnu.idatt2105.quizbank.response.LoginResponse;

/*
    * Class that handles the business logic for the user entity
    * This class uses the UserRepository to access the database
 */

public interface UserService {
    String registerNewUserAccount(UserDTO userDTO);
    LoginResponse loginUser(LoginDTO loginDTO);

    }

    /*  private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public User registerNewUserAccount(User userDto) {
        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    } */


    // Andre metoder som updateUser, deleteUser, etc.
//}

