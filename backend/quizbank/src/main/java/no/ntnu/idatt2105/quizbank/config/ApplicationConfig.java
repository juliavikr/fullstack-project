package no.ntnu.idatt2105.quizbank.config;

import lombok.RequiredArgsConstructor;
import no.ntnu.idatt2105.quizbank.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Defines the application's security configuration.
 * This configuration class
 * specifies the beans for the authentication process, including the user details service,
 * password encoder, and authentication provider.
 * <p>
 * It integrates with Spring Security to facilitate the authentication and authorization
 * mechanisms used throughout the application.
 * </p>
 *
 * @author Vegard Johnsen, Sander R. Skofsrud
 * @see UserDetailsService
 * @see PasswordEncoder
 * @see AuthenticationManager
 * @since 0.1
 * @version 0.1
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;
    /**
     * Defines the bean for the authentication provider.
     * It is configured with the
     * custom user details service and password encoder to support authentication.
     *
     * @return The {@link DaoAuthenticationProvider} bean.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Defines the bean for the authentication manager, required by Spring Security
     * to handle authentication processes.
     *
     * @param config The {@link AuthenticationConfiguration} provided by Spring Security.
     * @return The {@link AuthenticationManager} bean.
     * @throws Exception If there is an issue configuring the authentication manager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Defines the bean for the password encoder.
     * This application uses BCrypt hashing
     * for storing and verifying user passwords securely.
     *
     * @return The {@link PasswordEncoder} bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   @Bean
    public UserDetailsService userDetailsService() {
        return userRepository::findByUsername;
    }
}
