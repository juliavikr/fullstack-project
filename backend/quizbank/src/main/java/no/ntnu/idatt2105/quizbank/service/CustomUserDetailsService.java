package no.ntnu.idatt2105.quizbank.service;


import no.ntnu.idatt2105.quizbank.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * A service class that implements {@link UserDetailsService} interface
 * this class provides custom implementation of loadUserByUsername method to fetch user details from the database using the username
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Load user details by username from the userRepository.
     * @param username username of the user to load
     * @return UserDetails object containing the user details
     * @throws UsernameNotFoundException if the user is not found
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // retrieve user from the userRepository
        no.ntnu.idatt2105.quizbank.model.User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));

        // builds and returns a UserDetails object, representing the user in the database
        return User.withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities("ROLE_USER")
            .build();
    }
}

