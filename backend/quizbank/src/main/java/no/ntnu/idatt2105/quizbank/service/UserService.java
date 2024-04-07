package no.ntnu.idatt2105.quizbank.service;

import no.ntnu.idatt2105.quizbank.dto.LoginDTO;
import no.ntnu.idatt2105.quizbank.dto.UserDTO;
import no.ntnu.idatt2105.quizbank.response.LoginResponse;


/**
 * Service class for User
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
public interface UserService {
    String registerNewUserAccount(UserDTO userDTO);
    LoginResponse loginUser(LoginDTO loginDTO);
}

